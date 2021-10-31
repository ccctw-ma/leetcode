package Medium.DynamicTest;


/**
 * 712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * 示例 1:
 *
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * 示例 2:
 *
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * 注意:
 *
 * 0 < s1.length, s2.length <= 1000。
 * 所有字符串中的字符ASCII值在[97, 122]之间。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/19  */

public class minimumDeleteSum {

    public int minimumDeleteSum(String s1, String s2) {
        int a=s1.length(),b=s2.length();
        int[][] dp=new int[a+1][b+1];
        dp[a][b]=0;
        for (int i=a-1;i>=0;i--){
            dp[i][b]=s1.charAt(i)+dp[i+1][b];
        }
        for (int i=b-1;i>=0;i--){
            dp[a][i]=s2.charAt(i)+dp[a][i+1];
        }
        for (int i=a-1;i>=0;i--){
            for (int j=b-1;j>=0;j--){
                char left=s1.charAt(i),right=s2.charAt(j);
                if(left==right){
                    dp[i][j]=dp[i+1][j+1];
                }else {
                    dp[i][j]=Math.min(dp[i+1][j]+left,dp[i][j+1]+right);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new minimumDeleteSum().minimumDeleteSum("sea","eat"));
        //System.out.println("abcd".indexOf('a',2));
    }

}
