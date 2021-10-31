package Difficult.StringTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/9 19:40
 */

/*
* 68. 文本左右对齐
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
示例:

输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
示例 2:

输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。
     第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]*/



public class fullJustify {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int len = words.length, index = 0;
        char[] chars = new char[maxWidth];
        while (index < len) {
            //这一个单词就占据整行
            if (words[index].length() == maxWidth) {
                res.add(words[index++]);
            } else {
                //获取当前行可以放置的最多的字符串
                int count = 0;
                List<String> temp = new ArrayList<>();
                while (index < len && (count + words[index].length() + temp.size()) <= maxWidth) {
                    temp.add(words[index]);
                    count += words[index++].length();
                }
                //整行只能放一个单词那么进行左对齐即可
                if (temp.size() == 1) {
                    String s = temp.get(0);
                    Arrays.fill(chars, ' ');
                    for (int i = 0; i < s.length(); i++) {
                        chars[i] = s.charAt(i);
                    }
                    res.add(String.valueOf(chars));
                } else if (index == len) {//最后一行
                    Arrays.fill(chars, ' ');
                    int t = 0;
                    for (String s : temp) {
                        for (char c : s.toCharArray()) {
                            chars[t++] = c;
                        }
                        t++;
                    }
                    res.add(String.valueOf(chars));
                } else {
                    //平均分配这些字符串
                    int spaceSize = temp.size() - 1;
                    int totalLength = 0;
                    for (String s : temp) totalLength += s.length();
                    totalLength = maxWidth - totalLength;
                    Arrays.fill(chars, ' ');
                    int t = 0;
                    for (String s : temp) {
                        for (char c : s.toCharArray()) {
                            chars[t++] = c;
                        }
                        if (spaceSize == 0) continue;
                        int spaceNum = totalLength % spaceSize == 0 ? totalLength / spaceSize : totalLength / spaceSize + 1;
                        t += spaceNum;
                        totalLength -= spaceNum;
                        spaceSize--;
                    }
                    res.add(String.valueOf(chars));
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new fullJustify().fullJustify(new String[]{
                "Listen", "to", "many,", "speak", "to", "a", "few."

        }, 6));
    }
}
