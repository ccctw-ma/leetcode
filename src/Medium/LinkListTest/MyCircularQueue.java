package Medium.LinkListTest;

public class MyCircularQueue {


    private int[] arr;
    private int rear;
    private int front;
    private int size;
    private boolean tag;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        size=k;
        arr=new int[k];
        front=0;
        rear=0;
        tag=false;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }else {
            arr[rear]=value;
            rear=(rear+1)%size;
            tag=true;
            return true;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        front=(front+1)%size;
        tag=false;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty()?-1:arr[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty()?-1:arr[(rear-1+size)%size];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return rear==front&&!tag;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull()  {
        return rear==front&&tag;
    }

    public static void main(String[] args) {

    }
}
