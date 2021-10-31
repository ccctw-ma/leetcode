package Difficult.DynamicTest;


/*
* 410. 分割数组的最大值
给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。

注意:
数组长度 n 满足以下条件:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
示例:

输入:
nums = [7,2,5,10,8]
m = 2

输出:
18

解释:
一共有四种方法将nums分割为2个子数组。
其中最好的方式是将其分为[7,2,5] 和 [10,8]，
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/7/25  */


public class splitArray {


    //能过就是时间复杂度太高了，状态转移方程有点问题
    public int splitArray(int[] nums, int m) {
        int n=nums.length;
        int[] sums=new int[n+1];
        for (int i=1;i<sums.length;i++){
            sums[i]=(sums[i-1]+nums[i-1]);
        }
        int[][][] dp=new int[n+1][n+1][m];
        for (int i=1;i<=n;i++){
            for (int j=i;j<=n;j++){
                dp[i][j][0]=sums[j]-sums[i-1];
            }
        }
        //切几刀
        for (int k=1;k<m;k++){
            //遍历每一种可能的切法
            for (int i=1;i<=n-k;i++){
                for (int j=i+k;j<=n;j++){
                    //寻找切点
                    dp[i][j][k]=Integer.MAX_VALUE;
                    for (int c=i+1;c<=j-k+1;c++){
                        int temp=Math.max(dp[i][c-1][0],dp[c][j][k-1]);
                        dp[i][j][k]=Math.min(dp[i][j][k],temp);
                    }
                }
            }
        }
        return dp[1][n][m-1];
    }

    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        //f[i][j]表示(0,i)的数组分成j组的最优情况
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

    public int splitArray3(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            right += num;
            if (left < num) {
                left = num;
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int num : nums) {
            if (sum + num > x) {
                cnt++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return cnt <= m;
    }

    public static void main(String[] args) {
        System.out.println(new splitArray().splitArray3(new int[]{7,2,5,10,8},2));
    }
}
