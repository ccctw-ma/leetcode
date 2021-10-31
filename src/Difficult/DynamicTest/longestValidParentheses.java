package Difficult.DynamicTest;


/*
* 32. 最长有效括号
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/4  */


public class longestValidParentheses {

    public int longestValidParentheses(String s) {
        int n=s.length();
        if(n==0) return n;
        int[] dp=new int[n+1];
        int max=0;
        for (int i=1;i<n;i++){
            char c=s.charAt(i);
            if(c==')'){
                char pre=s.charAt(i-1);
                if(pre=='('){
                    dp[i+1]=dp[i-1]+2;
                }else if(pre==')'&&dp[i]!=0){
                    int index=i-dp[i]-1;
                    if(index>=0&&s.charAt(index)=='('){
                        dp[i+1]=dp[index]+dp[i]+2;
                    }
                }
            }
            max=Math.max(dp[i+1],max);
        }
        return max;
    }

    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }




    public static void main(String[] args) {
        System.out.println(new longestValidParentheses().longestValidParentheses("())"));
    }
}
