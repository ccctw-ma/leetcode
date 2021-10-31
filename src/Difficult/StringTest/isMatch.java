package Difficult.StringTest;


/*
* 44. 通配符匹配
给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输出: false*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/5  */


public class isMatch {

    public boolean isMatch(String s, String p) {
        int a=s.length();
        int b=p.length();
        if(a==0&&b==0) return true;
        if(a!=0&&b==0) return false;
        if(p.equals("*")) return true;
        boolean[][] dp=new boolean[a+1][b+1];
        dp[0][0]=true;
        for (int i=0;i<b;i++){
            if(p.charAt(i)=='*'){
                dp[0][i+1]=true;
            }else {
                break;
            }
        }
        for (int i=1;i<=a;i++){
            char c=s.charAt(i-1);
            for (int j=1;j<=b;j++){
                char d=p.charAt(j-1);
                //i,j-1为*不代替任务字符，i-1,j-1为*代替1个字符,i-1,j为*代替多个字符
                if(d=='*'&&(dp[i][j-1]||dp[i-1][j-1]||dp[i-1][j])){
                    dp[i][j]=true;
                }else if((d=='?'||c==d)&&dp[i-1][j-1]){
                    dp[i][j]=true;
                }
            }
        }
        return dp[a][b];
    }

    public boolean isMatch2(String s, String p) {
        int a=s.length();
        int b=p.length();
        if(a==0&&b==0) return true;
        if(a!=0&&b==0) return false;
        if(p.equals("*")) return true;
        boolean[] dp=new boolean[b+1];
        dp[0]=true;
        for (int i=0;i<b;i++){
            if(p.charAt(i)=='*'){
                dp[i+1]=true;
            }else {
                break;
            }
        }
        for (int i=1;i<=a;i++){
            char c=s.charAt(i-1);
            boolean[] temp=new boolean[b+1];
            for (int j=1;j<=b;j++){
                char d=p.charAt(j-1);
                //i,j-1为*不代替任务字符，i-1,j-1为*代替1个字符,i-1,j为*代替多个字符
                if(d=='*'&&(temp[j-1]||dp[j-1]||dp[j])){
                    temp[j]=true;
                }else if((d=='?'||c==d)&&dp[j-1]){
                    temp[j]=true;
                }
            }
            dp=temp;
        }
        return dp[b];
    }

    public static void main(String[] args) {
        System.out.println(new isMatch().isMatch2("acdcb","a*c?b"));
    }
}
