package cn.mars.concurrent;

/**
 * Description：
 * Created by Mars on 2019/12/28.
 */
public class SynchronizedTest {

    static int i = 0;

    public static void increment(){
        i++;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            SynchronizedTest.increment();
            System.out.printf("%s %s \n", Thread.currentThread().getName(), i);
        }, "t1");
        Thread t2 = new Thread(() -> {
            SynchronizedTest.increment();
            System.out.printf("%s %s \n", Thread.currentThread().getName(), i);
        }, "t2");
        t1.start();
        t2.start();
    }
}
