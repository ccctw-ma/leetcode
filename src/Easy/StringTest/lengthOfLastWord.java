package Easy.StringTest;



/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 *
 * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
 *
 *  
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/26  58. 最后一个单词的长度*/
public class lengthOfLastWord {


    public int lengthOfLastWord(String s) {

        return (s = s.trim()).length()-s.lastIndexOf(" ")-1;
    }
    public static void main(String[] args) {
        System.out.println(new lengthOfLastWord().lengthOfLastWord("Hello worlds"));
    }
}
