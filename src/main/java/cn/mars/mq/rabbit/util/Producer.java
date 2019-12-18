package cn.mars.mq.rabbit.util;

import cn.mars.mq.rabbit.constant.MQConstant;
import cn.mars.mq.rabbit.enums.MQ;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Created by Mars on 2017/11/22.
 */
@Component
public class Producer {

    private int type = MQ.Type.Producer.getCode();

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private ChannelUtil channelUtil;

    public boolean send(String message) {
        return send(message, null, null);
    }

    public boolean send(String message, Long delaySeconds) {
        return send(message, delaySeconds, null);
    }

    public boolean send(String message, TimeConfig timeConfig) {
        return send(message, null, timeConfig);
    }

    private boolean send(String message, Long delaySeconds, TimeConfig timeConfig){
        boolean success = false;
        try {
            Channel channel = channelUtil.getChannel(type);
            if(timeConfig != null){

            }else if(delaySeconds != null) {
                Map<String, Object> headers = new HashMap<String, Object>();
                Date now = new Date();
                Date timeToPublish = new Date(System.currentTimeMillis()+delaySeconds*1000);

                headers.put("x-delay", timeToPublish.getTime() - now.getTime());

                AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                        .headers(headers);
                channel.basicPublish(MQConstant.DELAY_EXCHANGE_NAME, MQConstant.DELAY_QUEUE_NAME, props.build(),
                        message.getBytes(MQConstant.DEFAULT_CHARSET));
            }else {
                channel.basicPublish("", MQConstant.QUEUE_NAME, null, message.getBytes(MQConstant.DEFAULT_CHARSET));
            }
            channelUtil.releaseChannel(channel, type);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
