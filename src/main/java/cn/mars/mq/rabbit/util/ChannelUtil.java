package cn.mars.mq.rabbit.util;

import cn.mars.mq.rabbit.constant.MQConstant;
import cn.mars.mq.rabbit.enums.MQ;
import com.google.common.collect.Lists;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 * Created by Mars on 2017/11/22.
 */
@Component
public class ChannelUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConnectionFactory factory;
    private Connection producerConnection;
    private Connection consumerConnection;
    private volatile List<Channel> freeProducerChannelList;
    private volatile List<Channel> freeConsumerChannelList;

    @PostConstruct
    private void initConfig(){
        factory = new ConnectionFactory();
        factory.setHost(MQConstant.HOST);
        factory.setUsername(MQConstant.USERNAME);
        factory.setPassword(MQConstant.PWD);
        factory.setConnectionTimeout(3000);
        freeProducerChannelList = Lists.newArrayList();
        freeConsumerChannelList = Lists.newArrayList();
        try {
            producerConnection = factory.newConnection();
            consumerConnection = factory.newConnection();
        } catch (Exception e) {
            logger.error("初始化producerConnection/consumerConnection异常", e);
        }
        initExchange();
    }

    private void initExchange(){
        Channel channel = getChannel(MQ.Type.Producer.getCode());
        // 声明x-delayed-type类型的exchange
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", MQConstant.DELAY_EXCHANGE_PRE_NAME);
        args.put("x-dead-letter-routing-key", MQConstant.DELAY_QUEUE_PRE_NAME);

        try {
            channel.exchangeDeclare(MQConstant.DELAY_EXCHANGE_PRE_NAME, BuiltinExchangeType.DIRECT, true,
                    false, args);
        } catch (IOException e) {
            e.printStackTrace();
        }
        releaseChannel(channel, MQ.Type.Producer.getCode());
    }

    public Channel getChannel(int type){
        Channel channel = null;
        try {
            if(type == MQ.Type.Producer.getCode()){
                if(CollectionUtils.isEmpty(freeProducerChannelList)){
                    channel = producerConnection.createChannel();
                }else {
                    channel = freeProducerChannelList.remove(0);
                }
            }else if(type == MQ.Type.Consumer.getCode()){
                if(CollectionUtils.isEmpty(freeConsumerChannelList)){
                    channel = consumerConnection.createChannel();
                }else {
                    channel = freeConsumerChannelList.remove(0);
                }
            }else {
                logger.error("未知类型的channel");
                return null;
            }
            if(!channel.isOpen()){
                channel.queueDeclare(MQConstant.QUEUE_NAME, false, false, false, null);
            }
        } catch (IOException e) {
            logger.error("获取{}类型的channel异常", type, e);
        }
        return channel;
    }

    public void releaseChannel(Channel channel, int type){
        if(channel.isOpen()){
            if(type == MQ.Type.Producer.getCode()){
                freeProducerChannelList.add(channel);
            }else if(type == MQ.Type.Consumer.getCode()){
                freeConsumerChannelList.add(channel);
            }else {
                logger.error("未知类型的channel");
            }
        }
    }
}
