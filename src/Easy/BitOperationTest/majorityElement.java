package Easy.BitOperationTest;



/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 **/

import java.util.Stack;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/17 169. 多数元素
 * @implNote 尽量发挥逻辑上的优势，使用辅助数据结构有时耗时有点点大*/
public class majorityElement {

    public static int majorityElement(int[] nums) {
        Stack<Integer> stack=new Stack<>();
        for (int i:nums){
            if(stack.isEmpty()){
                stack.push(i);
            }else if(!stack.isEmpty()&&(stack.peek()^i)==0){
                stack.push(i);
            }else {
                stack.pop();
            }
        }
        return stack.peek();
    }

    public static int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }


    public static void main(String[] args) {
        int[] nums=new int[]{3,2,1,2,3,4,5,2,3,3,3};
        System.out.println(majorityElement2(nums));
    }
}
