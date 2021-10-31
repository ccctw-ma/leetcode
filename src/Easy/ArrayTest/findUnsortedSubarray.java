package Easy.ArrayTest;



/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 **/

import java.util.Arrays;
import java.util.Stack;

/**
 * @author 马世臣
 * @// TODO: 2020/1/30 581. 最短无序连续子数组 */

public class findUnsortedSubarray {


    public int findUnsortedSubarray(int[] nums) {
        int[] temp= Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        while (i<nums.length&&nums[i]==temp[i]){
            i++;
        }
        if(i==nums.length){
            return 0;
        }
        while (j>i&&nums[j]==temp[j]){
            j--;
        }
        return j-i+1;
    }

    public int findUnsortedSubarray2(int[] nums) {
        Stack< Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    public int findUnsortedSubarray3(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }


    public static void main(String[] args) {
        int[] nums=new int[]{2,6,4,8,10,9,15};
        System.out.println(new findUnsortedSubarray().findUnsortedSubarray(nums));
    }
}
