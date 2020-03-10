package cn.mars.leetcode;

import java.util.HashMap;

/**
 * Description：
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

 注意:
  nums 数组的总和是一定在 32 位有符号整数范围之内的。

 示例 1:

 输入: nums = [1, -1, 5, -2, 3], k = 3
 输出: 4
 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 示例 2:

 输入: nums = [-2, -1, 2, 1], k = 1
 输出: 2
 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
 进阶:
 你能使时间复杂度在 O(n) 内完成此题吗?

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/28.
 */
public class 和等于k的最长子数组长度 {

    public static void main(String[] args) {

    }

    /*
    建立累和:如果当前累积和正好等于k，那么从开头到此位置的子数组就是一个符合要求的解，但不一定是最长的子数组，使用哈希表来建立累积和和其索引之间的映射
    算法原理：sum(0,i) = sum(0, j-1) + sum(j,i)
    nums[1,-1,5,-2,3], k = 3
    sum[1,0,5,3,6]
    我们可以看到累积和的第四个数字为3，和k相同，则说明前四个数字就是符合题意的一个子数组
    nums[-2,-1,2,1], k = 1
    sum[-2,-3,-1,0]
    我们发现累积和中没有数字等于k，但是我们知道这个例子的答案是[-1, 2]，
    那么我们看累积和数组的第一和第三个数字，我们是否能看出一些规律呢，没错，第三个数字-1减去k得到第一个数字-2，这就是规律，这也是累积和求区间和的方法，
    但是由于累计和数组中可能会有重复数字，而哈希表的关键字不能相同，比如下面这个例子：
    nums: [1, 0, -1], k = -1
    sums: [1, 1, 0]
    sums: [1,0]
    我们只要保存第一个出现该累积和的位置，后面再出现直接跳过（最长的子数组肯定是左边界肯定是最早出现的）
    */
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        for(int i=0; i<nums.length; i++){
            //建立累计和
            sum += nums[i];
            if(sum == k){
                //如果累计和正好等于k,那么res等于此时的长度即（i+1),注意此时的res不一定是最大值
                res = i+1;
            }else if(map.containsKey(sum-k)){
                //此时出现sum(0,i) = sum(0, j-1) + sum(j,i),其中sum(j,i) = k
                //sum(0,i)的长度为i+1，sum(0,j-1)的长度是j(由于我们的map保存的是index所以长度需要加1)
                //举个例子
                //[-2, -1, 2, 1]， k=1
                //sum[-2,-3,-1,0]
                //i=2的时候-1-（1） =-2, map.get(sum-k) = 0,对应的index0，它的长度为 1.
                //所以sum(j,i)为k的时候，sum(j,i)的长度为i+1-（map.get(sum-k)+1)即i-map.get(sum-k)
                res = Math.max(res, i+1-(map.get(sum-k)+1));
            }
            //如果map中不包括sum,把sum放入map中
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return res;
    }
}
