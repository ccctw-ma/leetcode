package Easy.ArrayTest;


/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 35. 搜索插入位置 */

public class searchInsert {


    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;
        for (int i=0;i<nums.length;i++){
            if(i==0&&target<nums[i]){
                return 0;
            }else if((i<nums.length-1)&&(nums[i]<target&&nums[i+1]>target)){
                return i+1;
            }else if(nums[i]==target){
                return i;
            } else if(i==nums.length-1&&nums[i]<target){
                return i+1;
            }
        }
        return 0;
    }


    /**@implNote useBinarySearch*/
    public int searchInsert2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            }
        }
        return low;
    }

    public int searchInsert3(int[] nums, int target) {
        for(int i = 0; i < nums.length;i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }
    
    public static void main(String[] args) {

    }
}
