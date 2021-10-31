package Easy.GreedyTest;


/**
 *
 * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
 *
 * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 *
 * 返回可以通过分割得到的平衡字符串的最大数量。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 2：
 *
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 3：
 *
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR".
 */

import java.util.Stack;

/**
 * @author 马世臣
 * @// TODO: 2020/1/14  1221. 分割平衡字符串*/

public class balancedStringSplit {

    public static int balancedStringSplit(String s) {
        char[] chars=s.toCharArray();
        Stack<Character> stack=new Stack<>();
        int num=0;
        for (int i=0;i<chars.length;i++){
            if(stack.isEmpty()||(!stack.isEmpty()&&(stack.peek()==chars[i]))){
                stack.push(chars[i]);
            }else {
                stack.pop();
                if(!stack.isEmpty()&&stack.peek()!=chars[i+1]){
                    continue;
                }else if(stack.isEmpty()){
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * int num = 0;
     *         int res = 0;
     *         for(int i=0;i<s.length();i++){
     *             if(s.charAt(i) == 'L')
     *                 num++;
     *             else
     *                 num--;
     *             if(num == 0)
     *                 res++;
     *         }
     *         return res;
     **/

    public static void main(String[] args) {
        System.out.println(balancedStringSplit("LLLLRRRR"));
    }
}
