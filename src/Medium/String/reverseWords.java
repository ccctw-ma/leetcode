package Medium.String;


/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 进阶：
 *
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/10  */

public class reverseWords {


    public String reverseWords(String s) {
        StringBuilder builder=new StringBuilder();
        int index=s.length()-1;
        while (index>=0){
            while (index>=0&&s.charAt(index)==' ') index--;
            int temp=index;
            while (temp>=0&&s.charAt(temp)!=' ') temp--;
            builder.append(s, temp+1, index+1);
            builder.append(' ');
            index=temp;
        }
        return builder.toString().trim();
    }

    public String reverseWords2(String s) {
        String[] arr = s.split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i=arr.length - 1;i>=0;i--){
            sb.append(arr[i]).append(" ");
        }
        return sb.toString().trim();
    }


    public static void main(String[] args) {
        System.out.println(new reverseWords().reverseWords("the sky is blue"));
//        System.out.println("the".substring(0,2));
    }
}
