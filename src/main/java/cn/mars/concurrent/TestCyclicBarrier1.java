package cn.mars.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description：
 * Created by Mars on 2019/12/24.
 */
public class TestCyclicBarrier1 {

    static class Worker implements Runnable{
        // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
        private CyclicBarrier barrier;
        private String name;

        public Worker(CyclicBarrier barrier, String name){
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try{
                System.out.println(name + " 准备好了...");
                // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
                barrier.await();
                System.out.println(name + " 走到这3！...");

            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(5,new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" 开会了，996工作要开始了！");
            }
        });

        for(int i = 0; i < 5; i++){
            new Thread(new Worker(barrier, "程序员" + i)).start();
        }
    }
}
