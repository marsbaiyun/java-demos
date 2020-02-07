package cn.mars.leetcode.tree;

import cn.mars.demo.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。

  示例:

 输入: [1,null,2,3]
 1
  \
   2
  /
 3

 输出: [1,2,3]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Description：
 * Created by Mars on 2020/1/9.
 */
public class 二叉树的前序遍历 {

    /**
     * 从根节点开始，每次迭代弹出当前栈顶元素，并将其孩子节点压入栈中，先压右孩子再压左孩子。
     * 在这个算法中，输出到最终结果的顺序按照 Top->Bottom 和 Left->Right，符合前序遍历的顺序。
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        if(root == null){
            return result;
        }
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            result.add(cur.val);
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
