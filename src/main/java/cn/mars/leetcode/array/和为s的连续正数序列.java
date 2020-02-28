package cn.mars.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * Description：
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

    示例 1：

    输入：target = 9
    输出：[[2,3,4],[4,5]]
    示例 2：

    输入：target = 15
    输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     

    限制：

    1 <= target <= 10^5

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/24.
 */
public class 和为s的连续正数序列 {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(findContinuousSequence(9)));
    }

    public static int[][] findContinuousSequence(int target) {
        int[][] result = null;
        if(target == 1 || target == 2){
            result = new int[1][];
            result[0] = new int[1];
            return result;
        }
        result = new int[10][];
        int idx = 0;
        int small = 1;
        int big = small+1;
        int sum = small;
        while (sum < target) {
            if(sum + big > target){
                if(small > target/2){
                    break;
                }
                small ++;
                sum = small;
                big = small + 1;
            }else if (sum + big < target) {
                sum += big;
                big ++;
            }else {
                int[] nums = new int[big - small + 1];
                for (int i = 0; i <= big - small;i ++) {
                    nums[i] = small + i;
                }
                small ++;
                big = small+1;
                sum = small;
                if(idx == result.length - 1){
                    result = Arrays.copyOf(result, idx*2);
                }
                result[idx] = nums;
                idx++;
            }
        }
        return Arrays.copyOf(result, idx);
    }
}
