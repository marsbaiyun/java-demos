package cn.mars.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class MySemaphore extends Thread {

    Semaphore position;
    private int id;

    public MySemaphore (int i, Semaphore semaphore) {
        this.id = i;
        this.position = semaphore;
    }

    @Override
    public void run() {
        if(position.availablePermits() > 0) {
            System.out.println("顾客[" + id + "]进入厕所，有空位");
        }else {
            System.out.println("顾客[" + id + "]进入厕所，没空位，排队");
        }
        try {
            position.acquire();
            System.out.println("顾客[" + id + "]获得坑位");
            Thread.sleep((int) Math.random()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("顾客[" + id + "]使用完毕");
        position.release();
    }

    public static void main(String[] args) {
        ExecutorService list = Executors.newCachedThreadPool();
        Semaphore position = new Semaphore(2);
        for(int i = 0;i < 10;i ++){
            list.submit(new MySemaphore(i, position));
        }
        list.shutdown();
        position.acquireUninterruptibly(2);
        System.out.println("使用完毕，需要清扫了");
        position.release(2);
    }
}
