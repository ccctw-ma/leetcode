package Difficult.DynamicTest;

public class maxPalindromes {

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                f[i][j] = true;
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j) && f[i + 1][j - 1]) {
                    f[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        for (int i = k; i <= n; i++) {
            for (int span = k; span <= i; span++) {
                if (dp[i - span] + 1 < dp[i]) {
                    break;
                }
                if (f[i - span][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[i - span] + 1);
                }
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[n];
    }


    public static void main(String[] args) {

    }
}
