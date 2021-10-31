package Difficult.StringTest;



/*
* 76. 最小覆盖子串
给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。*/

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author 马世臣
 * @// TODO: 2020/5/23  */


public class minWindow {

    public String minWindow(String s, String t) {
        int left,right,min;
        String res="";
        Map<Character, PriorityQueue<Integer>> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(map.containsKey(ch)){
                PriorityQueue<Integer> queue=map.get(ch);
                queue.add(i);
                map.put(ch,queue);
            }else {
                PriorityQueue<Integer> queue=new PriorityQueue<>();
                queue.add(i);
                map.put(ch,queue);
            }
        }
        TreeSet<Integer> list=new TreeSet<>();
        for (char c:t.toCharArray()){
            if(map.containsKey(c)&&map.get(c).size()>0){
                int temp=map.get(c).poll();
                list.add(temp);
            }else{
                return res;
            }
        }
        left=list.first();
        right=list.last();
        min=right-left;
        res=s.substring(left,right+1);
        char ch=s.charAt(left);
        while (map.get(ch).size()>0){
            int temp=map.get(ch).poll();
            list.pollFirst();
            list.add(temp);
            left=list.first();
            right=list.last();
            if(right-left<min){
                res=s.substring(left,right+1);
                min=right-left;
            }
            ch=s.charAt(left);
        }
        return res;
    }

    public String minWindow2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        int[] tFreq = new int[128];
        for (char c : charArrayT) {
            tFreq[c]++;
        }

        // 滑动窗口内部还差多少 T 中的字符，对应字符频数超过不重复计算
        int distance = tLen;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        // [left..right)
        while (right < sLen) {
            char charRight = charArrayS[right];
            if (tFreq[charRight] > 0) {
                distance--;
            }
            tFreq[charRight]--;
            right++;

            while (distance == 0) {

                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                char charLeft = charArrayS[left];
                tFreq[charLeft]++;

                //不是t里的字符对应的tFreq[i]永远不会大于0;
                if (tFreq[charLeft] > 0) {
                    distance++;
                }
                left++;
            }
        }

        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(begin, begin + minLen);
    }
    public static void main(String[] args) {
        System.out.println(new minWindow().minWindow2("abcabdebac" ,"cda"));
    }
}
