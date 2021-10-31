package Difficult.ArrayTest;


/*
* 41. 缺失的第一个正数
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。



示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1


提示：

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/26  */


public class firstMissingPositive {


    /**
     * @implNote 将整个数组作为一个hash表，数字i放在i-1的下标处
     * 对于不满条件的数据进行交换，知道该数字与下标对应或者数字超出限定
     */
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; ++ i) {
            while(nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i]-1]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }


    public static void main(String[] args) {
        System.out.println(new firstMissingPositive().firstMissingPositive(new int[]{3,4,-1,1}));
    }
}
