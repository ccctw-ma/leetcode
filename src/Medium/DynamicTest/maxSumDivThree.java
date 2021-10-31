package Medium.DynamicTest;


/**
 * 1262. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 *
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/4/10  */

public class maxSumDivThree {



    //就是想不到呀，很烦
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        for (int num : nums) {
            int mod = num % 3;
            int a = dp[(3 - mod) % 3];
            int b = dp[(3 + 1 - mod) % 3];
            int c = dp[(3 + 2 - mod) % 3];
            if (a != 0 || mod == 0) dp[0] = Math.max(dp[0], a + num);
            if (b != 0 || mod == 1) dp[1] = Math.max(dp[1], b + num);
            if (c != 0 || mod == 2) dp[2] = Math.max(dp[2], c + num);
        }
        return dp[0];
    }


    public int maxSumDivThree2(int[] nums) {
        int sum = 0;
        for(int i : nums) sum += i;
        int mod = sum%3;
        if(mod == 0) return sum;

        while(mod <= sum){
            if(coinChange(nums, mod)){
                return sum - mod;
            }
            mod += 3;
        }
        return 0;
    }

    private boolean coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int coin : coins){
            for(int i = amount; i >= coin; i--){
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? false : true;
    }

    public static void main(String[] args) {
        System.out.println(new maxSumDivThree().maxSumDivThree(new int[]{1,2,3,4,4}));
    }
}
