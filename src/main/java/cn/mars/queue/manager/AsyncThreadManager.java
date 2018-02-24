package cn.mars.queue.manager;

import cn.mars.queue.thread.AsyncThread;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description：
 * Created by Mars on 2017/11/6.
 */
@Component
public class AsyncThreadManager implements InitializingBean{

    private static int MAX_THREAD_COUNT = 10;
    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_COUNT);

    @Override
    public void afterPropertiesSet() throws Exception {
        AsyncThread asyncThread = new AsyncThread();
        asyncThread.setName("测试队列线程");
        executorService.submit(asyncThread);
    }
}
