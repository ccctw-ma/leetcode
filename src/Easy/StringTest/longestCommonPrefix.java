package Easy.StringTest;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 *
 * */

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * 14. 最长公共前缀
 * @// TODO: 2020/1/26  */

public class longestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        int index=0;
        String s="";
        if(strs.length==0){
            return s;
        }
        List<char[]> list=new ArrayList<>();
        for (int i=0;i<strs.length;i++){
            list.add(strs[i].toCharArray());
        }
        while (true){
            if(index<list.get(0).length){
                char temp=list.get(0)[index];
                for (int i=1;i<list.size();i++){
                    if(index<list.get(i).length&&temp==list.get(i)[index]){
                        continue;
                    }else {
                        return s;
                    }
                }
                s+=temp;
                index++;
            }else {
                return s;
            }
        }
    }


    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    /**水平扫描*/
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }




    public static void main(String[] args) {
        String[] strings=new String[]{"asd","a","c"};
        System.out.println();
    }
}
