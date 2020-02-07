package cn.mars.demo;

import cn.mars.demo.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description：
 * Created by Mars on 2019/12/29.
 */
public class Solution {

    public static void main(String[] args) {

        String s = "abba";
        System.out.println(new Solution().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for(int i = 0; i < s.length(); i ++){
            char ss = s.charAt(i);
            if(charMap.containsKey(ss)){
                start = Math.max(charMap.get(ss), start);
            }
            max = Math.max(max, i - start + 1);
            charMap.put(s.charAt(i), i + 1);
        }
        return max;
    }

    /**
     * 440. 字典序的第K小数字
     * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
     * @param n
     * @param k
     * @return
     */
    public void findKthNumber(int n, int k) {

    }


    public void inOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.println(current.val);
            current = current.right;
        }
    }

    public void preOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.println(current.val);
            current = current.right;
        }
    }
}
