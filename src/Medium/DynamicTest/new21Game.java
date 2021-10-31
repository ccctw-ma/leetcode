package Medium.DynamicTest;


/**
 * 837. 新21点
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 *
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 *
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * 示例 1：
 *
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 *
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 *
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 * 提示：
 *
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/22  */

public class new21Game {


    //得提升自己的阅读理解能力了，题都看不懂怎么来做题
//    核心递推式为 f(x) = (\frac{1}{W}) * (f(x+1) + f(x+2) + ... + f(x+W))f(x)=(
//W
//1
// )∗(f(x+1)+f(x+2)+...+f(x+W))，这是因为我们可以等概率 \frac{1}{W}
//W
//1
//  的获得从 11 到 WW 的卡片。
//事实上，相邻结果差分后，f(x) - f(x-1) = \frac{1}{W} \big( f(x+W) - f(x) \big)f(x)−f(x−1)=
//W
//1
// (f(x+W)−f(x)) 这就能够让我们在 O(1)O(1) 的时间内计算出 f(k)f(k)，通过维护分子 S = f(x+1) + f(x+2) + \cdots + f(x+W)S=f(x+1)+f(x+2)+⋯+f(x+W)。
//我们每次计算 dp[k] = S / W，同时维护分子的准确值 S \Rightarrow S + f(k) - f(k+W)S⇒S+f(k)−f(k+W)。
//


    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N + W + 1];
        // dp[x] = the answer when Alice has x points
        for (int k = K; k <= N; ++k)
            dp[k] = 1.0;

        double S = Math.min(N - K + 1, W);
        // S = dp[k+1] + dp[k+2] + ... + dp[k+W]
        for (int k = K - 1; k >= 0; --k) {
            dp[k] = S / W;
            S += dp[k] - dp[k + W];
        }
        return dp[0];

    }

    public static void main(String[] args) {
        System.out.println(new new21Game().new21Game(17,17,10));
    }
}
