package Medium.DynamicTest;


/**
 * 931. 下降路径最小和
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 *
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 *
 *
 *
 * 示例：
 *
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12。
 *
 *
 *
 * 提示：
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/24  */


public class minFallingPathSum {

    public int minFallingPathSum(int[][] A) {
        int a=A.length;
        if(a==1) return A[0][0];
        int[] dp=new int[a];
        System.arraycopy(A[a - 1], 0, dp, 0, a);
        for (int i=a-2;i>=0;i--){
            int[] temp=new int[a];
            for (int j=0;j<a;j++){
                if(j==0){
                    temp[j]=Math.min(dp[j],dp[j+1])+A[i][j];
                }else if(j==a-1){
                    temp[j]=Math.min(dp[j],dp[j-1])+A[i][j];
                }else {
                    temp[j]=Math.min(Math.min(dp[j-1],dp[j]),dp[j+1])+A[i][j];
                }
            }
            dp=temp;
        }
        int min=Integer.MAX_VALUE;
        for (int i:dp) min=Math.min(min,i);
        return min;
    }

    //进行空间的优化，自底向上的解题过程直接将结果放在原数组中
    public int minFallingPathSum2(int[][] A) {
        int N = A.length;
        for (int r = N-2; r >= 0; --r) {
            for (int c = 0; c < N; ++c) {
                // best = min(A[r+1][c-1], A[r+1][c], A[r+1][c+1])
                int best = A[r+1][c];
                if (c > 0)
                    best = Math.min(best, A[r+1][c-1]);
                if (c+1 < N)
                    best = Math.min(best, A[r+1][c+1]);
                A[r][c] += best;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int x: A[0])
            ans = Math.min(ans, x);
        return ans;
    }



    public static void main(String[] args) {
        System.out.println(new minFallingPathSum().minFallingPathSum(new int[][]{{1,2},{7,8}}));
    }
}
