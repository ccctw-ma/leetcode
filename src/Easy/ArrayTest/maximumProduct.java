package Easy.ArrayTest;




/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 **/


import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 628. 三个数的最大乘积 */

public class maximumProduct {


    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int length=nums.length;
        if(nums[0]*nums[length-1]<0){
            return nums[length-1]*nums[length-2]*nums[length-3];
        }else {
            int left=nums[0]*nums[1];
            int right=nums[length-2]*nums[length-3];
            return left>right?nums[length-1]*left:nums[length-1]*right;
        }
    }

    //在方法一中，我们实际上只要求出数组中最大的三个数以及最小的两个数，
    // 因此我们可以不用排序，用线性扫描直接得出这五个数。o(n) o(1)
    public int maximumProduct2(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


    public static void main(String[] args) {

    }
}
