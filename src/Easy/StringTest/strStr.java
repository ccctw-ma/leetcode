package Easy.StringTest;


/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 **/

/**@author 马世臣 
 * @// TODO: 2020/1/26 28. 实现 strStr() */
public class strStr {

    
    public int strStr(String haystack, String needle) {
        if(haystack==null&&needle==null){
            return 0;
        }else if(haystack==null||needle==null){
            if(haystack!=null&&needle==null){
                return 0;
            }else{
                return -1;
            }
        }else{
            if(needle==""&&haystack!=""){
                return 0;
            }else if(needle!=""&&haystack==null){
                return -1;
            } else if (needle == "" && haystack == "") {
                return 0;
            }
            char[] s1=haystack.toCharArray();
            char[] s2=needle.toCharArray();
            char first=s2[0];
            for (int i=0;i<s1.length;i++){
                if(s1[i]!=first) while (++i < s1.length && s1[i] != first) ;
                if(i<s1.length){
                    int j=i+1;
                    int end = j + s2.length - 1;
                    if(end<=s1.length){
                        for (int k = 1; j < end && s1[j] == s2[k]; j++, k++);
                        if (j == end) {
                            // Found whole string.
                            return i;
                        }
                    }
                }
            }

        }
        return haystack.indexOf(needle);
    }

    public int strStr2(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        int result = 0;
        if(l2==0) return 0;
        // char temp = needle.charAt(0);
        for (int i = 0; i <= l1-l2; i++) {
            for (int j = 0; j<l2; j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) break;
                if (j == l2-1) return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String s=null;
        System.out.println(new strStr().strStr2("",""));
    }
}
