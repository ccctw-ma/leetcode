package Medium.HashTableTest;


/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。*/

import java.util.HashSet;
import java.util.Set;

/**
 * @author 马世臣
 * @// TODO: 2020/4/17
 * */


public class lengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<>();
        int max=0,index=0;
        while (index<s.length()){
            while (index<s.length()&&!set.contains(s.charAt(index))){
                set.add(s.charAt(index));
                index++;
            }
            if(index<s.length()){
                char temp=s.charAt(index);
                index--;
                while (index>=0&&s.charAt(index)!=temp){
                    index--;
                }
                index++;
            }
            max=Math.max(max,set.size());
            set.clear();
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        int res=0;
        int[] bucket=new int[128];
        for (int i=0,j=0;j<s.length();j++){
            i=Math.max(i,bucket[s.charAt(j)]);//确保左边指针与右边指针之间的字符串是没有重复的
            res=Math.max(res,j-i+1);
            bucket[s.charAt(j)]=j+1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));
    }

}
