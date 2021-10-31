package Medium.BackTrackingTest;

import java.util.ArrayList;
import java.util.List;



/*
* 17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/27
 * */

public class letterCombinations {


    private List<String> res;
    public List<String> letterCombinations(String digits) {
        res=new ArrayList<>();
        if(digits.length()==0) return res;
        char[][] chars=new char[][]{{'a','b','c'},{'d','e','f'},{'g','h','i'},
                {'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        track(new StringBuilder(),digits,0,chars);
        return res;
    }

    private void track(StringBuilder temp,String digits,int index,char[][] chars){
        if(index==digits.length()){
            res.add(temp.toString());
            return;
        }
        int n=digits.charAt(index)-'2';
        for (char c:chars[n]){
            temp.append(c);
            track(temp,digits,index+1,chars);
            temp.deleteCharAt(index);
        }
    }




    public static void main(String[] args) {
        System.out.println(new letterCombinations().letterCombinations("23456789"));

        new Thread(() -> {
            try {
                StringBuilder s= new StringBuilder();
                while (true){
                    s.append('2');
                    Thread.sleep(1000);
                    System.out.println(new letterCombinations().letterCombinations(s.toString()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();
    }
}
