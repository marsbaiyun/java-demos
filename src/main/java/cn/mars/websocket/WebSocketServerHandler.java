package cn.mars.websocket;

import cn.mars.websocket.dto.Response;
import cn.mars.websocket.entity.Client;
import cn.mars.websocket.service.MessageService;
import cn.mars.websocket.service.RequestService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaderNames.HOST;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Description：
 * Created by Mars on 2018/2/23.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //websocket 服务的uri
    private static final String WEBSOCKET_PATH = "/websocket";

    //一个ChannelGroup就是一个直播频道
    private static Map<Integer, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    //本次请求的code
    private static final String HTTP_REQUEST_STRING = "request";

    private Client client = null;

    private WebSocketServerHandshaker handshaker;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // Handle a bad request.
        if(!req.decoderResult().isSuccess()){
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }

        // Allow only GET methods.
        if(req.method() != GET){
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
            return;
        }

        if ("/favicon.ico".equals(req.uri()) || ("/".equals(req.uri()))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
            return;
        }

        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
        Map<String, List<String>> parameters = queryStringDecoder.parameters();

        if (parameters.size() == 0 || !parameters.containsKey(HTTP_REQUEST_STRING)) {
            logger.error(HTTP_REQUEST_STRING + "参数不可缺省");
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
            return;
        }

        client = RequestService.clientRegister(parameters.get(HTTP_REQUEST_STRING).get(0));
        if(client.getRoomId() == 0){
            logger.error("房间号不可缺省");
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
            return;
        }

        //房间列表中如果不存在则为该频道新增一个频道ChannelDroup
        if(!channelGroupMap.containsKey(client.getRoomId())){
            channelGroupMap.put(client.getRoomId(), new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
        }
        //确定有房间号，才将客户端加入到频道中
        channelGroupMap.get(client.getRoomId()).add(ctx.channel());

        //handshake
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req), null, true);
        handshaker = wsFactory.newHandshaker(req);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            ChannelFuture channelFuture = handshaker.handshake(ctx.channel(), req);

            //握手成功之后，业务逻辑
            if(channelFuture.isSuccess()){
                if(client.getId() == 0){
                    logger.info("游客{}加入房间{}，channel id : {}", client.getId(), client.getRoomId(), ctx.channel().id().asShortText());
                }else {
                    logger.info("用户{}加入房间{}，channel id : {}", client.getId(), client.getRoomId(), ctx.channel().id().asShortText());
                }
                return;
            }
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }

        if (client.getId() == 0) {
            Response response = new Response(1001, "没登录不能聊天哦");
            String msg = new JSONObject(response).toString();
            ctx.channel().write(new TextWebSocketFrame(msg));
            return;
        }

        String request = ((TextWebSocketFrame) frame).text();
        logger.info("收到{}", ctx.channel() + request);

        if("单聊".equals(request)){
            //单独回复
            ctx.channel().writeAndFlush(new TextWebSocketFrame("好的"));
        }else {
            broadcast(request);
        }
    }

    /**
     * 房间内广播
     * @param request
     */
    private void broadcast(String request) {
        Response response = MessageService.sendMessage(client, request);
        String msg = new JSONObject(response).toString();
        if (channelGroupMap.containsKey(client.getRoomId())) {
            channelGroupMap.get(client.getRoomId()).writeAndFlush(new TextWebSocketFrame(msg));
        }
    }

    /**
     * 全部房间广播
     * @param msg
     */
    private void broadcastAll(String msg){
        if(MapUtils.isEmpty(channelGroupMap)){
            return;
        }
        for(ChannelGroup channelGroup : channelGroupMap.values()){
            if(channelGroup == null) continue;
            channelGroup.writeAndFlush(new TextWebSocketFrame(msg));
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpHeaderUtil.setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaderUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        logger.info("收到{}握手请求", incoming.id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (client != null && channelGroupMap.containsKey(client.getRoomId())) {
            channelGroupMap.get(client.getRoomId()).remove(ctx.channel());
        }
    }

    private static String getWebSocketLocation(FullHttpRequest req) {
        String location = req.headers().get(HOST) + WEBSOCKET_PATH;
        return "ws://" + location;
    }
}
