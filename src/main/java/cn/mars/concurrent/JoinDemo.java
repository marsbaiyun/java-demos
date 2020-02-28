package cn.mars.concurrent;

/**
 * Description：
 * Created by Mars on 2020/2/19.
 */
public class JoinDemo {

    public static void main(String[] args) {
        JoinWorker joinWorker1 = new JoinWorker(1000, "message 1");
        JoinWorker joinWorker2 = new JoinWorker(2000, "message 2");

        System.out.printf("%s before start... \n", System.currentTimeMillis());

        joinWorker1.start();
        joinWorker2.start();

        System.out.printf("%s start... \n", System.currentTimeMillis());
        try {
            joinWorker1.join();
            System.out.printf("%s worker1 finish... \n", System.currentTimeMillis());
            joinWorker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s over... \n", System.currentTimeMillis());
    }

    static class JoinWorker extends Thread {
        private long seconds;
        private String message;


        public JoinWorker(long seconds, String message) {
            this.seconds = seconds;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s %s %s \n", System.currentTimeMillis(), Thread.currentThread().getName(), message);
        }
    }
}
