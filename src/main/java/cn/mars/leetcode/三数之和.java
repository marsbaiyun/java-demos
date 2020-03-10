package cn.mars.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description：
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

  

 示例：

 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/3sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/3/7.
 */
public class 三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if(length < 3){
            return result;
        }
        //排序
        Arrays.sort(nums);


        for(int i = 0;i < length;i ++) {
            //如果最小的数大于0，无解
            if(nums[i] > 0) break;

            //如果当前的数字，和前一位数相等，则继续下一位
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //左右指针相向移动，跳过重复的值
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left ++;
                    right --;
                }else if(sum < 0) {
                    left ++;
                }else {
                    right --;
                }
            }
        }
        return result;
    }
}
