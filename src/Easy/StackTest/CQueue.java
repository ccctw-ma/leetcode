package Easy.StackTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class CQueue {


    private Deque<Integer> deque;
    public CQueue() {
        deque=new ArrayDeque<>();
    }

    public void appendTail(int value) {
        deque.addFirst(value);
    }

    public int deleteHead() {
        if(deque.isEmpty()) return -1;
        return deque.removeLast();
    }

    LinkedList<Integer> stack1;
    LinkedList<Integer> stack2;

//    public CQueue() {
//        stack1 = new LinkedList<>();
//        stack2 = new LinkedList<>();
//    }
//
//    public void appendTail(int value) {
//        stack1.add(value);
//    }
//
//    public int deleteHead() {
//        if (stack2.isEmpty()) {
//            if (stack1.isEmpty()) return -1;
//            while (!stack1.isEmpty()) {
//                stack2.add(stack1.pop());
//            }
//            return stack2.pop();
//        } else return stack2.pop();
//    }


    public static void main(String[] args) {
        Deque<Integer> stack=new ArrayDeque<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.addFirst(4);
        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);
        stack.push(14);
        stack.pop();
        System.out.println(Arrays.toString(stack.toArray()));

    }
}
