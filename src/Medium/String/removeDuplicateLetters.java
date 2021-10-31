package Medium.String;


import java.util.Stack;

/*
* 316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同



示例 1：

输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"


提示：

1 <= s.length <= 104
s 由小写英文字母组成*/

/**
 * @author 马世臣
 * @// TODO: 2020/12/20  */



public class removeDuplicateLetters {


    public String removeDuplicateLetters(String s) {
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c=s.charAt(i);
            if(stack.contains(c))
                continue;
            while(!stack.isEmpty()&&stack.peek()>c&&s.indexOf(stack.peek(),i)!=-1)
                stack.pop();
            stack.push(c);
        }
        char[] chars = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            chars[i]=stack.get(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(new removeDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
        int[] arr=new int[]{1,2,3};


    }
}
