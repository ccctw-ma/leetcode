package Difficult.SlindingWindowTest;

/*239. 滑动窗口最大值
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。



进阶：

你能在线性时间复杂度内解决此题吗？



示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


提示：

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/5/12  */


public class maxSlidingWindow {


    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res=new int[nums.length-k+1];
        queue queue=new queue();
        int index=0;
        for (int i=0;i<k;i++){
            queue.push(nums[i]);
        }
        res[index++]=queue.getMax();
        for (int i=k;i<nums.length;i++){
            queue.push(nums[i]);
            queue.pop(nums[i-k]);
            res[index++]=queue.getMax();
        }
        return res;
    }

    class queue{

        private int max;
        private Deque<Integer> deque;

        public queue(){
            deque=new ArrayDeque<>();
        }

        public void push(int val){
            if(deque.size()==0){
                deque.addFirst(val);
            }else {
                if(val>=deque.getFirst()){
                    deque.clear();
                    deque.addFirst(val);
                }else {
                    while (deque.getLast()<val){
                        deque.removeLast();
                    }
                    deque.addLast(val);
                }
            }
        }

        public void pop(int val){
            if(val==deque.getFirst()) deque.removeFirst();
        }

        public int getMax(){
            return deque.getFirst();
        }
    }

    public static void main(String[] args) {

    }
}
