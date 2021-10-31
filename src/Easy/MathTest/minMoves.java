package Easy.MathTest;



/**
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *
 * 示例:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 3
 *
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 **/

import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 453. 最小移动次数使数组元素相等 */

public class minMoves {

    public int minMoves(int[] nums) {
        if(nums.length<=1) return 0;
        if(nums.length==2) return Math.abs(nums[0]-nums[1]);
        Arrays.sort(nums);
        int count=0,max,secMax;
        while (!isAllEquals(nums)){
            for (int i=0;i<nums.length-1;i++){
                nums[i]++;
            }
            Arrays.sort(nums,0,nums.length-1);
            max=nums[nums.length-1];
            secMax=nums[nums.length-2];
            if(secMax>max){
                nums[nums.length-1]=secMax;
                nums[nums.length-2]=max;
            }
        }
        return nums[nums.length-1]-nums[nums.length-2]+count;
    }

    public boolean isAllEquals(int[] nums){
        for (int i=1;i<nums.length-1;i++){
            if(nums[i]!=nums[i-1]) return false;
        }
        return true;
    }

    public int minMoves2(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }


    public static void main(String[] args) {

    }
}
