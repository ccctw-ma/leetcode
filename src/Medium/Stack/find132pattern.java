package Medium.Stack;



/**
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 *
 * 注意：n 的值小于15000。
 *
 * 示例1:
 *
 * 输入: [1, 2, 3, 4]
 *
 * 输出: False
 *
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 *
 * 输入: [3, 1, 4, 2]
 *
 * 输出: True
 *
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * 示例 3:
 *
 * 输入: [-1, 3, 2, 0]
 *
 * 输出: True
 *
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 马世臣 
 * @// TODO: 2020/3/4 456. 132模式 */

public class find132pattern {


    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;
        Stack < Integer > stack = new Stack < > ();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            min[i] = Math.min(min[i - 1], nums[i]);
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                if (!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }

    
    public static void main(String[] args) {
        System.out.println(new find132pattern().find132pattern(new int[]{3,5,0,3,4}));
        List<Integer> list=new ArrayList<>();
    }
}
