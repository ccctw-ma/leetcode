package Easy.HashTable;

import java.util.*;


/**
 * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
 *
 * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
 *
 * 返回所有不常用单词的列表。
 *
 * 您可以按任何顺序返回列表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = "this apple is sweet", B = "this apple is sour"
 * 输出：["sweet","sour"]
 * 示例 2：
 *
 * 输入：A = "apple apple", B = "banana"
 * 输出：["banana"]
 *  
 *
 * 提示：
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A 和 B 都只包含空格和小写字母。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/2/5 884. 两句话中的不常见单词 */


public class uncommonFromSentences {


    public String[] uncommonFromSentences(String A, String B) {
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();
        for (String s:A.split(" ")){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        for (String s:B.split(" ")){
            map2.put(s,map.getOrDefault(s,0)+1);
        }
        List<String> strings=new ArrayList<>();
        for (String s:map.keySet()){
            if(map.get(s)==1&&!map2.containsKey(s)){
                strings.add(s);
            }
        }
        for (String s:map2.keySet()){
            if(map2.get(s)==1&&!map.containsKey(s)){
                strings.add(s);
            }
        }
        return strings.toArray(new String[strings.size()]);
    }


    //是需要用一个哈西即可
    public String[] uncommonFromSentences2(String A, String B) {
        Map<String, Integer> count = new HashMap();
        for (String word: A.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);
        for (String word: B.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);

        List<String> ans = new LinkedList();
        for (String word: count.keySet())
            if (count.get(word) == 1)
                ans.add(word);

        return ans.toArray(new String[ans.size()]);
    }

    public static void main(String[] args) {

    }
}
