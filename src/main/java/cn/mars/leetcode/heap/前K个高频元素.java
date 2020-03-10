package cn.mars.leetcode.heap;

import java.util.*;

/**
 * Description：给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

 示例 1:

 输入: nums = [1,1,1,2,2,3], k = 2
 输出: [1,2]
 示例 2:

 输入: nums = [1], k = 1
 输出: [1]
 说明：

 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/3/6.
 */
public class 前K个高频元素 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0;i < nums.length;i ++){
            if(countMap.containsKey(nums[i])){
                countMap.put(nums[i], countMap.get(nums[i])+1);
            }else {
                countMap.put(nums[i], 1);
            }
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> countMap.get(n1) - countMap.get(n2));
        for(int n : countMap.keySet()){
            heap.add(n);
            if(heap.size() > k){
                heap.poll();
            }
        }

        while (!heap.isEmpty())
            result.add(heap.poll());
        Collections.reverse(result);
        return result;
    }
}
