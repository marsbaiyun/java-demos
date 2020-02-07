package cn.mars.leetcode.tree;

import cn.mars.demo.entity.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 后序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [3,2,1]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * Description：
 * Created by Mars on 2020/1/9.
 */
public class 二叉树的后序遍历 {

    /**
     * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
     * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
     * 如果遇到的节点为灰色，则将节点的值输出。
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Set<TreeNode> nodeSet = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if(!nodeSet.contains(pop)){
                stack.push(pop);
                nodeSet.add(pop);
                if(pop.right != null){
                    stack.push(pop.right);
                }
                if(pop.left != null){
                    stack.push(pop.left);
                }
            }else {
                result.add(pop.val);
            }
        }
        return result;
    }
}
