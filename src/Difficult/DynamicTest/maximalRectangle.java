package Difficult.DynamicTest;



/* *
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/8
 * */



public class maximalRectangle {


    public int maximalRectangle(char[][] matrix) {
        int m=matrix.length;
        if(m == 0) return 0;
        int n=matrix[0].length;
        int max=0;
        int[][][] dp=new int[m][n][2];
        // 0-> width 1->height
        if(matrix[0][0]=='1'){
            dp[0][0][0]=1;
            dp[0][0][1]=1;
            max=1;
        }
        for (int i=1;i<n;i++){
            if(matrix[0][i]=='1'){
                dp[0][i][0]=dp[0][i-1][0]+1;
                dp[0][i][1]=1;
                max=Math.max(max,dp[0][i][0]);
            }
        }
        for (int i=1;i<m;i++){
            if(matrix[i][0]=='1'){
                dp[i][0][1]=dp[i-1][0][1]+1;
                dp[i][0][0]=1;
                max=Math.max(max,dp[i][0][1]);
            }
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if(matrix[i][j]=='1'){
                    dp[i][j][0]=dp[i][j-1][0]+1;
                    dp[i][j][1]=dp[i-1][j][1]+1;
                    int width=dp[i][j][0],height=dp[i][j][1],minHeight=height;
                    for (int k=1;k<=width;k++){
                        minHeight=Math.min(minHeight,dp[i][j-k+1][1]);
                        max=Math.max(max,k*minHeight);
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
