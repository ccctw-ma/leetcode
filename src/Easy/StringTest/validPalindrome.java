package Easy.StringTest;


/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 680. 验证回文字符串 Ⅱ
 * */

public class validPalindrome {

    public boolean validPalindrome(String s) {
        char[] chars=s.toCharArray();
        int i=0,j=chars.length-1;
        boolean flag=true;
        while (i<j){
            if(chars[i]!=chars[j]){
                if(flag&&(i+1==j||i==j-1)){
                    return true;
                }else if(flag&&i+1<j&&chars[i+1]==chars[j]&&chars[i+2]==chars[j-1]){
                    i++;
                    flag=false;
                }else if(flag&&i<j-1&&chars[i]==chars[j-1]&&chars[i+1]==chars[j-2]){
                    j--;
                    flag=false;
                }else {
                    return false;
                }
            }else {
                i++;
                j--;
            }

        }
        return true;
    }


    //递归进行验证
    public boolean validPalindrome2(String s) {
        int i = 0,j = s.length() -1;
        char[] chars = s.toCharArray();
        while(i<j){
            if(chars[i]==chars[j]){
                i++;
                j--;
            }else{
                return isVaild(chars,i+1,j) || isVaild(chars,i,j-1) ;
            }
        }
        return true;
    }

    private boolean isVaild(char[] chars ,int i,int j){
        while(i<j){
            if(chars[i++]!=chars[j--]){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new validPalindrome().validPalindrome("cupuuuupucu"));
    }
}
