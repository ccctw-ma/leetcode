package Medium.DynamicTest;


/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * 通过次数6,342提交次数18,298*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/19  */

public class findNumberOfLIS {


    public int findNumberOfLIS(int[] nums) {
        if(nums.length<=1) return  nums.length;
        int[][] dp=new int[nums.length][2];
        dp[0][0]=1;
        dp[0][1]=1;
        int count=0,sum=0;
        for (int i=1;i<nums.length;i++){
            dp[i][0]=1;
            dp[i][1]=1;
            for (int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    if(dp[i][0]<dp[j][0]+1){
                        dp[i][0]=dp[j][0]+1;
                        dp[i][1]=dp[j][1];
                    }else if(dp[i][0]==dp[j][0]+1){
                        dp[i][1]+=dp[j][1];
                    }
                }
            }
            count=Math.max(count,dp[i][0]);
        }
        for (int i=0;i<dp.length;i++){
            if(dp[i][0]==count){
                sum+=dp[i][1];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new findNumberOfLIS().findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }
}
