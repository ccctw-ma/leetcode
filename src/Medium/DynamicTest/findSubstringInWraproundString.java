package Medium.DynamicTest;



/**
 * 467. 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。
 *
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 *
 *
 * 示例 2:
 *
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 *
 *
 * 示例 3:
 *
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/17  */

public class findSubstringInWraproundString {

    public int findSubstringInWraproundString(String p) {
        if(p.length()<=1) return p.length();
        int[] dp=new int[p.length()];
        int[] sums=new int[26];
        dp[0]=1;
        sums[p.charAt(0)-'a']=1;
        for (int i=1;i<p.length();i++){
            char first=p.charAt(i),last=p.charAt(i-1);
            if((first-last+26)%26==1){
                dp[i]=dp[i-1]+1;
            }else {
                dp[i]=1;
            }
            sums[first-'a']=Math.max(sums[first-'a'],dp[i]);
        }
        int sum=0;
        for (int i:sums) sum+=i;
        return sum;
    }

    //每次的如果遇到更大的值就把加上差值，做到总是取最大值
    public int findSubstringInWraproundString2(String p) {
        int len = p.length();
        if (len == 0) return 0;
        int[] map = new int[26];
        int dp = 0;
        int sum = 0;
        char[] arr = p.toCharArray();
        for (int i=0; i<len; i++) {
            char c = arr[i];
            if (i == 0 || (c-arr[i-1] -1)%26 == 0) {
                dp++;
            } else dp = 1;
            int cnt = map[c-'a'];
            if (dp > cnt) {
                sum += dp - cnt;
                map[c-'a'] = dp;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new findSubstringInWraproundString().findSubstringInWraproundString("cdefefg"));
    }
}
