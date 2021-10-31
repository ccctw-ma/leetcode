package Easy.StringTest;


/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 125. 验证回文串 */

public class isPalindrome {
    public boolean isPalindrome(String s) {
        if(s.length()==1||s.length()==0){
            return true;
        }
        char[] chars=s.toCharArray();
        int i=0,j=chars.length-1;
        while (i<=j){
            while ((i<chars.length)&&(chars[i]<'A'||chars[i]>'Z')&&(chars[i]<'a'||chars[i]>'z')&&(chars[i]<'0'||chars[i]>'9')){
                i++;
            }
            if(i==(j+1)){
                return true;
            }
            while ((j>=0)&&(chars[j]<'A'||chars[j]>'Z')&&(chars[j]<'a'||chars[j]>'z')&&(chars[j]<'0'||chars[j]>'9')){
                j--;
            }
            if(i<=j&&(chars[i]==chars[j]||(Character.isLetter(chars[i])&&Character.isLetter(chars[j])&&Math.abs(chars[i]-chars[j])==32))){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println('0'-'P');
        System.out.println(new isPalindrome().isPalindrome("0P"));

    }
}
