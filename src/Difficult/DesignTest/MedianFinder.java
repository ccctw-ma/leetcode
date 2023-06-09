package Difficult.DesignTest;

import java.util.PriorityQueue;

/**
 * @author msc
 * @version 1.0
 * @date 2021/8/27 10:22
 */


/*
* 295. 数据流的中位数
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
进阶:

如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？*/


public class MedianFinder {

    /**
     * initialize your data structure here.
     */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private int min_size;
    private int max_size;

    public MedianFinder() {
        minHeap = new PriorityQueue<>((x, y) -> y - x);
        maxHeap = new PriorityQueue<>();
        min_size = 0;
        max_size = 0;
    }

    public void addNum(int num) {
        if (min_size == 0 && max_size == 0) {
            minHeap.add(num);
            min_size++;
            return;
        }
        int p = min_size != 0 ? minHeap.peek() : maxHeap.peek();
        if (num <= p) {
            minHeap.add(num);
            min_size++;
        } else {
            maxHeap.add(num);
            max_size++;
        }
        if (min_size - max_size > 1) {
            int t = minHeap.poll();
            maxHeap.add(t);
            min_size--;
            max_size++;
        } else if (max_size - min_size > 1) {
            int t = maxHeap.poll();
            minHeap.add(t);
            max_size--;
            min_size++;
        }
    }

    public double findMedian() {
        return min_size == max_size ? (maxHeap.peek() + minHeap.peek()) / 2.0 : min_size > max_size ? minHeap.peek() : maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        System.out.println(obj.findMedian());
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(5);
        System.out.println(obj.findMedian());
        obj.addNum(4);
        System.out.println(obj.findMedian());
        obj.addNum(9);
        System.out.println(obj.findMedian());
        obj.addNum(1);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        System.out.println(queue.peek());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */