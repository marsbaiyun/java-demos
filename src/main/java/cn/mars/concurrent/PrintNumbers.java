package cn.mars.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description：
 * Created by Mars on 2020/2/28.
 */
public class PrintNumbers {

    private static volatile AtomicInteger count = new AtomicInteger(1);
    private static volatile int signal = 1;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintNumbers.Worker(1, 2), "Thread-A");
        Thread thread2 = new Thread(new PrintNumbers.Worker(2, 1), "Thread-B");
        thread1.start();
        thread2.start();
    }

    static class Worker implements Runnable {
        private int num;
        private int next;

        public Worker(int num, int next) {
            this.num = num;
            this.next = next;
        }

        @Override
        public void run() {
            while (count.intValue() <= 100) {
                if(signal == num){
                    System.out.printf("%s : %s\n", Thread.currentThread().getName(), count.intValue());
                    count.incrementAndGet();
                    signal = next;
                }
            }
        }
    }
}
