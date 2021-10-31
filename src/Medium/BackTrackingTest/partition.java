package Medium.BackTrackingTest;

import java.util.ArrayList;
import java.util.List;


/*
* 131. 分割回文串
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]*/


/**
 * @author 马世臣
 * @// TODO: 2021/3/7
 * */

public class partition {

    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        trace(s,new ArrayList<>(),0);
        return res;
    }

    private void trace(String s, List<String> list,int index){
        if(index==s.length()){
            res.add(new ArrayList<>(list));
        }else {
            for (int i=index;i<s.length();i++){
                String temp = s.substring(index,i+1);
                if(isPalindrome(temp)){
                    list.add(temp);
                    trace(s,list,i+1);
                    list.remove(list.size()-1);
                }
            }
        }

    }



    private boolean isPalindrome(String s){
        int l = 0, r = s.length()-1;
        while (l<r){
            if(s.charAt(l)!=s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new partition().partition("aab"));
    }
}
