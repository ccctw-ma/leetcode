package Difficult.MapTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/*
* 1178. 猜字谜
外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。

字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：

单词 word 中包含谜面 puzzle 的第一个字母。
单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。



示例：

输入：
words = ["aaaa","asas","able","ability","actt","actor","access"],
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
输出：[1,1,3,2,4,0]
解释：
1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。


提示：

1 <= words.length <= 10^5
4 <= words[i].length <= 50
1 <= puzzles.length <= 10^4
puzzles[i].length == 7
words[i][j], puzzles[i][j] 都是小写英文字母。
每个 puzzles[i] 所包含的字符都不重复。*/

/**
 * @author 马世臣
 * @// TODO: 2021/2/26
 * */


public class findNumOfValidWords {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (String word : words) {
            int d = digitizing(word);
            if(Integer.bitCount(d)<=7){
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
        }
        for (String puzzle : puzzles) {
            int a = digitizing(puzzle);//谜面
            int count = 0;//满足要求的谜底数量
            int t = puzzle.charAt(0) - 'a';//谜面的第一个字母在32位中的位置
            for (int b : map.keySet()) {
                if (((b >> t) & 1) == 1 && (a & b) == b) {
                    count += map.get(b);
                }
            }
            res.add(count);
        }
        return res;
    }

    private int digitizing(String s){
        int temp = 0;
        for (char c:s.toCharArray()){
            temp |= (1<<(c-'a'));
        }
        return temp;
    }

    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集方法一
            // for (int choose = 0; choose < (1 << 6); ++choose) {
            //     int mask = 0;
            //     for (int i = 0; i < 6; ++i) {
            //         if ((choose & (1 << i)) != 0) {
            //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //         }
            //     }
            //     mask |= (1 << (puzzle.charAt(0) - 'a'));
            //     if (frequency.containsKey(mask)) {
            //         total += frequency.get(mask);
            //     }
            // }

            // 枚举子集方法二
            int mask = 0;
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
/**
 * 就是这一部分，因为puzzle固定是7个字母且这7个字母不同，所以bitmask（Integer）里有7个1，
 * 又有谜底要有谜面的第一个字母，所以有一个1确定的必须有，subset = (subset - 1) & mask
 * 这一步操作可以其余6个1所有排列组合全部列出来
 * 比如 subset为2进制的 111111 ，操作之后会是111110、111101、111100、...、000000（64种组合）
 * 到最后sub-1 为 -1 也就是11111111111111111111111111111111 在与mask与运算就与mask相等了
 * 循环也就结束了，通过这种枚举获取所有与之匹配的谜底 完成计算
 *
 * */


                subset = (subset - 1) & mask;
//                System.out.println(Integer.toBinaryString(subset));
            } while (subset != mask);
            ans.add(total);
        }
        return ans;
    }




    public static void main(String[] args) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};


//        System.out.println(new findNumOfValidWords().findNumOfValidWords2(words,puzzles));
//        int mask = 0;
//        for (int i=0;i<7;i++){
//            mask |= (1<<i);
//        }
//        System.out.println(mask);
//        int subset = mask;
//        int count = 0;
//        do {
//            int s = subset | (1 << 7);
//            System.out.println("s:"+Integer.toBinaryString(s));
//            subset = (subset - 1) & mask;
//            count++;
//            System.out.println("subset:"+Integer.toBinaryString(subset));
//        } while (subset != mask);
//        System.out.println(count);
        System.out.println(Integer.toBinaryString(-1));
    }
}
