package Medium.DynamicTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/11  */

public class wordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int[] dp=new int[s.length()];
        for (int i=1;i<=dp.length;i++){
            if(set.contains(s.substring(0,i))){
                dp[i-1]=1;
            }else {
                for(int j=0;j<i;j++){
                    if(dp[j]==1&&set.contains(s.substring(j+1,i))){
                        dp[i-1]=1;
                    }
                }
            }

        }
        return dp[s.length()-1]==1;
    }

    public static void main(String[] args) {
        List<String> wordDict=new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        //System.out.println(new wordBreak().wordBreak("catsandog",wordDict));
        System.out.println("\u2060"+"\u2059"+"\u2058"+"\u2222");

    }
}
