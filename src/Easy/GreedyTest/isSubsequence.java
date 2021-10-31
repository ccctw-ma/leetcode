package Easy.GreedyTest;



/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 * 后续挑战 :
 *
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢:
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 **/


import java.util.IdentityHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author 马世臣
 * @// TODO: 2020/1/15  392. 判断子序列
 * @implNote 最好使用indexof函数来查询某一字符在字符串的位置，比较高效*/
public class isSubsequence {

    public static boolean isSubsequence(String s, String t) {
        Map<Character, PriorityQueue<Integer>> map=new IdentityHashMap<>();
        for (int i=0;i<t.length();i++){
            if(!map.containsKey(t.charAt(i))){
                PriorityQueue<Integer> p=new PriorityQueue<>();
                p.offer(i);
                map.put(t.charAt(i),p);
            }else {
                map.get(t.charAt(i)).offer(i);
            }
        }
        int index=Integer.MIN_VALUE;
        for (int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                while (!(map.get(s.charAt(i)).isEmpty())){
                    int temp=map.get(s.charAt(i)).poll();
                    if(temp<index){
                        if(map.get(s.charAt(i)).isEmpty()){
                            return false;
                        }
                    }else {
                        index=temp;
                        break;
                    }
                }

            }else {
                return false;
            }
        }
        return true;
    }

    public static boolean isSubsequence2(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()){
            index = t.indexOf(c, index+1);
            if (index == -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence2("agcaa","ahbaggdccaa"));
    }
}
