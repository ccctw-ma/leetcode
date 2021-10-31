package Medium.SlidingWindow;


/*
*
* 567. 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间*/

/**
 * @author 马世臣
 * @// TODO: 2021/2/10
 * */

public class checkInclusion {


    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if(n1>n2) return false;
        int[] tar = new int[26];
        int[] temp = new int[26];
        for (char c:s1.toCharArray()){
            tar[c-'a']++;
        }
        for (int i=0;i<n1;i++){
            temp[s2.charAt(i)-'a']++;
        }
        if(check(temp,tar)) return true;
        for(int i=n1;i<n2;i++){
            int a = s2.charAt(i)-'a';
            int b = s2.charAt(i-n1)-'a';
            temp[a]++;
            temp[b]--;
            if(check(temp,tar)) return true;
        }
        return false;
    }

    private boolean check(int[] a,int[] b){
        for (int i=0;i<26;i++){
            if(a[i]!=b[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) {

    }
}
