package cn.mars.concurrent;

import java.util.HashMap;

/**
 * Description：
 * Created by Mars on 2019/12/28.
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer,String> map = new HashMap<Integer,String>(2, 0.75f);

    public static void main(String[] args) {
//        map.put(5, "C");
//
//        new Thread("Thread1") {
//            public void run() {
//                map.put(7, "B");
//                System.out.println(map);
//            };
//        }.start();
//        new Thread("Thread2") {
//            public void run() {
//                map.put(3, "A");
//                        System.out.println(map);
//            };
//        }.start();

        System.out.println(tableSizeFor(4));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 32) ? 32 : n + 1;
    }
}
