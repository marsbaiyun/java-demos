package cn.mars.leetcode.array;

import cn.mars.demo.entity.ListNode;

import java.util.PriorityQueue;

/**
 * Description：
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

 示例:

 输入:
 [
   1->4->5,
   1->3->4,
   2->6
 ]
 输出: 1->1->2->3->4->4->5->6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/3/6.
 */
public class 合并K个排序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, (n1, n2) -> n1.val - n2.val);
        for(ListNode node : lists){
            if(node == null){
                continue;
            }
            heap.add(node);
        }
        if(heap.isEmpty()){
            return null;
        }
        ListNode pre = new ListNode(-1);
        ListNode tmp = pre;
        while (!heap.isEmpty()) {
            tmp.next = heap.poll();
            tmp = tmp.next;
            if(tmp.next != null){
                heap.add(tmp.next);
            }
        }
        return pre.next;
    }
}
