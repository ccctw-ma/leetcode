package Medium.SlidingWindow;


/*
*
* 1438. 绝对差不超过限制的最长连续子数组
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。

如果不存在满足条件的子数组，则返回 0 。



示例 1：

输入：nums = [8,2,4,7], limit = 4
输出：2
解释：所有子数组如下：
[8] 最大绝对差 |8-8| = 0 <= 4.
[8,2] 最大绝对差 |8-2| = 6 > 4.
[8,2,4] 最大绝对差 |8-2| = 6 > 4.
[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
[2] 最大绝对差 |2-2| = 0 <= 4.
[2,4] 最大绝对差 |2-4| = 2 <= 4.
[2,4,7] 最大绝对差 |2-7| = 5 > 4.
[4] 最大绝对差 |4-4| = 0 <= 4.
[4,7] 最大绝对差 |4-7| = 3 <= 4.
[7] 最大绝对差 |7-7| = 0 <= 4.
因此，满足题意的最长子数组的长度为 2 。
示例 2：

输入：nums = [10,1,2,4,7,2], limit = 5
输出：4
解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
示例 3：

输入：nums = [4,2,2,2,4,4,2,2], limit = 0
输出：3


提示：

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author 马世臣
 * @// TODO: 2021/2/21
 * */


public class longestSubarray {


    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        if(len==1||limit==0) return 0;
        int l=0,r=0;
        int res = 0;
        Struct struct = new Struct(limit);
        while (r<len){
            int t = nums[r++];
            if(struct.add(t)){
                res = Math.max(res,r-l);
            }else {
                while (!struct.delete(nums[l++]));
            }

        }
        return res;
    }

    class Struct{

        PriorityQueue<Integer> big;
        PriorityQueue<Integer> small;
        int limit;
        public Struct(int limit){
            this.limit = limit;
            big = new PriorityQueue<>((o1, o2) -> o2-o1);
            small = new PriorityQueue<>();
        }

        public boolean add(int x){
            big.add(x);
            small.add(x);
            return big.peek() - small.peek() <= limit;
        }

        public boolean delete(int x){
            big.remove(x);
            small.remove(x);
            return big.peek() - small.peek() <= limit;
        }
    }

    public int longestSubarray3(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    public int longestSubarray4(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<>();
        Deque<Integer> queMin = new LinkedList<>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;

    }


    public static void main(String[] args) {
        System.out.println(new longestSubarray().longestSubarray(new int[]{8,2,4,7},4));
    }
}
