package Easy.HashTable;


/**
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 *
 *  
 *
 *
 *
 *  
 *
 * 示例：
 *
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *  
 *
 * 注意：
 *
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/2/2 500. 键盘行 */

public class findWords {

    public String[] findWords(String[] words) {
        String l1="qwertyuiop";
        String l2="asdfghjkl";
        String l3="zxcvbnm";
        List<String> strings=new ArrayList<>();
        for (String ss:words){
            String s=ss.toLowerCase();
            String l;
            char first=s.charAt(0);
            if(l1.indexOf(first)!=-1){
                l=l1;
            }else if(l2.indexOf(first)!=-1){
                l=l2;
            }else {
                l=l3;
            }
            boolean flag=true;
            for (char ch:s.toCharArray()){
                if(l.indexOf(ch)==-1){
                    flag=false;
                    break;
                }
            }
            if(flag){
                strings.add(ss);
            }
        }
        String[] strings1=new String[strings.size()];
        for (int i=0;i<strings.size();i++){
            strings1[i]=strings.get(i);
        }
        return strings1;
    }

    public static void main(String[] args) {

    }
}
