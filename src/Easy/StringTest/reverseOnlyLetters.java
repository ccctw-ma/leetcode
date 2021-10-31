package Easy.StringTest;




/**
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 *
 * 提示：
 *
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122 
 * S 中不包含 \ or "
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 917. 仅仅反转字母 */

public class reverseOnlyLetters {


    public String reverseOnlyLetters(String S) {
        char[] chars=S.toCharArray();
        int i=0,j=S.length()-1;
        while (i<j){
            while (i<=j&&!Character.isLetter(chars[i])) i++;
            if(i==j+1) return String.valueOf(chars);
            while (j>=i&&!Character.isLetter(chars[j])) j--;
            char ch=chars[j];
            chars[j]=chars[i];
            chars[i]=ch;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    public String reverseOnlyLetters2(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {

    }
}
