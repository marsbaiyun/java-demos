package cn.mars.mq.rabbit.util;

import cn.mars.mq.rabbit.constant.MQConstant;
import cn.mars.mq.rabbit.enums.MQ;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static cn.mars.mq.rabbit.constant.MQConstant.DELAY_EXCHANGE_NAME;
import static cn.mars.mq.rabbit.constant.MQConstant.DELAY_QUEUE_NAME;
import static cn.mars.mq.rabbit.constant.MQConstant.QUEUE_NAME;

/**
 * Description：
 * Created by Mars on 2017/11/22.
 */
@Component
public class Consumer{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int type = MQ.Type.Consumer.getCode();

    @Autowired
    ChannelUtil channelUtil;

    @PostConstruct
    private void initConsumer(){
        Channel generalChannel = channelUtil.getChannel(type);
        try {
            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
            DefaultConsumer consumer = new DefaultConsumer(generalChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.info("general message received : {}", message);
                }
            };
            //自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
            generalChannel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Channel delayChannel = channelUtil.getChannel(type);
        try {
            DefaultConsumer queueingConsumer = new DefaultConsumer(delayChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.info("delay message received : {}", message);
                }
            };

            delayChannel.exchangeDeclare(DELAY_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
            delayChannel.exchangeBind(DELAY_QUEUE_NAME, DELAY_EXCHANGE_NAME, "");
            delayChannel.queueDeclare(DELAY_QUEUE_NAME, true,false,false,null);
            delayChannel.queueBind(DELAY_QUEUE_NAME, DELAY_EXCHANGE_NAME, "");
            delayChannel.basicConsume(DELAY_QUEUE_NAME, true, queueingConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
