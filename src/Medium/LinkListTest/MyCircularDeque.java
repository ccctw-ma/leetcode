package Medium.LinkListTest;


/*
* 641. 设计循环双端队列
设计实现双端队列。
你的实现需要支持以下操作：

MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了。
示例：

MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4



提示：

所有值的范围为 [1, 1000]
操作次数的范围为 [1, 1000]
请不要使用内置的双端队列库。*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/24  */


public class MyCircularDeque {

    /** Initialize your data structure here. Set the size of the deque to be k. */

    private int[] arr;
    private int front;
    private int rear;
    private int MaxSize;
    public MyCircularDeque(int k) {
        arr=new int[k+1];
        front=0;
        rear=0;
        MaxSize=k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()) return false;
        front=(front-1+MaxSize)%MaxSize;
        arr[front]=value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()) return false;
        arr[rear]=value;
        rear=(rear+1)%MaxSize;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) return false;
        front=(front+1)%MaxSize;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) return false;
        rear=(rear-1+MaxSize)%MaxSize;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty()?-1:arr[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty()?-1:arr[(rear-1+MaxSize)%MaxSize];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return rear==front;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return front==(rear+1)%MaxSize;
    }

    public static void main(String[] args) {
        int a=0;
        System.out.println(Math.pow(a,1));
    }
}
