package Difficult.DynamicTest;

class stoneGameV {

    int[][] dp = new int[505][505];
    int[] preSums;


    public int f(int i, int j) {
        if (i >= j) return 0;

        if (dp[i][j] != 0) return dp[i][j];
        int res = 0;
        for (int k = i + 1; k <= j; k++) {
            int left = preSums[k] - preSums[i];
            int right = preSums[j + 1] - preSums[k];
            if (left < right) {
                res = Math.max(res, left + f(i, k - 1));
            } else if (left == right) {
                res = Math.max(res, left + Math.max(f(i, k - 1), f(k, j)));
            } else {
                res = Math.max(res, right + f(k, j));
            }
        }

        return res;
    }

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;
        preSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = stoneValue[i] + preSums[i];
        }
        return f(0, n - 1);
    }
}