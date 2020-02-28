package cn.mars.leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description：
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。

 若队列为空，pop_front 和 max_value 需要返回 -1

 示例 1：

 输入:
 ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 [[],[1],[2],[],[],[]]
 输出: [null,null,null,2,1,2]
 示例 2：

 输入:
 ["MaxQueue","pop_front","max_value"]
 [[],[],[]]
 输出: [null,-1,-1]
  

 限制：

 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 1 <= value <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/2/26.
 */
public class 队列的最大值 {


    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(5);
        System.out.printf("%s\n", maxQueue.max_value());
        maxQueue.push_back(2);
        System.out.printf("%s\n", maxQueue.max_value());
        maxQueue.push_back(3);
        System.out.printf("%s\n", maxQueue.max_value());
    }

    static class MaxQueue {
        private LinkedList<Integer> data;
        private LinkedList<Integer> maxQue;
        public MaxQueue() {
            data = new LinkedList<>();
            maxQue = new LinkedList<>();
            maxQue.addLast(Integer.MIN_VALUE);
        }

        public int max_value() {
            if(data.isEmpty()){
                return -1;
            }
            return maxQue.peekFirst();
        }

        public void push_back(int value) {
            data.addLast(value);
            while(!maxQue.isEmpty() && maxQue.peekLast() < value){
                maxQue.removeLast();
            }
            maxQue.addLast(value);
        }

        public int pop_front() {
            if(data.isEmpty()) return -1;
            int front = data.removeFirst();
            if(maxQue.peekFirst() == front){
                maxQue.removeFirst();
            }
            return front;
        }
    }
}
