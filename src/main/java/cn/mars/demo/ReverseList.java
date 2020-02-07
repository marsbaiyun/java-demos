package cn.mars.demo;

import cn.mars.demo.entity.ListNode;

/**
 * Description：
 * Created by Mars on 2019/12/25.
 */
public class ReverseList {

    public static void reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    }

    public static void main(String[] args) {

    }
}
