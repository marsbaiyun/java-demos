package cn.mars.concurrent;

/**
 *  Thread类中有一个threadLocals和inheritableThreadLocals都是ThreadLocalMap类型的变量，
 *  而ThreadLocalMap是一个定制化的Hashmap，默认每个线程中这个两个变量都为null，
 *  只有当前线程第一次调用了ThreadLocal的set或者get方法时候才会进行创建。
 *
 *  ThreadLocal 并不解决线程间共享数据的问题
 *  ThreadLocal 通过隐式的在不同线程内创建独立实例副本避免了实例线程安全的问题
 *  每个线程持有一个 Map 并维护了 ThreadLocal 对象与具体实例的映射，该 Map 由于只被持有它的线程访问，故不存在线程安全以及锁的问题
 *  ThreadLocalMap 的 Entry 对 ThreadLocal 的引用为弱引用，避免了 ThreadLocal 对象无法被回收的问题
 *  ThreadLocalMap 的 set 方法通过调用 replaceStaleEntry 方法回收键为 null 的 Entry 对象的值（即为具体实例）以及 Entry 对象本身从而防止内存泄漏
 *  ThreadLocal 适用于变量在线程间隔离且在方法间共享的场景
 *
 * Description：
 * Created by Mars on 2019/12/24.
 */
public class TestThreadLocal {

    //(1)打印函数
    static void print(String str){
        //1.1  打印当前线程本地内存中localVariable变量的值
        System.out.println(str + ":" +localVariable.get());
        //1.2 清除当前线程本地内存中localVariable变量
        //localVariable.remove();
    }
    //(2) 创建ThreadLocal变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();


    public static void main(String[] args) {
        //(3) 创建线程one
        Thread threadOne = new Thread(new  Runnable() {
            public void run() {
                //3.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadOne local variable");
                //3.2 调用打印函数
                print("threadOne");
                //3.3打印本地变量值
                System.out.println("threadOne remove after" + ":" +localVariable.get());

            }
        });
        //(4) 创建线程two
        Thread threadTwo = new Thread(new  Runnable() {
            public void run() {
                //4.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadTwo local variable");
                //4.2 调用打印函数
                print("threadTwo");
                //4.3打印本地变量值
                System.out.println("threadTwo remove after" + ":" +localVariable.get());

            }
        });
        //(5)启动线程
        threadOne.start();
        threadTwo.start();
    }
}
