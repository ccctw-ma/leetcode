package Easy.StackTest;

import java.util.Stack;

/*
* 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
* 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
示例 1：
输入："(()())(())"
输出："()()()"
解释：
输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
示例 2：
输入："(()())(())(()(()))"
输出："()()()()(())"
解释：
输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
删除每隔部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
示例 3：
输入："()()"
输出：""
解释：
输入字符串为 "()()"，原语化分解得到 "()" + "()"，
删除每个部分中的最外层括号后得到 "" + "" = ""。
提示：
S.length <= 10000
S[i] 为 "(" 或 ")"
S 是一个有效括号字符串
*/

/***2020.1.3 by Msc 1021删除在外层括号*/
public class removeOuterParentheses {


    /**
     * class Solution {
     *     public String removeOuterParentheses(String S) {
     *
     *         int left = 0;
     *         StringBuilder res = new StringBuilder();
     *         for (int i = 0; i < S.length(); i++) {
     *             if (S.charAt(i) == '(' && left++ > 0)
     *                 res.append('(');
     *             if (S.charAt(i) == ')' && --left > 0)
     *                 res.append(')');
     *         }
     *         return res.toString();
     *     }
     * }**/

    private static String removeOuterParentheses(String s){
        Stack<Character> stack=new Stack<>();
        StringBuilder stringBuilder=new StringBuilder();
        int index=0,temp;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stack.push('(');
            } else if(ch==')'){
                stack.pop();
                if(stack.size()==0){
                    temp=i;
                    stringBuilder.append(s, index+1, temp);
                    index=temp+1;
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("()()"));

    }


}
