package Medium.DynamicTest;



/**
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 *
 *
 * 示例 1：
 *
 * 输入: [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释:
 * 最长的斐波那契式子序列为：[1,2,3,5,8] 。
 * 示例 2：
 *
 * 输入: [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释:
 * 最长的斐波那契式子序列有：
 * [1,11,12]，[3,11,14] 以及 [7,11,18] 。
 *
 *
 * 提示：
 *
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/3/22  */

public class lenLongestFibSubseq {


    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        Map<Integer, Integer> longest = new HashMap<>();
        int ans = 0;

        for (int k = 0; k < N; ++k)
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // Encoding tuple (i, j) as integer (i * N + j)
                    //因为不同的斐波那契数列的差值不同，所以使用i*N+j就可以确保有不同的状态
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }

        return ans >= 3 ? ans : 0;
    }

    //双指针来寻找最佳答案，用dp[i,j]来表示A[i]->A[j]所能承载最大长度，很强
    public int lenLongestFibSubseq2(int[] A) {
        int n = A.length;
        int res = 0, sum;
        int[][] dp = new int[n][n];
        //dp[i][j]表示以A[i]、A[j]结尾的子序列的最大斐波那契数列长度
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 2);
        }
        int l = 0, r = 0;
        for(int i  = 1; i < n; i++) {
            l = 0;
            r = i - 1;
            while(l < r) {
                sum = A[l] + A[r];
                if(sum == A[i]) {
                    dp[r][i] = Math.max(dp[r][i], dp[l][r] + 1);
                    res = Math.max(dp[r][i], res);
                    l++;
                    r--;
                }else if(sum < A[i]) {
                    l++;
                }else {
                    r--;
                }
            }
        }
        return res;
    }



    //int max=1;
    //        int[][] dp=new int[A[A.length-1]+1][2];
//        for (int i:A) {
//            dp[i][0]=1;
//            dp[i][1]=i;
//        }
//        for (int i=1;i<A.length;i++){
//            for (int j=i-1;j>=0&&A[j]>A[i]/2;j--){
//                if(dp[A[i]-A[j]][0]>0){
//                   if(A[i]==A[j]+dp[A[j]][1]&&dp[A[i]][0]<dp[A[j]][0]+1){
//                        dp[A[i]][0]=dp[A[j]][0]+1;
//                        dp[A[i]][1]=A[j];
//                   }else if(dp[A[i]][0]<dp[A[j]][0]+1){
//                       dp[A[i]][0]=3;
//                       dp[A[i]][1]=A[j];
//                   }
//                   max=Math.max(dp[A[i]][0],max);
//                }
//            }
//        }
//        return max==1?0:max;
    public static void main(String[] args) {
        System.out.println(new lenLongestFibSubseq().lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18,20,21,22,25,29,30,39,45,46,47,50}));
    }
}
