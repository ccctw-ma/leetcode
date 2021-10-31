package Difficult.StringTest;


/*
* 214. 最短回文串
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

示例 1:

输入: "aacecaaa"
输出: "aaacecaaa"
示例 2:

输入: "abcd"
输出: "dcbabcd"*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/5/21  */


public class shortestPalindrome {


    private int index;
    public String shortestPalindrome(String s) {
        index=0;
        for (int i=0;i<s.length();i++){
            i=searchPalindrome(s,i);
        }
        //把从index到结尾的字符串翻转拼接到字符串头部即可
        return new StringBuilder(s.substring(index)).reverse().toString()+s;
    }


    //寻找起点在字符串0处的最长回文串
    private int searchPalindrome(String s, int i){
        int low=i,high=i;
        while(high<s.length()-1&&s.charAt(low)==s.charAt(high+1)) high++;
        int ans=high;
        while(low>=0&&high<s.length()&&s.charAt(low)==s.charAt(high)) {
            low--;
            high++;
        }
        low++;
        if(low==0) index=Math.max(index,high);
        return ans;
    }


    public String shortestPalindrome2(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(new shortestPalindrome().shortestPalindrome("aaassscc"));
    }
}
