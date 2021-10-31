package Easy.StringTest;


/**
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/26 38. 外观数列 */

public class countAndSay {

    public String countAndSay(int n) {
        if(n<=1){
            return "1";
        }
        String s=countAndSay(n-1);
        List<Character> list=new ArrayList<>();
        int count=0;
        char temp=' ';
        for (char ch:s.toCharArray()){
            if(temp==' '){
                temp=ch;
                count=1;
            }else if(ch==temp){
                count++;
                continue;
            }else {
                list.add((char)(count+'0'));
                list.add(temp);
                temp=ch;
                count=1;
            }
        }
        list.add((char)(count+'0'));
        list.add(temp);
        StringBuilder stringBuilder=new StringBuilder();
        for (char ch:list){
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    
    public static void main(String[] args) {
        System.out.println(new countAndSay().countAndSay(4));
    }
}
