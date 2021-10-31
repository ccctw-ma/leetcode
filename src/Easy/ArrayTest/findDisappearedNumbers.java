package Easy.ArrayTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/30 448. 找到所有数组中消失的数字 */

public class findDisappearedNumbers {


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] array=new int[nums.length];
        for (int i:nums){
            array[i-1]++;
        }
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<array.length;i++){
            if(array[i]==0){
                list.add(i+1);
            }
        }
        return list;
    }



    //原地置反

        public List<Integer> findDisappearedNumbers2(int[] nums) {

            // Iterate over each of the elements in the original array
            for (int i = 0; i < nums.length; i++) {

                // Treat the value as the new index
                int newIndex = Math.abs(nums[i]) - 1;

                // Check the magnitude of value at this new index
                // If the magnitude is positive, make it negative
                // thus indicating that the number nums[i] has
                // appeared or has been visited.
                if (nums[newIndex] > 0) {
                    nums[newIndex] *= -1;
                }
            }

            // Response array that would contain the missing numbers
            List<Integer> result = new LinkedList<Integer>();

            // Iterate over the numbers from 1 to N and add all those
            // that have positive magnitude in the array
            for (int i = 1; i <= nums.length; i++) {

                if (nums[i - 1] > 0) {
                    result.add(i);
                }
            }

            return result;
        }


    public static void main(String[] args) {

    }
}

