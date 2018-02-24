package cn.mars.mq.rabbit.util;

import cn.mars.mq.rabbit.constant.MQConstant;
import cn.mars.mq.rabbit.enums.MQ;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description：
 * Created by Mars on 2017/11/22.
 */
@Component
public class Producer {

    private int type = MQ.Type.Producer.getCode();

    @Autowired
    private ChannelUtil channelUtil;

    public boolean send(String message) {
        boolean success = false;
        try {
            Channel channel = channelUtil.getChannel(type);
            channel.basicPublish("", MQConstant.QUEUE_NAME, null, message.getBytes(MQConstant.DEFAULT_CHARSET));
            channelUtil.releaseChannel(channel, type);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

}
