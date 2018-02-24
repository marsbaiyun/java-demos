package cn.mars.websocket.service;

import cn.mars.websocket.dto.Response;
import cn.mars.websocket.entity.Client;

/**
 * Description：
 * Created by Mars on 2018/2/23.
 */
public class MessageService {

    public static Response sendMessage(Client client, String message) {
        Response res = new Response();
        res.getData().put("id", client.getId());
        res.getData().put("message", message);
        res.getData().put("ts", System.currentTimeMillis());// 返回毫秒数
        return res;
    }

}
