package cn.mars.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Ints;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Description：从一个无序且不重复的数组A中，抽取n（n小于A数组的长度）个数字组成数组B，要求B数组的数据也都不重复，要求随机且时间和空间复杂度尽可能的低。
 * Created by Mars on 2020/2/12.
 */
public class RandomArray {

    public static void main(String[] args) {
        int al = 100000;
        Set<Integer> aSet = new HashSet<>();
        Random random = new Random();
        while (aSet.size() < al) {
            int num = random.nextInt(1000000000);
            if(!aSet.contains(num)){
                aSet.add(num);
            }
        }
        int[] a = Ints.toArray(aSet);
        System.out.println(JSON.toJSONString(handle(a, 500)));
    }


    public static int[] handle(int[] a, int n) {
        int[] b = new int[n];
        int al = a.length;
        Random random = new Random();
        for(int i = 0;i < n;i ++){
            int j = random.nextInt(al);
            int num = a[j];
            b[i] = num;
            a[j] = a[al - 1];
            a[al - 1] = num;
            al--;
        }
        return b;
    }
}
