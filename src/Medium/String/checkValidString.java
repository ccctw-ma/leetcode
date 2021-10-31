package Medium.String;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/12 15:02
 */


/*
* 678. 有效的括号字符串
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:

输入: "()"
输出: True
示例 2:

输入: "(*)"
输出: True
示例 3:

输入: "(*))"
输出: True
注意:

字符串大小将在 [1，100] 范围内。*/



public class checkValidString {

    //问题出在到底是用*替换那个括号
    public boolean checkValidString(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Integer> stars = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c == '(') {
                left.push(i);
            } else if (c == ')') {
                if (left.size() == 0 && stars.size() == 0) return false;
                if (left.size() != 0) {
                    left.pop();
                } else {
                    stars.pop();
                }
            } else {
                stars.push(i);
            }
        }
        while (!left.isEmpty() && !stars.isEmpty()) {
            int leftIndex = left.pop();
            int asteriskIndex = stars.pop();
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        return left.isEmpty();

        //优化
//        Arrays.fill(chars, ' ');
//        while (!left.isEmpty()) {
//            chars[left.pop()] = '(';
//        }
//        while (!stars.isEmpty()) {
//            chars[stars.pop()] = '*';
//        }
//        int l = 0;
//        for (int i = 0; i < len; i++) {
//            char c = chars[i];
//            if (c == ' ') continue;
//            if (c == '*' && l > 0) {
//                l--;
//            } else if (c == '(') {
//                l++;
//            }
//        }
//        return l <= 0;
    }

    public boolean checkValidString2(String s) {
        // possible range
        int min = 0, max = 0; // 维护当前左括号的数量范围：[min, max]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++min;
                ++max;
            } else if (c == ')') {
                if (min > 0) min--;
                if (max-- == 0) return false;// 左括号不够
            } else {
                if (min > 0) min--; // 可作为右括号，抵消
                ++max; // 可作为左括号
            }
        }
        return min == 0;
    }

    public static void main(String[] args) {
        System.out.println(new checkValidString().checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));
//        System.out.println(new checkValidString().checkValidString("(*)"));
    }
}
