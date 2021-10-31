package Medium.DynamicTest;


/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/14  */

public class lengthOfLIS {


    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1) return nums.length;
        int[] dp=new int[nums.length];
        dp[0]=1;
        for (int i=1;i<nums.length;i++){
            int temp=0;
            for (int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    temp=Math.max(temp,dp[j]);
                }
            }
            dp[i]=temp+1;
        }
        int max=Integer.MIN_VALUE;
        for (int i:dp) max=Math.max(max,i);
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new lengthOfLIS().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18,120,180,200}));
    }
}
