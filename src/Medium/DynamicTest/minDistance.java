package Medium.DynamicTest;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/25 15:09
 */


/*
* 583. 两个字符串的删除操作
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。



示例：

输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"


提示：

给定单词的长度不超过500。
给定单词中的字符只含有小写字母。*/


public class minDistance {


    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            char a = chars1[i - 1];
            for (int j = 1; j <= len2; j++) {
                char b = chars2[j - 1];
                dp[i][j] = Math.min(dp[i - 1][j - 1] + (a == b ? 0 : 2), Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[len1][len2];
    }


    public static void main(String[] args) {
        System.out.println(new minDistance().minDistance("sea", "eat"));
    }
}
