package cn.mars.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组 nums，将该数组升序排列。

  

 示例 1：

 输入：[5,2,3,1]
 输出：[1,2,3,5]
 示例 2：

 输入：[5,1,1,2,0,0]
 输出：[0,0,1,1,2,5]
  

 提示：

 1 <= A.length <= 10000
 -50000 <= A[i] <= 50000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sort-an-array

 * Description：
 * Created by Mars on 2020/1/9.
 */
public class 排序数组 {

    /**
     * 时间复杂度：O(N*N)
     * @param nums
     * @return
     */
    public List<Integer> 冒泡排序(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int tmp = 0;
        for(int i = 0;i < nums.length;i ++){
            for(int j = i+1;j < nums.length;j ++){
                if(nums[i] > nums[j]){
                    tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            result.add(nums[i]);
        }
        return result;
    }

    public int[] 快速排序(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }


    public void sort(int[] nums, int s, int e){
        int left = s, right = e;
        if(left < right){
            int tmp = nums[left];
            while (left < right) {
                while (left < right && nums[right] >= tmp) {
                    right --;
                }
                if(left < right){

                }
            }



        }
    }








    public void quickSort(int[] nums, int s, int e){
        int l = s, r = e;
        if(l < r){
            int temp = nums[l];
            while(l < r){
                while(l < r && nums[r] >= temp) r--;
                if(l < r) nums[l] = nums[r];
                while(l < r && nums[l] < temp) l++;
                if(l < r) nums[r] = nums[l];
            }
            nums[l] = temp;
            quickSort(nums,s,l);
            quickSort(nums,l + 1, e);
        }
    }

}
