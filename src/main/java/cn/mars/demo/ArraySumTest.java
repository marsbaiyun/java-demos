package cn.mars.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description：
 * Created by Mars on 2018/4/25.
 */
public class ArraySumTest {

    public static AtomicInteger sum = new AtomicInteger(0);

    public static class MyThread extends Thread {

        private int i;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            sum.addAndGet(i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[10000000];
        int summary = 0;
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0;i < array.length;i ++){
            array[i] = (int)(Math.random()*1000);
        }
        System.out.println("....."+System.currentTimeMillis());
        for(int i = 0;i < array.length;i ++){
            summary += array[i];
        }
        System.out.println("sum : " + summary + "....."+System.currentTimeMillis());
        System.out.println("....."+System.currentTimeMillis());
        for(int i = 0;i < array.length;i ++){
            service.submit(new MyThread(array[i]));
        }
        System.out.println("sum : "+sum+"....."+System.currentTimeMillis());
        service.shutdown();
        System.out.println("....."+System.currentTimeMillis());

    }



}
