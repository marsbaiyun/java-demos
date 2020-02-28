package cn.mars.demo;

import cn.mars.demo.entity.TreeNode;

import java.util.*;

/**
 * 前序遍历：根结点-左孩子-右孩子
 * 中序遍历：左孩子-根结点-右孩子
 * 后序遍历：左孩子-右孩子-根结点
 *
 *
 * Description：
 * Created by Mars on 2019/12/25.
 */
public class TreeTraverse {

    public static void visit(TreeNode node){
        System.out.print(node.val+" ");
    }

    /**
     * 递归先序遍历
     * */
    public static void preOrderRecursion(TreeNode node){
        if(node==null) //如果结点为空则返回
            return;
        visit(node);//访问根节点
        preOrderRecursion(node.left);//访问左孩子
        preOrderRecursion(node.right);//访问右孩子
    }


    /**
     * 非递归先序遍历二叉树
     * */
    public static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> resultList=new ArrayList<>();
        Stack<TreeNode> treeStack=new Stack<>();
        if(root==null) //如果为空树则返回
            return resultList;
        treeStack.push(root);
        while(!treeStack.isEmpty()){
            TreeNode tempNode=treeStack.pop();
            if(tempNode!=null){
                resultList.add(tempNode.val);//访问根节点
                treeStack.push(tempNode.right); //入栈右孩子
                treeStack.push(tempNode.left);//入栈左孩子
            }
        }
        return resultList;
    }


    /**
     * 递归中序遍历
     * */
    public void inOrderRecursion(TreeNode node){
        if(node==null) //如果结点为空则返回
            return;
        inOrderRecursion(node.left);//访问左孩子
        visit(node);//访问根节点
        inOrderRecursion(node.right);//访问右孩子
    }

    /**
     * 非递归中序遍历
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(cur!=null || !stack.empty()){
            while(cur!=null){
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }

        return list;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public static ArrayList postOrder(TreeNode root){
        ArrayList alist = new ArrayList();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return alist;
        TreeNode cur,pre = null;
        stack.push(root);
        while(!stack.empty()){
            cur = stack.peek();
            if((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))){
                TreeNode temp = stack.pop();
                alist.add(temp.val);
                pre = temp;
            }
            else{
                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            }
        }
        return alist;
    }

    private static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null)
            return;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
    }

    public static List<List<Integer>> levelOrderWithLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrder(root, 0, result);
        return result;
    }

    public static void levelOrder(TreeNode root, int level, List<List<Integer>> result){
        if(root == null){
            return;
        }
        if(result.size() == level){
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(root.val);
        if(root.left != null){
            levelOrder(root.left, level + 1, result);
        }
        if(root.right != null){
            levelOrder(root.right, level + 1, result);
        }
    }

    public static void main(String[] args) {

    }
}
