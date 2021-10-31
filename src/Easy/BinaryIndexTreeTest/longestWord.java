package Easy.BinaryIndexTreeTest;


/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 *
 * 若无答案，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入: 
 * words = ["w","wo","wor","worl", "world"]
 * 输出: "world"
 * 解释: 
 * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 * 示例 2:
 *
 * 输入: 
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出: "apple"
 * 解释: 
 * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 * 注意:
 *
 * 所有输入的字符串都只包含小写字母。
 * words数组长度范围为[1,1000]。
 * words[i]的长度范围为[1,30]。
 **/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/25 720. 词典中最长的单词 */

public class longestWord {


    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String s : words) {
            //如果单词只有一个字母，那一定是共有的
            if (s.length() == 1 || set.contains(s.substring(0, s.length() - 1))) {
                res = s.length() > res.length() ? s : res;
                set.add(s);
            }
        }
        return res;
    }

    class Solution {
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isWord = false;
            String str = "";
        }
        TrieNode node = new TrieNode();
        public void insert(String s) {
            TrieNode cur = node;
            for(int i = 0; i < s.length(); i++)
            {
                int pos = s.charAt(i) - 'a';
                if(cur.children[pos] == null)
                    cur.children[pos] = new TrieNode();
                cur = cur.children[pos];
            }
            cur.isWord = true;
            cur.str = s;
        }
        public String longestWord(String[] words) {
            for(String word: words)
                insert(word);
            dfs(node);
            return res;
        }

        String res = "";
        public void dfs(TrieNode cur) {
            if(cur.str.length() > res.length())//???????? ????????26????????
                res = cur.str;

            for(int i = 0; i < 26; i++)
            {
                if(cur.children[i] != null && cur.children[i].isWord)
                    dfs(cur.children[i]);
            }
        }
    }



    
    public static void main(String[] args) {
        System.out.println("asdf".substring(0,3));
    }
}
