package cn.mars.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);
        final ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0;i < 10;i ++){
            final int NO = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        Thread.sleep((int)Math.random()*1000);
                        System.out.println("NO."+NO+" arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        System.out.println("Game Start");
        begin.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Game Over");
        service.shutdown();
    }
}
