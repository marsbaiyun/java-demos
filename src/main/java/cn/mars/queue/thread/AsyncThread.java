package cn.mars.queue.thread;

import cn.mars.queue.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Description：
 * Created by Mars on 2017/11/6.
 */
public class AsyncThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncThread.class);

    private static int MAX_TASK_COUNT = 10000;
    private static LinkedBlockingDeque<Task> blockingDeque = new LinkedBlockingDeque<>(MAX_TASK_COUNT);

    @Override
    public void run() {
        Task task = null;
        LOGGER.info("AsyncThread队列任务开始处理......");
        try {
            while ((task = blockingDeque.take()) != null){
                System.out.println(task.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("AsyncThread队列任务处理结束......");
    }

    public static void addTask(Task task){
        if(!blockingDeque.offer(task)){
            LOGGER.error("任务队列已满");
        }
    }
}
