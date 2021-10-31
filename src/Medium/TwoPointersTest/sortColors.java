package Medium.TwoPointersTest;


/*
*
* 75. Sort Colors
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?


Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.*/

/**
 * @author 马世臣
 * @// TODO: 2020/10/7  */




public class sortColors {


    public void sortColors(int[] nums) {
        int n=nums.length;
        int l=0,index=0,r=n-1;
        while (index<=r){
            if(nums[index]==0){
                swap(nums,l,index);
                l++;
                index++;
            }else if(nums[index]==1){
                index++;
            }else {
                swap(nums,index,r);
                r--;
            }
        }

    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public void sortColors2(int[] nums) {

        // two-pass o(1) constant space not meet the constraint
        int red=0,white=0,blue=0;
        for(int i:nums){
            if(i==0) red++;
            if(i==1) white++;
            if(i==2) blue++;
        }
        int index=0;
        for(int i=0;i<red;i++) nums[index++]=0;
        for(int i=0;i<white;i++) nums[index++]=1;
        for(int i=0;i<blue;i++) nums[index++]=2;
    }


    public static void main(String[] args) {

    }
}
