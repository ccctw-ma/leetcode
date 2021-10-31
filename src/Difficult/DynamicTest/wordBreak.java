package Difficult.DynamicTest;

import java.util.ArrayList;
import java.util.List;


/*140. Word Break II
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]*/


/**
 * @author 马世臣
 * @// TODO: 2020/11/1  */


public class wordBreak {


    private List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        res=new ArrayList<>();
        //排除不可能的用例
        int[] bucket=new int[26];
        for (String word:wordDict){
            for (char c:word.toCharArray()) bucket[c-'a']++;
        }
        for (char c:s.toCharArray()){
            if(bucket[c-'a']==0) return res;
        }
        traceBack(s,new ArrayList<>(),wordDict,0);
        return res;
    }

    private void traceBack(String s,List<String> list,List<String> wordDict,int index){
        if(index==s.length()){
            StringBuilder builder=new StringBuilder();
            for (String word:list)  builder.append(word).append(" ");
            builder.deleteCharAt(builder.length()-1);
            res.add(builder.toString());
        }
        for (String temp:wordDict){
            if(index+temp.length()>s.length())  continue;
            if(s.substring(index,index+temp.length()).equals(temp)){
                list.add(temp);
                traceBack(s,list,wordDict,index+temp.length());
                list.remove(list.size()-1);
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("abcd".substring(0,4));
    }
}
