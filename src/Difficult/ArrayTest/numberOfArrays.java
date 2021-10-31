package Difficult.ArrayTest;

public class numberOfArrays {


    public int numberOfArrays(String s, int k) {
        int N = s.length();
        int MOD = 1_000_000_007;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        int len = (k + "").length();
        for(int i = 1; i <= N; i++) {
            for(int j = Math.max(0, i - len); j < i; j++) {
                if(s.charAt(j) != '0' && Long.valueOf(s.substring(j, i)) <= k) {
                    dp[i] = (dp[i] + dp[j]) % MOD;
                    // System.out.println(s.substring(j, i));
                }
            }
        }
        return (int)dp[N];
    }
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("020"));
    }
}
