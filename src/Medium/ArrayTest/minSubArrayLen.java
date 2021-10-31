package Medium.ArrayTest;


/*
* 209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。

示例:

输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
进阶:

如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
* */


import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/6/28  */



public class minSubArrayLen {


    public int minSubArrayLen(int s, int[] nums) {
        int[] pre=new int[nums.length+1];
        for (int i=0;i<nums.length;i++){
            pre[i+1]=pre[i]+nums[i];
        }

        int min=nums.length;

        for (int i=1;i<pre.length;i++){
            if(pre[i]<s) continue;
            int left=0;
            int right=i-1;

            System.out.println(Arrays.binarySearch(pre,pre[i]-s));
            while (left<right){
                int mid=left+(right-left)/2;
                int diff=pre[i]-pre[mid];
                if(diff>s){
                    left=mid+1;
                }else if(diff==s) {
                    left=mid;
                    break;
                }else {
                    right=mid-1;
                }
            }
            if(pre[i]-pre[left]<s) left--;
            min=Math.min(min,i-left);
        }
        return min;
    }


    //滑动窗口
    public int minSubArrayLen2(int s, int[] nums) {
        if(nums == null || nums.length ==0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int index = 0, sum = 0;
        for(int i=0;i<nums.length;++i){
            sum += nums[i];
            while(sum >= s){
                if(i + 1 - index < min){
                    min = i+1-index;
                }
                sum -= nums[index];
                ++index;
            }
        }
        if(min == Integer.MAX_VALUE){
            return 0;
        }
        return min;
    }

    public static void main(String[] args) {

        int[] arr=new int[]{1,2,3,4,6,8,9};
        System.out.println(Arrays.binarySearch(arr,2));
//        System.out.println(new minSubArrayLen().minSubArrayLen(11,new int[]{1,2,3,4,5}));
    }
}
