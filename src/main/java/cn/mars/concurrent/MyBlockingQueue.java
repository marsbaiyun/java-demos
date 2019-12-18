package cn.mars.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class MyBlockingQueue extends Thread {

    public static BlockingDeque<String> queue = new LinkedBlockingDeque<>(3);
    private int index;

    public MyBlockingQueue(int i) {
        this.index = i;
    }

    @Override
    public void run() {
        try {
            queue.put(String.valueOf(this.index));
            System.out.println("{" + this.index + "} in queue!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 10;i ++) {
            service.submit(new MyBlockingQueue(i));
        }
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((int)Math.random()*1000);
                        if(MyBlockingQueue.queue.isEmpty())break;
                        String str = MyBlockingQueue.queue.take();
                        System.out.println(str + " has take!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        service.submit(thread);
        service.shutdown();
    }
}
