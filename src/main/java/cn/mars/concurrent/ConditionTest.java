package cn.mars.concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 * Created by Mars on 2020/2/28.
 */
public class ConditionTest {

    private static int fullSize = 10;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition provider = lock.newCondition();
    private static Condition consumer = lock.newCondition();
    private static LinkedList<Integer> queue = new LinkedList<>();


    static class Provider implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    if(queue.size() == fullSize){
                        System.out.printf("%s生产产品，剩余产品数量为10，开始等待...\n", Thread.currentThread().getName());
                        provider.await();
                    }else {
                        int i = new Random().nextInt(100);
                        queue.add(i);
                        System.out.printf("%s添加产品%s，当前产品数量：%s\n", Thread.currentThread().getName(), i, queue.size());
                        consumer.signalAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    if(queue.size() == 0){
                        System.out.printf("%s打算消费产品，剩余产品数量为0，开始等待...\n", Thread.currentThread().getName());
                        consumer.await();
                    }else {
                        Integer pop = queue.pop();
                        System.out.printf("%s消费产品%s，剩余产品数量：%s\n", Thread.currentThread().getName(), pop, queue.size());
                        provider.signalAll();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }



    public static void main(String[] args) {
        for (int i =0;i < 10;i ++)
            new Thread(new Consumer(), "Consumer"+i).start();
        for (int i =0;i < 10;i ++)
            new Thread(new Provider(), "Provider"+i).start();

    }


}
