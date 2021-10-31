package Easy.StringTest;


/**
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。题目保证至少有一个词不在禁用列表中，而且答案唯一。
 *
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 *
 *  
 *
 * 示例：
 *
 * 输入: 
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * 输出: "ball"
 * 解释: 
 * "hit" 出现了3次，但它是一个禁用的单词。
 * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 
 * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"）， 
 * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
 *  
 *
 * 说明：
 *
 * 1 <= 段落长度 <= 1000.
 * 1 <= 禁用单词个数 <= 100.
 * 1 <= 禁用单词长度 <= 10.
 * 答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
 * paragraph 只包含字母、空格和下列标点符号!?',;.
 * 不存在没有连字符或者带有连字符的单词。
 * 单词里只包含字母，不会出现省略号或者其他标点符号。
 **/

import java.util.*;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/30 819. 最常见的单词 */

public class mostCommonWord {


    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> strings=new ArrayList<>();
        List<String> banList = new ArrayList<>(Arrays.asList(banned));
        int i=0,j;
        while (i<paragraph.length()){
            j=i;
            while (j<paragraph.length()&&Character.isLetter(paragraph.charAt(j))) j++;
            String temp=paragraph.substring(i,j).toLowerCase();
            if(!banList.contains(temp))
            strings.add(temp);
            while (j<paragraph.length()&&!Character.isLetter(paragraph.charAt(j))) j++;
            i=j;
        }
        int max=Integer.MIN_VALUE;
        Map<String,Integer> map=new HashMap<>();
        for (String s:strings){
            int temp=map.getOrDefault(s,0)+1;
            if(temp>max) max=temp;
            map.put(s,temp);
        }
        for (String s:map.keySet()){
            if(map.get(s)==max){
                return s;
            }
        }
        return null;
    }


    //可以一边获取单词一边进行是否为最多的判断，节省时间
    public String mostCommonWord2(String paragraph, String[] banned) {
        paragraph += ".";
        Set<String> ban = new HashSet<>();
        for (String s : banned) {
            ban.add(s);
        }
        Map<String,Integer> map = new HashMap<>();
        String res = "";
        int fre = 0;
        StringBuilder sb = new StringBuilder();
        for (char c: paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (sb.length() > 0) {
                String word = sb.toString();
                if (!ban.contains(word)) {
                    map.put(word,map.getOrDefault(word,0) + 1);
                    if (map.get(word) > fre) {
                        fre = map.get(word);
                        res = word;
                    }
                }
                sb = new StringBuilder();
            }
        }
        // System.out.println(fre);
        return res;
    }

    public static void main(String[] args) {
        String[] banned=new String[]{"hit"};
        System.out.println(new mostCommonWord().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",banned));
    }
}
