package cn.mars.demo;

import java.util.Arrays;

/**
 * Description：
 * Created by Mars on 2020/2/17.
 */
public class WidestPathOfTrees {

    public static void main(String[] args) {
//        int[] x = new int[]{1,8,7,3,4,1,8}, y = new int[]{6,4,1,8,5,1,7};
//        int[] x = new int[]{5,5,5,7,7,7}, y = new int[]{3,4,5,1,3,7};
        int[] x = new int[]{6,10,1,4,3}, y = new int[]{2,5,3,1,6};
        System.out.println(solution(x, y));
    }

    public static int solution(int[] x, int[] y) {
        Arrays.sort(x);
        int maxWidth = 0;
        for (int i = 0, j = 1;i < x.length-1; i ++,j++) {
            if(Math.abs(x[i] - x[j]) > maxWidth){
                maxWidth = Math.abs(x[i] - x[j]);
            }
        }
        return maxWidth;
    }
}
