package Easy.StringTest;



/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 345. 反转字符串中的元音字母 */

public class reverseVowels {


    public String reverseVowels(String s) {
        if(s==null||s.length()==0||s.length()==1){
            return s;
        }
        int i=0;
        int j=s.length()-1;
        char[] chars=s.toCharArray();
        while (i<=j){
            while (i<=j&&!isVowels(chars[i])){
                i++;
            }
            while (j>=i&&!isVowels(chars[j])){
                j--;
            }
            if(i>j){
                break;
            }
            char ch=chars[i];
            chars[i]=chars[j];
            chars[j]=ch;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    public boolean isVowels(char ch){
        if(ch=='a'||ch=='o'||ch=='i'||ch=='u'||ch=='e'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
            return true;
        }
        return false;
    }

    
    public static void main(String[] args) {
        System.out.println(new reverseVowels().reverseVowels("hello"));
    }
}
