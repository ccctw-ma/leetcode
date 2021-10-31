package Medium.DynamicTest;


/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 *
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/17  */

public class findTargetSumWays {


    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length==0) return 0;
        int sum=0,length=nums.length;
        for (int i:nums) sum+=i;
        if(sum<Math.abs(S)) return 0;
        int[][] dp=new int[nums.length][2*sum+1];
        dp[0][sum+nums[0]]++;
        dp[0][sum-nums[0]]++;
        for (int j=1;j<nums.length;j++){
            for (int i=0;i<dp[0].length;i++){
                if(dp[j-1][i]>0){
                    dp[j][i+nums[j]]+=dp[j-1][i];
                    dp[j][i-nums[j]]+=dp[j-1][i];
                }
            }
        }
        return dp[length-1][sum+S];
    }

    public static void main(String[] args) {
        System.out.println(new findTargetSumWays().findTargetSumWays(new int[]{1000},-1000));
    }
}
