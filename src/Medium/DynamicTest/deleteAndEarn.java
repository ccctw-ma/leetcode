package Medium.DynamicTest;



/**
 * 740. 删除与获得点数
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 示例 1:
 *
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 * 示例 2:
 *
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 注意:
 *
 * nums的长度最大为20000。
 * 每个整数nums[i]的大小都在[1, 10000]范围内。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/20  */

public class deleteAndEarn {


    public int deleteAndEarn(int[] nums) {
        int[] counts=new int[10001];
        for (int i:nums){
            counts[i]+=i;
        }
        int[] dp=new int[10001];
        dp[0]=0;
        dp[1]=counts[1];
        for (int i=2;i<counts.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+counts[i]);
        }
        return dp[counts.length-1];
    }

    public static void main(String[] args) {

    }
}
