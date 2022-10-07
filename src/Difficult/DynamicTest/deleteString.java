class Solution {

	public int deleteString(String s) {
		int[] dp = new int[s.length()];
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i + (dp[i] = 1), k = i; j < s.length();) {
				if (s.charAt(k) == s.charAt(j)) {
					dp[i] = Math.max(dp[i], ++k + k == ++j + i ? dp[k] + 1 : 0);
				} else if (k > i) {
					k = i;
				} else {
					j++;
				}
			}
		}
		return dp[0];
	}
}