package cn.mars.gaode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description：10个线程抢100张票
 * Created by Mars on 2020/2/6.
 */
public class BuyTicket {

    static class Worker implements Runnable {

        private volatile AtomicInteger nums;

        public Worker(AtomicInteger nums) {
            this.nums = nums;
        }

        @Override
        public void run() {
            while (nums.intValue() > 0) {
                int i = nums.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + "抢到了" + i);
            }
        }
    }

    public static void main(String[] args) {
        AtomicInteger nums = new AtomicInteger(100);
        for (int i = 0;i < 10;i ++) {
            Worker worker = new Worker(nums);
            Thread thread = new Thread(worker, "Thread-"+i);
            thread.start();
        }
    }
}
