package Medium.DynamicTest;



/**
 * 1048. 最长字符串链
 * 给出一个单词列表，其中每个单词都由小写英文字母组成。
 *
 * 如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
 *
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。
 *
 * 从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
 *
 *
 * 示例：
 *
 * 输入：["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 "a","ba","bda","bdca"。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] 仅由小写英文字母组成。*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author 马世臣
 * @// TODO: 2020/3/26
 * */

public class longestStrChain {

    public int longestStrChain(String[] words) {
        if(words.length==1) return 1;
        int[] dp=new int[words.length];
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Arrays.fill(dp,1);
        int max=1;
        for (int i=1;i<words.length;i++){
            String a=words[i];
            for (int j=i-1;j>=0;j--){
                String b=words[j];
                if(contain(a,b)){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    max=Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }

    private static boolean contain(String a,String b){
        if(a.length()-b.length()==1){
            int flag=0,i=0,j=0;
            while (i<a.length()){
                if(j==b.length()||a.charAt(i)!=b.charAt(j)){
                    i++;
                    flag++;
                }else {
                    i++;
                    j++;
                }
            }
            return flag==1;
        }
        return false;
    }



    //DFS方法，深度搜索算法实现：速度较快
    public int longestStrChain2(String[] words) {
        int maxlenth = 1;

        HashMap<Integer, ArrayList> m = new HashMap<>();

        //先将所有字符按照长度来分组
        for (int i = 0; i < words.length; i++) {
            m.computeIfAbsent(words[i].length(), k -> new ArrayList<String>());

            m.get(words[i].length()).add(words[i]);
        }

        for (int i = 16; i > 0; i--) {
            if (i <= maxlenth) {
                break;
            }

            ArrayList<String> currentList = m.get(i);

            if (currentList == null) {
                continue;
            }

            for (int j = 0; j < currentList.size(); j++) {
                String currentword = currentList.get(j);
                int maxlenthTemp = 1 + getSubLenth(currentword, m);
                if (maxlenthTemp > maxlenth) {
                    maxlenth = maxlenthTemp;
                }
            }
        }

        return maxlenth;
    }

    private int getSubLenth(String word, HashMap<Integer, ArrayList> m) {
        int maxlenth = 0;
        char[] wordCharList = word.toCharArray();
        char[] currentWordCharList = null;
        int displacement = 0;

        ArrayList<String> currentList = m.get(word.length() - 1);
        if (currentList == null) {
            return maxlenth;
        }

        for (int j = 0; j < currentList.size(); j++) {
            boolean isSubString = true;

            displacement = 0;
            String currentword = currentList.get(j);
            currentWordCharList = currentword.toCharArray();

            for (int i = 0; i < wordCharList.length; i++) {
                if (displacement == 0 && i == currentWordCharList.length) {
                    break;
                }

                if (wordCharList[i] != currentWordCharList[i - displacement]) {
                    displacement++;
                }

                if (displacement > 1) {
                    isSubString = false;
                    break;
                }
            }

            if (isSubString) {
                int maxlenthTemp = 1 + getSubLenth(currentword, m);
                if (maxlenthTemp > maxlenth) {
                    maxlenth = maxlenthTemp;
                }
            }
        }

        return maxlenth;
    }


    public static void main(String[] args) {
        System.out.println(new longestStrChain().longestStrChain(new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"}));
        System.out.println(contain("kss","ks"));
    }
}
