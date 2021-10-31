package Difficult.DynamicTest;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/27 14:03
 */
public class numDecodings {


    public int numDecodings(String s) {
        int mod = (int) (1e9 + 7);
        int len = s.length();
        char[] chars = s.toCharArray();
        if (len == 1 && chars[0] == '0') return 0;
        long[] dp = new long[len + 1];
        int c = chars[0];
        dp[0] = 1;
        dp[1] = c == '*' ? 9 : c == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            char a = chars[i - 1];
            char b = chars[i - 2];
            if (a == '*') {
                dp[i] = (dp[i] + (dp[i - 1] * 9) % mod) % mod;
                if (b == '*') {
                    dp[i] = (dp[i] + (dp[i - 2] * 15) % mod) % mod;
                } else if (b == '2') {
                    dp[i] = (dp[i] + (dp[i - 2] * 6) % mod) % mod;
                } else if (b == '1') {
                    dp[i] = (dp[i] + (dp[i - 2] * 9) % mod) % mod;
                }
            } else if (a == '0') {
                dp[i] = dp[i - 2];
                if (b == '*') {
                    dp[i] = (dp[i - 2] * 2) % mod;
                } else if (b != '1' && b != '2') return 0;
            } else if (a >= '1' && a <= '6') {
                dp[i] = dp[i - 1];
                if (b == '*') {
                    dp[i] = (dp[i] + (dp[i - 2] * 2) % mod) % mod;
                } else if (b == '1' || b == '2') {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                }
            } else {
                dp[i] = dp[i - 1];
                if (b == '1' || b == '*') {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                }
            }
        }
        return (int) dp[len];
    }


    static final int MOD = 1000000007;

    public int numDecodings2(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    public int check1digit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    public int check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 :  1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }



    public static void main(String[] args) {
        System.out.println(new numDecodings().numDecodings("0*1*8"));
    }
}
