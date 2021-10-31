package Easy.HashTable;



/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/1 205. 同构字符串 */

public class isIsomorphic {

    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Character> map=new HashMap<>();
        for (int i=0;i<s.length();i++){
            char cs=s.charAt(i);
            char ts=t.charAt(i);
            if(map.containsKey(cs)&&map.get(cs)!=ts){
                return false;
            }else if(!map.containsKey(cs)&&map.containsValue(ts)){
                return false;
            }else if(!map.containsKey(cs)){
                map.put(cs,ts);
            }
        }
        return true;
    }


    //含有字符串的使用桶排序比较搞笑
    public boolean isIsomorphic2(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        int[] s2t = new int[128];
        int[] t2s = new int[128];

        for(int i = 0; i < ss.length; i++) {
            if(s2t[ss[i]] == 0) {
                s2t[ss[i]] = tt[i];
            }else if(s2t[ss[i]] != tt[i]) {
                return false;
            }
            if(t2s[tt[i]] == 0) {
                t2s[tt[i]] = ss[i];
            }else if(t2s[tt[i]] != ss[i]) {
                return false;
            }
        }

        return true;
    }


    //妙呀
    public boolean isIsomorphic3(String s, String t) {
        char[] s2t = new char[127];
        char[] t2s = new char[127];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        int len = s.length();
        for(int i =0;i<len;i++){
            if(s2t[S[i]]!='\0' || t2s[T[i]]!='\0'){
                if(s2t[S[i]]!=T[i]){
                    return false;
                }
            }else{
                s2t[S[i]]=T[i];
                t2s[T[i]]=S[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        
    }
}
