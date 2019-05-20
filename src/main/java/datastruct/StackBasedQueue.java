package datastruct;

import java.util.Queue;
import java.util.Stack;

/**
 * @author songdexu
 * @date 2019/5/19
 */
public class StackBasedQueue {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void add(int e) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(e);
    }

    public Integer poll() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        StackBasedQueue queue = new StackBasedQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        queue.add(4);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
