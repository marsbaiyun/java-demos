package cn.mars.demo;

import com.alibaba.fastjson.JSON;

/**
 * Description：
 * Created by Mars on 2019/12/23.
 */
public class CombineSortArray {

    public int[] combine(int[] a, int[] b) {
        if(a.length == 0){
            return b;
        }
        if(b.length == 0){
            return a;
        }

        int[] c = new int[a.length+b.length];
        int i = 0;
        int j = 0;
        while (true) {
            if(a[i] < b[j]){
                c[i+j] = a[i];
                i++;
            }else if(a[i] == b[j]){
                c[i+j] = a[i];
                c[i+j+1] = b[j];
                i++;
                j++;
            }else {
                c[i+j] = b[j];
                j++;
            }
            if(i == a.length){
                i--;
                break;
            }else if(j == b.length){
                j--;
                break;
            }
        }
        return c;
    }

    private void combin(int[] c, int[] o, int tmp){

    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,7};
        int[] b = new int[]{3,5,6,8};
        int[] c = new CombineSortArray().combine(a, b);
        System.out.println(JSON.toJSONString(c));
    }
}
