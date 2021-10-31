package Easy.HeapTest;


/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 马世臣
 * @// TODO: 2020/1/12  703. 数据流中的第K大元素*/
public class KthLargest {

    int size;
    private PriorityQueue<Integer> q;

    public KthLargest(int k, int[] nums) {
        size = k;
        q = new PriorityQueue(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (q.size() < size) {
            q.add(val);
        } else if (q.peek() < val) {
            q.poll();
            q.add(val);
        }
        return q.peek();
    }








    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3));// returns 4
        System.out.println(kthLargest.add(5));// returns 5
        System.out.println(kthLargest.add(10));// returns 5
        System.out.println(kthLargest.add(9));// returns 8
        System.out.println(kthLargest.add(4));// returns 8
        PriorityQueue<Integer> integers=new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        integers.offer(9);
        integers.offer(8);
        integers.offer(7);
        integers.offer(6);
        System.out.println(integers.peek());
    }

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
}
