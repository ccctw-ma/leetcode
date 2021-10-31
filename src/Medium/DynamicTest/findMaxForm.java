package Medium.DynamicTest;


/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 * 注意:
 *
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * 示例 1:
 *
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 *
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 *
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/3/17  */

public class findMaxForm {

    //双重的01背包问题
    // dp(i, j) = max(1 + dp(i - cost_zero[k], j - cost_one[k]))
    //    if i >= cost_zero[k] and j >= cost_one[k]
    //其中 k 表示第 k 个字符串，cost_zero[k] 和 cost_one[k] 表示该字符串中 0 和 1 的个数。我们依次枚举这些字符串，并根据状态转移方程更新所有的 dp(i, j)。注意由于每个字符串只能使用一次（即有限背包），因此在更新 dp(i, j) 时，i 和 j 都需要从大到小进行枚举。
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp=new int[m+1][n+1];
        for (String s:strs){
            int[] temp=findOneAndZero(s);
            for (int i=m;i>=temp[0];i--)
                for (int j=n;j>=temp[1];j--){
                    dp[i][j]=Math.max(dp[i][j],dp[i-temp[0]][j-temp[1]]+1);
                }
        }
        return dp[m][n];
    }

    private int[] findOneAndZero(String s){
        int one=0,zero=0;
        for (char c:s.toCharArray()){
            if(c=='0'){
                zero++;
            }else {
                one++;
            }
        }
        return new int[]{zero,one};
    }
    public static void main(String[] args) {
        System.out.println(new findMaxForm().findMaxForm(new String[]{"10","0001","111001","1","0"}, 3, 4));
    }
}
