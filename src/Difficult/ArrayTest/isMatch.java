package Difficult.ArrayTest;


/*
* 10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/20  */


public class isMatch {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


    public boolean isMatch2(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        // dp[i][j] => is s[0, i - 1] match p[0, j - 1] ?
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];

        dp[0][0] = true;

        for (int i = 1; i <= pArr.length; ++i) {
            dp[0][i] = pArr[i - 1] == '*' && dp[0][i - 2];
        }

        for (int i = 1; i <= sArr.length; ++i) {
            for (int j = 1; j <= pArr.length; ++j) {
                if (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.') {
                    // 看 s[0,...i-1] 和 p[0,...j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (pArr[j - 1] == '*') {
                    // 看 s[0,...i] 和 p[0,...j-2]
                    dp[i][j] |= dp[i][j - 2];

                    if (pArr[j - 2] == sArr[i - 1] || pArr[j - 2] == '.') {
                        // 看 s[0,...i-1] 和 p[0,...j]
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sArr.length][pArr.length];
    }



    public static void main(String[] args) {
        System.out.println(new isMatch().isMatch2("ab",".*"));
    }
}
