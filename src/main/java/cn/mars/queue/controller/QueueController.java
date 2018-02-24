package cn.mars.queue.controller;

import cn.mars.queue.task.Task;
import cn.mars.queue.thread.AsyncThread;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 * Created by Mars on 2017/11/7.
 */
@RestController
@RequestMapping("queue")
public class QueueController {

    @RequestMapping("add")
    public ResponseEntity add(int taskSize){
        for(int i = 0;i < taskSize;i ++){
            AsyncThread.addTask(new Task(i));
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
