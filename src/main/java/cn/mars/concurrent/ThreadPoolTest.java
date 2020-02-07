package cn.mars.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description：
 * Created by Mars on 2019/12/28.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(6), new ThreadPoolExecutor.AbortPolicy()
                );

        for (int i = 0;i < 10;i ++) {
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "running...");
                System.out.printf("activeCount: %s \n", threadPool.getActiveCount());
                System.out.printf("activeCount: %s \n", threadPool.getQueue());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t"+i);
        }
        threadPool.shutdown();
    }

}
