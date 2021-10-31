package Medium.String;


/*
* 49. 字母异位词分组
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。*/

import java.util.*;

/**
 * @author 马世臣
 * @// TODO: 2020/12/14  */


public class groupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for (String s:strs){
            String temp=String.valueOf(Arrays.stream(new char[][]{s.toCharArray()}).sorted());
            List<String> list=map.getOrDefault(temp,new ArrayList<>());
            list.add(s);
            map.put(temp,list);
        }
        return new ArrayList<>(map.values());
    }




    public static void main(String[] args) {

    }
}
