package Medium.DynamicTest;


/**
 * 576. 出界的路径数
 * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入: m = 2, n = 2, N = 2, i = 0, j = 0
 * 输出: 6
 * 解释:
 *
 * 示例 2：
 *
 * 输入: m = 1, n = 3, N = 3, i = 0, j = 1
 * 输出: 12
 * 解释:
 *
 *
 *
 * 说明:
 *
 * 球一旦出界，就不能再被移动回网格内。
 * 网格的长度和高度在 [1,50] 的范围内。
 * N 在 [0,50] 的范围内。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/18  */

public class findPaths {


    //思路是对的，就是数据处理上出了一些问题
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][] dp = new int[m][n];
        int[] steps = new int[N+1];
        dp[i][j] = 1;
        int[] mi = {-1,1,0,0};
        int[] mj = {0,0,-1,1};
        int mod = 1000000007;
        int sum = 0;
        for(int s=1; s<=N; ++s){
            int[][] ndp = new int[m][n];
            for(int l=0; l<m; ++l){
                for(int h=0; h<n; ++h){
                    if(dp[l][h] == 0) continue;
                    for(int k=0; k<mi.length; ++k){
                        int ni = l + mi[k];
                        int nj = h + mj[k];
                        if(ni < 0 || ni >= m || nj < 0 || nj >= n){
                            steps[s] = (steps[s]+dp[l][h])%mod;
                        }else{
                            ndp[ni][nj] = (ndp[ni][nj] + dp[l][h]) % mod;
                        }
                    }
                }
            }
            dp = ndp;
            sum = (sum + steps[s]) % mod;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new findPaths().findPaths(8,50,23,5,26));
    }
}
