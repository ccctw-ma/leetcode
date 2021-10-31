package Medium.DynamicTest;


/**
 * 516. 最长回文子序列
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/17  */


public class longestPalindromeSubseq {


//    public int longestPalindromeSubseq(String s) {
//        if(s.length()<=1) return s.length();
//        int[][] dp=new int[s.length()][2];//0 is the length of palindrome 1 is the correspond item's index
//        dp[0][0]=1;
//        dp[0][1]=0;
//        int max=0;
//        for (int i=1;i<s.length();i++){
//            char first=s.charAt(i);
//            for (int j=i-1;j>=0;j--){
//                if(first==s.charAt(j)){
//                    int k=j-1;
//                    while (k>=0&&s.charAt(k)==first) k--;
//                    if(j-k+1>dp[i][0]){
//                        dp[i][0]=j-k+1;
//                        dp[i][1]=k+1;
//                    }
//                }
//                int index=dp[j][1]-1;
//                while (index>=0&&s.charAt(index)!=first) index--;
//                if(index>=0&&dp[j][0]+2>dp[i][0]){
//                    dp[i][0]=dp[j][0]+2;
//                    dp[i][1]=index;
//                }
//            }
//            if(dp[i][0]==0){
//                dp[i][0]=1;
//                dp[i][1]=i;
//            }
//            max=Math.max(max,dp[i][0]);
//        }
//        return max;
//    }

    public int longestPalindromeSubseq(String s) {
        if(s.length()<=1) return s.length();
        int[][] dp=new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            dp[i][i]=1;
        }
        int max=0;
        for (int i=s.length()-1;i>=0;i--){
            for (int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else {
                    dp[i][j]=Math.max(dp[i+1][j],Math.max(dp[i][j-1],dp[i+1][j-1]));
                }
                max=Math.max(dp[i][j],max);
            }
        }
        return max;
    }

    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }


    //进行空间优化，只用两个数组即可
    public int longestPalindromeSubseq3(String s) {
        char[] chars=s.toCharArray();
        int length=s.length();
        int[] current=new int[length];
        int[] pre =new int[length];
        for(int i=length-1;i>=0;i--){
            current[i]=1;
            for(int j=i+1;j<length;j++){
                if(chars[i]==chars[j]){
                    current[j]=pre[j-1]+2;
                }else{
                    current[j]=Math.max(current[j-1],pre[j]);
                }
            }
            int[] tmp=pre;
            pre=current;
            current=tmp;
        }
        return pre[length-1];
    }


    public static void main(String[] args) {
        System.out.println(new longestPalindromeSubseq().longestPalindromeSubseq("skjhdjlas"));
    }
}
