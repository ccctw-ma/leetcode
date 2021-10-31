package Difficult.DynamicTest;


/*
* 97. 交错字符串
给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

示例 1:

输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true
示例 2:

输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/18  */



public class isInterleave {



    //时间有点长
    private String s1;
    private String s2;
    private String s3;
    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        if(s1.length()+s2.length()!=s3.length()) return false;
        return dfs(0,0,0);
    }

    private boolean dfs(int i1,int i2,int i3){
        if(i3==s3.length()) return true;
        char c=s3.charAt(i3);
        if(i1<s1.length()&&i2<s2.length()){
            char a=s1.charAt(i1);
            char b=s2.charAt(i2);
            if(a==c&&b==c){
                return dfs(i1+1,i2,i3+1)||dfs(i1,i2+1,i3+1);
            }else if(a==c){
                return dfs(i1+1,i2,i3+1);
            }else if(b==c){
                return dfs(i1,i2+1,i3+1);
            }else {
                return false;
            }
        }else if(i1==s1.length()&&i2<s2.length()){
            if (s2.charAt(i2) == s3.charAt(i3)) {
                return dfs(i1,i2+1,i3+1);
            }
        }else if(i1<s1.length()&&i2==s2.length()){
            if(s1.charAt(i1)==s3.charAt(i3)){
                return dfs(i1+1,i2,i3+1);
            }
        }
        return false;
    }



    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int i=1;i<n;i++){
            if(dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1))  dp[i][0]=true;
        }

        for (int i=1;i<m;i++){
            if(dp[0][i-1]&&s2.charAt(i-1)==s3.charAt(i-1))  dp[0][i]=true;
        }

        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                char c=s3.charAt(i+j-1);
                if(dp[i-1][j]&&c==s1.charAt(i-1))
                    dp[i][j]=true;
                if(dp[i][j-1]&&c==s2.charAt(j-1))
                    dp[i][j]=true;
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new isInterleave().isInterleave("aabcc","dbbca","aadbbcbcac"));
    }
}
