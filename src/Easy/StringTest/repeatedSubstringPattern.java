package Easy.StringTest;


/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: "aba"
 *
 * 输出: False
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 459. 重复的子字符串
 * */


public class repeatedSubstringPattern {


    public boolean repeatedSubstringPattern(String s) {
        if(s.length()==0){
            return false;
        }
        int i=1;
        while ((i<=s.length())&&s.lastIndexOf(s.substring(0,i))!=s.length()-i) i++;
        if(i==s.length()){
            return false;
        }
        String pattern=s.substring(0,i);
        for (int j=pattern.length();j<s.length();j+=pattern.length()){
            if(s.indexOf(pattern,j)!=j){
                return false;
            }
        }
        return true;
    }


    //妙呀，每个子字符串的长度肯定是字符串的因子，可以二分遍历这些长度的子字符串来进行校验
    public boolean repeatedSubstringPattern2(String s) {
        int len = s.length();

        for (int i = len / 2; i > 0; i--) {
            if (len % i != 0) {
                continue;
            }

            boolean flag = true;
            for (int j = len / i; j > 1; j--) {
                if (!s.substring(0, i).equals(s.substring((j - 1) * i, j * i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    
    public static void main(String[] args) {
        System.out.println(new repeatedSubstringPattern().repeatedSubstringPattern2("abcdabcdabcd"));
        //System.out.println("abcabcabc".lastIndexOf("abc"));
    }
}
