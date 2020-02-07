package cn.mars.leetcode.tree;

import cn.mars.demo.entity.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。

    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    说明: 叶子节点是指没有子节点的节点。

    示例：
    给定二叉树 [3,9,20,null,null,15,7]，

    3
    / \
    9  20
      /  \
     15   7
    返回它的最大深度 3 。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 *
 * Description：
 * Created by Mars on 2020/1/9.
 */
public class 二叉树的最大深度 {

    /**
     * 递归实现二叉树最大深度
     * 时间复杂度O(n)
     * 空间复杂度:线性表最差O(n)、二叉树完全平衡最好O(logn)
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        //递归退出条件，到叶子节点
        if (root == null) {
            return 0;
        }
        //计算左子树最大深度
        int leftMaxDepth = maxDepth(root.left);
        //计算右子树最大深度
        int rightMaxDepth = maxDepth(root.right);
        //以某个节点为根节点的数的最大深度为max
        //max=max(leftMaxDepth,rightMaxDepth)+1
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    public static void main(String[] args) {

    }


}
