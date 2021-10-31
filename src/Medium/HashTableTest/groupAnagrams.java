package Medium.HashTableTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* 面试题 10.02. 变位词组
编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。

注意：本题相对原题稍作修改

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
通过次数23,023提交次数31,777*/

/**
 * @author 马世臣
 * 21.7.18*/
public class groupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long,List<String>> map = new HashMap<>();
        for (String s:strs){
            int[] arr = new int[26];
            for (char c:s.toCharArray()){
                arr[c-'a']++;
            }
            long index = 0;
            for (int i=0;i<26;i++){
                index+=arr[i];
                index<<=1;
            }
            List<String> list = map.getOrDefault(index,new ArrayList<>());
            list.add(s);
            map.put(index,list);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        System.out.println(new groupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
