package Medium.DynamicTest;


/**
 * 1027. 最长等差数列
 * 给定一个整数数组 A，返回 A 中最长等差子序列的长度。
 *
 * 回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * 示例 2：
 *
 * 输入：[9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * 示例 3：
 *
 * 输入：[20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 *
 *
 * 提示：
 *
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/25
 * */

public class longestArithSeqLength {


    public int longestArithSeqLength(int[] A) {
        if(A.length==2) return 2;
        int[][] dp=new int[A.length][A.length];
        for (int[] ints : dp) Arrays.fill(ints, 2);
        int max=2;
        for (int i=2;i<A.length;i++){
            for (int j=0;j<=i-2;j++){
                for (int k=i-1;k>j;k--){
                    if(A[i]+A[j]==A[k]*2){
                        dp[k][i]=Math.max(dp[k][i],dp[j][k]+1);
                        max=Math.max(max,dp[k][i]);
                    }
                }
            }
        }
        return max;
    }


    //使用指针去寻找对应差值的数据是否存在，大大节省了时间o(n2),my code need o(n3)
    public int longestArithSeqLength2(int[] A) {
        int n = A.length;
        if (n < 2) return n;
        int[][] dp = new int[n][n];
        //dp[i][j] means nums[i] nums[j] is two last elements.
        int[] index = new int[20001];
        int res = 2;
        Arrays.fill(index, -1);
        for (int i = 0; i < n - 1; i++) {
            Arrays.fill(dp[i], 2);
            for (int j = i + 1; j < n; j++) {
                int prev = A[i] * 2 - A[j];
                if (prev < 0 || index[prev] == -1) continue;
                dp[i][j] = dp[index[prev]][i] + 1;
                res = Math.max(res, dp[i][j]);
            }
            index[A[i]] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new longestArithSeqLength().longestArithSeqLength(new int[]{9,4,7,2,10}));
    }
}
