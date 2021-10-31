package Easy.StringTest;



/**
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 1071. 字符串的最大公因子 */

public class gcdOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        if(str1.length()==str2.length()) {
            if(str1.equals(str2)){
                return str1;
            }else {
                return "";
            }
        }else if(str1.length() > str2.length()){
            if(!str1.contains(str2)||str1.indexOf(str2)!=0||str1.lastIndexOf(str2)!=str1.length()-str2.length()){
                return "";
            }else {
                String s=str1.substring(str2.length());
                String temp=s.length()>str2.length()?str2:s;
                int i=temp.length();
                while (str1.length()%i!=0||str2.length()%i!=0) {
                    i--;
                }
                return temp.substring(0,i);
            }
        }else{
            if(!str2.contains(str1)||str2.indexOf(str1)!=0||str2.lastIndexOf(str1)!=str2.length()-str1.length()){
                return "";
            }else {
                String s=str2.substring(str1.length());
                String temp=s.length()>str1.length()?str1:s;
                int i=temp.length();
                while (str1.length()%i!=0||str2.length()%i!=0) {
                    i--;
                }
                return temp.substring(0,i);
            }
        }
    }


    //辗转相除，递归求解 最后一步len1==0时表示两个字符串相同
    public String gcdOfStrings2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        if(len1 == 0)
            return str2;
        if(len1 < len2)
            return gcdOfStrings2(str2, str1);
        if(!str1.startsWith(str2))
            return "";
        return gcdOfStrings2(str1.substring(len2,len1), str2);
    }

    public static void main(String[] args) {
        //System.out.println(new gcdOfStrings().gcdOfStrings("ABABABAB","ABABABABABABAB"));
        System.out.println("1.1.1.1".replace(".","[.]"));
    }

}
