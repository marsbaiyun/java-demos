package cn.mars.mq.rabbit.controller;

import cn.mars.mq.rabbit.util.Consumer;
import cn.mars.mq.rabbit.util.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 * Created by Mars on 2017/11/22.
 */
@RestController
@RequestMapping("mq")
public class MQController {

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @RequestMapping("send/{messageCount}")
    public Object sendMessage(@PathVariable int messageCount){
        for(int i = 1;i <= messageCount;i ++){
            producer.send(String.valueOf(i));
        }
        return new ResponseEntity("发送消息成功", HttpStatus.OK);
    }

    @RequestMapping("delay/send/{delayseconds}/{message}")
    public Object sendMessage(@PathVariable long delayseconds, @PathVariable String message){
        producer.send(message, delayseconds);
        return new ResponseEntity("发送消息成功", HttpStatus.OK);
    }
}
