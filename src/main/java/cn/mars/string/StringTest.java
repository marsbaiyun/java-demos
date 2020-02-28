package cn.mars.string;

/**
 * Description：
 * Created by Mars on 2020/2/27.
 */
public class StringTest {

    public static void main(String[] args) {
        String str1 = "HelloFlyapi";
        String str2 = "HelloFlyapi";
        String str3 = new String("HelloFlyapi");
        String str4 = "Hello";
        String str5 = "Flyapi";
        String str6 = "Hello" + "Flyapi";
        String str7 = str4 + str5;

        //true
        System.out.println("str1 == str2 result: " + (str1 == str2));

        //false
        System.out.println("str1 == str3 result: " + (str1 == str3));

        //true
        System.out.println("str1 == str6 result: " + (str1 == str6));

        //false
        //JVM会在堆（heap）中创建一个以str4为基础的一个StringBuilder对象，
        // 然后调用StringBuilder的append()方法完成与str5的合并，
        // 之后会调用toString()方法在堆（heap）中创建一个String对象，并把这个String对象的引用赋给str7。
        System.out.println("str1 == str7 result: " + (str1 == str7));

        //true
        System.out.println("str1 == str7.intern() result: " + (str1 == str7.intern()));

        //false
        System.out.println("str3 == str3.intern() result: " + (str3 == str3.intern()));
    }
}
