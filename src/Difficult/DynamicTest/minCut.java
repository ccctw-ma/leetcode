package Difficult.DynamicTest;


/*
* 132. 分割回文串 II
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

返回符合要求的 最少分割次数 。



示例 1：

输入：s = "aab"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
示例 2：

输入：s = "a"
输出：0
示例 3：

输入：s = "ab"
输出：1


提示：

1 <= s.length <= 2000
s 仅由小写英文字母组成
* */

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2021/3/8  */


public class minCut {

    public int minCut(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[len+1];
        dp[0] = -1;
        for(int i=1;i<=len;i++){
            dp[i] = dp[i-1]+1;
            for(int j=i-1;j>=1;j--){
                if(isPalindrome(chars,j-1,i-1)){
                    dp[i] = Math.min(dp[i], dp[j-1]+1);
                }
            }
        }
        return dp[len];
    }

    private boolean isPalindrome(char[] chars, int l, int r) {
        while(l<r){
            if(chars[l]!=chars[r]){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    /**@apiNote 增加预处理*/
    public int minCut2(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = chars[i] == chars[j] && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

    /**
     * @apiNote 这样写能够加快冗余计算过程，贪心到最好的结果*/
    public int minCut3(String s) {
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, len - 1);
        for (int i = 0;i < len;i ++) {
            minCutHelper(s, i, i, dp, len);
            minCutHelper(s, i, i + 1, dp, len);
        }
        return dp[len - 1];
    }

    private void minCutHelper(String s, int left, int right, int[] dp, int len) {
        while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            dp[right] = Math.min(dp[right], left == 0 ? 0 : dp[left - 1] + 1);
            left--;
            right++;
        }
    }


    public static void main(String[] args) {
        System.out.println(new minCut().minCut("aababababba"));
    }
}
