package InterviewTest;

import java.util.*;

public class tengxunyinyue23_3_23 {

    int[][] dp;
    int n;
    char[] arr;

    public int f(int i, int t) {
        if (dp[i][t] != -1) {
            return dp[i][t];
        }
        if (t == 1) {
            Set<Character> s = new HashSet<>();
            for(int idx = i; idx < n; idx++){
                s.add(arr[idx]);
            }
            int res = s.size() * (n - i);
            dp[i][t] = res;
            return res;
        }

        Set<Character> s = new HashSet<>();
        int res = Integer.MAX_VALUE;
        for (int idx = i; idx <= n - t; idx++) {
            s.add(arr[idx]);
            int tmp = s.size() * (idx - i + 1);
            int other = f(idx + 1, t - 1);
            res = Math.min(res, Math.max(tmp, other));
        }
        dp[i][t] = res;
        return res;
    }

    public int getMaxValue(String str, int k) {
        // write code here

        n = str.length();
        arr = str.toCharArray();
        dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f(0, k);
    }


    public static void main(String[] args) {

        System.out.println(new tengxunyinyue23_3_23().getMaxValue("aacbbbb", 4));
    }
}
