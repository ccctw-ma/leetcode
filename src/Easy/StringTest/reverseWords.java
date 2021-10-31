package Easy.StringTest;


/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例 1:
 *
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 557. 反转字符串中的单词 III */
public class reverseWords {


    public String reverseWords(String s) {
        String[] strings=s.split(" ");
        StringBuilder res=new StringBuilder();
        for (String ss:strings){
            StringBuilder temp=new StringBuilder(ss);
            res.append(" ");
            res.append(temp.reverse());
        }
        return res.toString().trim();
    }
    
    public static void main(String[] args) {

    }
}
