package cn.mars.leetcode;

/**
 * Description：
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 为了让您更好地理解问题，以下面的二叉搜索树为例：

   4
 /  \
 2   5
 / \
 1  3

  

 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

  head>1<>2<>3<>4<>5-
        \            \
         ------------


  

 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/27.
 */
public class 二叉搜索树与双向链表 {

    public static void main(String[] args) {
        Node node = new Node(4);
        Node left = new Node(2);
        left.left = new Node(1);
        left.right = new Node(3);
        node.right = new Node(5);
        node.left = left;
        Node result = treeToDoublyList(node);
        System.out.println(result);
    }

    public static Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Node newhead = helper(root);
        //创建头尾相连
        Node temp = newhead;
        while(temp.right != null){
            temp = temp.right;
        }
        //进行头尾相连
        temp.right = newhead;
        newhead.left = temp;
        return newhead;
    }


    public static Node helper(Node root){
        if(root == null){
            return null;
        }
        if(root.left != null){
            //找到最小的值
            Node left = helper(root.left);
            while(left.right != null) left = left.right;
            //进行小值双向赋值
            root.left = left;
            left.right = root;
        }
        if(root.right != null){
            Node right = helper(root.right);
            //进行大值双向赋值
            root.right = right;
            right.left = root;
        }
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
