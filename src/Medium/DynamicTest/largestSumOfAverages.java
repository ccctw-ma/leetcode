package Medium.DynamicTest;


/**
 * 813. 最大平均值和的分组
 * 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。
 *
 * 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
 *
 * 示例:
 * 输入:
 * A = [9,1,2,3,9]
 * K = 3
 * 输出: 20
 * 解释:
 * A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * 我们也可以把 A 分成[9, 1], [2], [3, 9].
 * 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 * 说明:
 *
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * 答案误差在 10^-6 内被视为是正确的。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/21  */

public class largestSumOfAverages {


    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[] P = new double[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + A[i];

        double[] dp = new double[N];
        for (int i = 0; i < N; ++i)
            dp[i] = (P[N] - P[i]) / (N - i);

        for (int k = 0; k < K-1; ++k)
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j)
                    dp[i] = Math.max(dp[i], (P[j]-P[i]) / (j-i) + dp[j]);

        return dp[0];
    }

    public double largestSumOfAverages2(int[] A, int K) {
        int n = A.length;
        /**
         * 这个最优关系是很重要
         dp[i][k]表示前i个数构成k个子数组时的最大平均值, 那么对于所有 0 <= j <= i-1
         dp[i][k] = Math.max(dp[i][k], dp[j][k-1] + (A[j+1] + ... + A[i+1]) / (i-j))
         **/
        double[][] dp = new double[n + 1][K + 1];
        double[] sum = new double[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + A[i - 1];
            dp[i][1] = sum[i] / i;
        }
        for(int i = 1; i <= n; i++){
            for(int k = 2; k <= K; k++){
                //由于要求每组非空，所以从k - 1开始
                for(int j = k - 1; j < i; j++){
                    dp[i][k] = Math.max(dp[i][k],dp[j][k - 1] + (sum[i] - sum[j]) / (i - j));
                }
            }
        }
        return dp[n][K];
    }


    public static void main(String[] args) {

    }
}
