package cn.mars.leetcode;

import java.util.Arrays;

/**
 * Description：
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

  

 示例 1：

 输入：arr = [3,2,1], k = 2
 输出：[1,2] 或者 [2,1]
 示例 2：

 输入：arr = [0,1,2,1], k = 1
 输出：[0]
  

 限制：

 0 <= k <= arr.length <= 10000
 0 <= arr[i] <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/28.
 */
public class 最小的k个数 {

    public static void main(String[] args) {

    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = Arrays.copyOf(arr, k);
        if(k == 0){
            return result;
        }
        Arrays.sort(result);
        for(int i = k;i < arr.length;i ++){
            if(arr[i] >= result[k-1]){
                continue;
            }
            result[k-1] = arr[i];
            Arrays.sort(result);
        }
        return result;
    }
}
