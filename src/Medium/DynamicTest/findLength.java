package Medium.DynamicTest;


/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/20  */

public class findLength {


    public int findLength(int[] A, int[] B) {
        int a=A.length,b=B.length,max=0;
        int[][] dp=new int[a+1][b+1];
        for (int i=a-1;i>=0;i--){
            for (int j=b-1;j>=0;j--){
                if(A[i]==B[j]){
                    dp[i][j]=dp[i+1][j+1]+1;
                    max=Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }



    //可以只用一个数组，进行空间优化
    public int findLength2(int[] A, int[] B) {
        int[] dp = new int[B.length + 1];
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    dp[j + 1] = dp[j] + 1;
                    if (res < dp[j + 1]) {
                        res = dp[j + 1];
                    }
                } else {
                    dp[j + 1] = 0;
                }
            }

        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new findLength().findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
    }
}
