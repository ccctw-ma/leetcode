package Medium.DynamicTest;



/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/14  */

public class maxProfit {




    public int maxProfit(int[] prices) {
        int len;
        if (prices == null || (len = prices.length) == 0)
            return 0;
        int[][] dp = new int[len][3];
        dp[0][0] = 0; // 不持股
        dp[0][1] = -prices[0]; // 持股
        dp[0][2] = 0; // 冷冻期
        for (int i = 1; i < len; i++) {
            // 不持股
            // 第i-1天不持股，第i天仍不持股
            // 第i-1天处于冷冻期，第i天就变为不持股状态，那么当前第i天的最大值就是从这两个状态中取
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            // 持股
            // 第i-1天持股，第i天仍持股
            // 第i-1天不持股，第i天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 冷冻期
            // 第i-1天持有股票且今天卖出
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        // 返回不持由股票或者处于冷冻期的最大值
        return Math.max(dp[len - 1][0], dp[len - 1][2]);
    }

    public static void main(String[] args) {

    }
}
