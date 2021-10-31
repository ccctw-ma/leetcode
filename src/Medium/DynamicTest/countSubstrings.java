package Medium.DynamicTest;


/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/3/18  */

public class countSubstrings {

    public int countSubstrings(String s) {
        if(s.length()<=1) return s.length();
        int[][] dp=new int[s.length()][s.length()];
        int count=0;
        for (int i=s.length()-1;i>=0;i--){
            dp[i][i]=1;
            count++;
            for (int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)&&(j-i==1||dp[i+1][j-1]==1)){
                    dp[i][j]=1;
                    count++;
                }
            }
        }
        return count;
    }


    //这也是从中心往两侧延伸，类似于暴力破解
    private int cnt = 0;
    public int countSubstrings2(String s) {

        for(int i = 0; i < s.length(); i++){
            extern(s,i,i+1);
            extern(s,i,i);
        }

        return cnt;

    }

    private void extern(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            cnt++;
            start--;
            end++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new countSubstrings().countSubstrings2("abcccba"));
    }
}
