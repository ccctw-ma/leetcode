package Easy.StringTest;


/**
 * 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
 *
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
 *
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 * 示例 2：
 *
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 *  
 *
 * 提示：
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] 都是小写英文字母
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 1170. 比较字符串最小字母出现频次 */

public class numSmallerByFrequency {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] res=new int[queries.length];
        int j=0;
        for (String s:queries){
            int i=0;
            for (String ss:words){
                if(f(s)<f(ss)){
                    i++;
                }
            }
            res[j++]=i;
        }
        return res;
    }

    public int f(String s){
        int[] bucket=new int[26];
        for (char c:s.toCharArray()){
            bucket[c-'a']++;
        }
        int i=0;
        while (bucket[i]==0) {
            i++;
        }
        return bucket[i];
    }

    public int[] numSmallerByFrequency2(String[] queries, String[] words) {

        // 统计words里的所有情况
        int [] counter = new int[12];
        for (int i = 0; i < words.length; i++)
            counter[numSmallerByFrequencyChild(words[i])]++;

        // 累和便于判断
        for (int i = 9; i >= 0; i--)
            counter[i] += counter[i + 1];
        //妙呀，
        // 拿值
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++)
            ret[i] = counter[numSmallerByFrequencyChild(queries[i]) + 1];

        return ret;
    }
    //求字符串中最小字符的出现频数
    private static int numSmallerByFrequencyChild(String str) {

        int minChar = str.charAt(0);
        int count = 0;
        for(int i = 1; i < str.length(); i++) {
            if(minChar > str.charAt(i)) {
                minChar = str.charAt(i);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if(minChar == str.charAt(i)) {
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
