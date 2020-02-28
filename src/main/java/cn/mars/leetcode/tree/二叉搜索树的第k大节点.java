package cn.mars.leetcode.tree;

import cn.mars.demo.entity.TreeNode;

import java.util.LinkedList;

/**
 * Description：给定一棵二叉搜索树，请找出其中第k大的节点。

      

     示例 1:

     输入: root = [3,1,4,null,2], k = 1
     3
     / \
     1   4
     \
        2
     输出: 4
     示例 2:

     输入: root = [5,3,6,2,4,null,null,1], k = 3
     5
     / \
     3   6
     / \
     2   4
     /
     1
     输出: 4
      

     限制：

     1 ≤ k ≤ 二叉搜索树元素个数

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/19.
 */
public class 二叉搜索树的第k大节点 {

    static int count = 0;
    static int res = 0;

    public static void main(String[] args) {

    }

    public static int kthLargest(TreeNode root, int k) {
        if(root == null || count >= k) return res;
        //进行后序遍历
        kthLargest(root.right,k);
        count ++;
        res = count==k?root.val:res;
        kthLargest(root.left,k);
        return res;
    }
}
