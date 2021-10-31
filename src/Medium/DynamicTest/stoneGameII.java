package Medium.DynamicTest;


/**
 * 1140. 石子游戏 II
 * 亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
 *
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 *
 * 游戏一直持续到所有石子都被拿走。
 *
 * 假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
 *
 *
 *
 * 示例：
 *
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：
 * 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
 * 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
 * 所以我们返回更大的 10。
 *
 *
 * 提示：
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/27  */

public class stoneGameII {


    public int stoneGameII(int[] piles) {
        int len=piles.length;
        int sum=0;
        int[][] dp=new int[len][len];
        for (int i=len-1;i>=0;i--){
            sum+=piles[i];
            for (int m=1;m<=len;m++){
                if(i+2*m>=len){
                    dp[i][m]=sum;
                    continue;
                }
                for (int j=1;i+j<=len&&j<=2*m;j++){
                    dp[i][j]=Math.max(dp[i][j],sum-dp[i+j][Math.max(m,j)]);
                }
            }
        }
        return dp[0][1];
    }



    //不简单并且很难
    private int[] sum;
    private int[][] memo;
    public int stoneGameII2(int[] piles) {
        int n = piles.length;
        // sum[i]表示[i,n-1]的石头总量
        sum = new int[n];
        sum[n-1] = piles[n-1];
        for(int i=n-2; i>=0; i--){
            sum[i] = piles[i] + sum[i+1];
        }
        //memo[i][j]表示M=j时, alex在[i,n-1]范围上能够获得的最大值
        memo = new int[n][n];
        return core(piles, 0, 1);
    }

    //核心思想就是，min = Math.min(min, core(piles, index+x, Math.max(x, M)));
    //也就是让对手拿的最少那么我拿的就是最多的
    private int core(int[] piles, int index, int M){
        //base case
        int n = piles.length;
        if(index==n){
            return 0;
        }
        if(2*M >= n-index){
            return sum[index];
        }
        if(memo[index][M]!=0){
            return memo[index][M];
        }
        int min = Integer.MAX_VALUE;
        for(int x=1; x<=2*M; x++){
            min = Math.min(min, core(piles, index+x, Math.max(x, M)));
        }
        memo[index][M] = sum[index] - min;
        return memo[index][M];
    }

    public static void main(String[] args) {

    }
}
