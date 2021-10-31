package Medium.Stack;


/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 **/

import java.util.Stack;

/**
 * @author 马世臣 
 * @// TODO: 2020/3/4 402. 移掉K位数字 */

public class removeKdigits {


    public String removeKdigits(String num, int k) {
        if(num.length()==k) return "0";
        int length=num.length(),size=length-k;
        Stack<Character> stack=new Stack<>();
        for (int i=0;i<num.length();i++){
            if(stack.isEmpty()){
                stack.push(num.charAt(i));
            }else if(num.charAt(i)<stack.peek()){
                //这里的判断是否完成删除任务可以使用k--来进行判断
                while (!stack.isEmpty()&&(stack.peek()>num.charAt(i)&&(size-stack.size()+1<=length-i))){
                    stack.pop();
                }
                stack.push(num.charAt(i));
            }else if(stack.size()<size){
                stack.push(num.charAt(i));
            }
        }
        StringBuilder res=new StringBuilder();
        while (!stack.isEmpty()){
            res.insert(0,stack.pop());
        }
        int i=0;
        while (i<res.length()&&res.charAt(i)=='0') i++;
        return i==res.length()?"0":res.substring(i);
    }


    //大致思路是一样的，这个为了效率没有使用栈，
    public String removeKdigits2(String num, int k) {
        if (num == null || num.length() == 0) {
            return num;
        }
        int length = num.length();
        if (k <= 0 || k > length) {
            return num;// 非法
        }
        if (k == length) {
            return "0";
        }

        char[] chars = num.toCharArray();

        char[] newChars = new char[length]; // 移除k个数字的结果
        int newCharsTop = 0;
        for (int i = 0; i < length; i++) {
            while (k > 0 && newCharsTop > 0 && newChars[newCharsTop - 1] > chars[i]) {
                newCharsTop--;
                k--; // 移除一个数字
            }
            newChars[newCharsTop] = chars[i];
            newCharsTop++;
        }
        if (k > 0) { // 从后面移除k个数字
            newCharsTop = newCharsTop - k;
            k = 0;
        }

        // 起始位置不能是0
        int startIndex = 0;
        while (newChars[startIndex] == '0' && startIndex < newCharsTop) {
            startIndex++;
        }
        // 从起始位置返回  newCharsTop - startIndex
        if (newCharsTop - startIndex > 0) {
            return new String(newChars, startIndex, newCharsTop- startIndex);
        }

        return "0";
    }
    
    public static void main(String[] args) {
        System.out.println(new removeKdigits().removeKdigits("61583276598173",5));
    }
}
