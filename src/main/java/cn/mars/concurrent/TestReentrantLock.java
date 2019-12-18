package cn.mars.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class TestReentrantLock {
    private ReentrantLock lock = new ReentrantLock();

    public void print(int str) {
        try {
            lock.lock();
            System.out.println(str + "获取");
            Thread.sleep((int)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(str + "释放");
            lock.unlock();
        }
    }
}
