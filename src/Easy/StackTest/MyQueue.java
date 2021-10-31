package Easy.StackTest;

import java.util.Stack;


/**
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 **/
/**@author 马世臣
 * @// TODO: 2020/1/11  232. 用栈实现队列*/
public class MyQueue {
    /** Initialize your data structure here. */


    /**/
    private Stack<Integer> stack1;
    public MyQueue() {
        stack1=new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int num=stack1.size();
        int[] array=new int[num];
        int i=0;
        for(Integer integer:stack1){
            array[i++]=integer;
        }
        stack1.clear();
        for(int j=1;j<num;j++){
            stack1.push(array[j]);
        }
        return array[0];
    }

    /** Get the front element. */
    public int peek() {
        return stack1.elementAt(0);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }


    /*
        private Stack<Integer> s1 = new Stack<>();
private Stack<Integer> s2 = new Stack<>();

// Push element x to the back of queue.
public void push(int x) {
    if (s1.empty())
        front = x;
    s1.push(x);
}

// Removes the element from in front of queue.
public void pop() {
    if (s2.isEmpty()) {
        while (!s1.isEmpty())
            s2.push(s1.pop());
    }
    s2.pop();
}


// Return whether the queue is empty.
public boolean empty() {
    return s1.isEmpty() && s2.isEmpty();
}


// Get the front element.
public int peek() {
    if (!s2.isEmpty()) {
        return s2.peek();
    }
    return front;
}

    */
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(3);
        queue.push(2);
        queue.push(2);
        queue.push(2);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());   // 返回 1
        System.out.println(queue.empty()); // 返回 false
    }
}
