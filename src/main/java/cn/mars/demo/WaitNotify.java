package cn.mars.demo;

/**
 * Description：
 * Created by Mars on 2020/2/19.
 */
public class WaitNotify {

    public static volatile int num = 1;

    public static void main(String[] args) {
        Object lock = new Object();
        for (int i = 0;i < 2; i++)
            new Worker(i, lock).start();
    }

    static class Worker extends Thread {
        private int n;
        private Object lock;

        public Worker(int n, Object lock) {
            this.n = n;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                if(n % 2 == num){
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    num --;
                    lock.notifyAll();
                }else {
                    try {
                        System.out.println(Thread.currentThread().getName() + "开始等待...");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + "被唤醒..." + "，num为：" + n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
