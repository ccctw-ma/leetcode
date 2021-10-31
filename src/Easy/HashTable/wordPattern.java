package Easy.HashTable;




/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 * */

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/1 290. 单词规律 */

public class wordPattern {


    public boolean wordPattern(String pattern, String str) {
        String[] strings=str.split(" ");
        if(pattern.length()!=strings.length) return false;
        HashMap<Character,Integer> map1=new HashMap<>();
        HashMap<String,Integer> map2=new HashMap<>();
        for (int i=0;i<pattern.length();i++){
            if(map1.containsKey(pattern.charAt(i))||map2.containsKey(strings[i])){
                if(!map1.getOrDefault(pattern.charAt(i),-1).equals(map2.getOrDefault(strings[i],-1))){
                    return false;
                }
            }else {
                map1.put(pattern.charAt(i),i);
                map2.put(strings[i],i);
            }
        }
        return true;
    }


    public boolean wordPattern2(String pattern, String str) {
        String[] s = str.split(" "); //以空格分隔str
        if(s.length != pattern.length()) return false; //如果没有全部成对的映射则返回false
        Map<Character, String> map = new HashMap<>(); //存放映射
        for(int i = 0; i < pattern.length(); i++){
            if(!map.containsKey(pattern.charAt(i))){ //1. 没有映射时执行
                if(map.containsValue(s[i])) return false; //2. 没有映射的情况下s[i]已被使用，则不匹配返回false
                map.put(pattern.charAt(i), s[i]); //3. 构建映射
            }else{
                if(!map.get(pattern.charAt(i)).equals(s[i])) return false; //当前字符串与映射不匹配,返回false
            }
        }
        return true;
    }
    public static void main(String[] args) {

    }


}
