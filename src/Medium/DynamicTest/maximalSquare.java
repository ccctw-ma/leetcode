package Medium.DynamicTest;


/**
 * 221. 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/13  */
public class maximalSquare {


    public int maximalSquare(char[][] matrix) {
        int max=0;
        int[][] dp=new int[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++){
            if(matrix[i][0]=='1'){
                dp[i][0]=1;
                max=Math.max(max,1);
            }
        }
        for (int i=0;i<matrix[0].length;i++){
            if(matrix[0][i]=='1'){
                dp[0][i]=1;
                max=Math.max(max,1);
            }
        }
        for (int i=1;i<matrix.length;i++){
            for (int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    if(dp[i-1][j-1]==dp[i][j-1]&&dp[i-1][j-1]==dp[i-1][j]){
                        dp[i][j]= (int) Math.pow(Math.sqrt(dp[i-1][j-1])+1,2);
                    }else if(dp[i-1][j-1]>0&&dp[i-1][j]>0&&dp[i][j-1]>0){
                        int min=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                        dp[i][j]=(int)Math.pow(Math.sqrt(min)+1,2);
                    }else{
                        dp[i][j]=1;
                    }
                    max=Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }

    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }


    public static void main(String[] args) {

    }
}
