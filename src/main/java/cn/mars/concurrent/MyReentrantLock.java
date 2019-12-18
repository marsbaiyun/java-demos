package cn.mars.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class MyReentrantLock extends Thread {
    TestReentrantLock lock;
    private int id;

    public MyReentrantLock(int i, TestReentrantLock test) {
        this.id = i;
        this.lock = test;
    }

    @Override
    public void run() {
        lock.print(id);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        TestReentrantLock lock = new TestReentrantLock();
        for(int i = 0;i < 10;i ++){
            executorService.submit(new MyReentrantLock(i, lock));
        }
        executorService.shutdown();
    }
}
