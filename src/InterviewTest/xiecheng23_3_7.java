package InterviewTest;

import java.util.*;

public class xiecheng23_3_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] prices = new int[n];
        int[] loves = new int[n];
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            prices[i] = t;
        }
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            loves[i] = t;
        }
        long res = 0;
        long[][] dp = new long[n + 1][x + 1];
        for (int i = n - 1; i >= 0; i -= 1) {
            for (int j = x; j >= 0; j -= 1) {
                long a = dp[i + 1][j];
                if (j < prices[i]) {
                    dp[i][j] = a;
                    continue;
                }
                long b = j >= prices[i] ? dp[i + 1][j - prices[i]] + loves[i] : 0;
                long c = 0;
                if (i < n - 1) {
                    int cost = prices[i] + prices[i + 1] / 2;
                    if (j >= cost) {
                        c = dp[i + 2][j - cost] + loves[i] + loves[i + 1];
                    }
                }
                dp[i][j] = Math.max(a, Math.max(b, c));
                res = Math.max(res, dp[i][j]);
            }
        }
        System.out.println(res);

    }
}
