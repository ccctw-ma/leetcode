package Easy.StringTest;




/**
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 *
 * 示例:
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 要求:
 *
 * 该字符串只包含小写的英文字母。
 * 给定字符串的长度和 k 在[1, 10000]范围内。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 541. 反转字符串 II */


public class reverseStr {



    //熟练掌握api
    public String reverseStr(String s, int k) {
        StringBuilder res=new StringBuilder();
        for (int i=0;i<s.length();i+=2*k){
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2=new StringBuilder();
            if(s.length()-i<2*k){
                if(s.length()-i<k){
                    s1=new StringBuilder(s.substring(i));
                }else {
                    s1=new StringBuilder(s.substring(i,i+k));
                    s2=new StringBuilder(s.substring(i+k));
                }
            }else {
                s1=new StringBuilder(s.substring(i,i+k));
                s2=new StringBuilder(s.substring(i+k,i+2*k));
            }
            res.append(s1.reverse());
            res.append(s2);
        }
        return res.toString();
    }




    public String reverseStr2(String s, int k) {
        if(s == null || s.length() == 0) return s;

        char[] array = s.toCharArray();
        // loop every 2k
        for(int start=0; start < s.length(); start += 2*k){
            int i = start;
            int j = Math.min(start + k - 1, array.length-1);
            while(i<j){
                char tmp = array[i];
                array[i++] = array[j];
                array[j--] = tmp;
            }
        }
        return new String(array);
    }

    public static void main(String[] args) {

    }
}
