package Medium.ArrayTest;


/* *
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/27
 * */


public class search {


    /**
     *
     * @param nums 数组
     * @param target 目标值
     * @return int 不能使用暴力搜索法
     */
    public int search(int[] nums, int target) {
        int len=nums.length;
        if(target==nums[0]) return 0;
        if(target==nums[len-1]) return len-1;
        if(target>nums[0]){
            int min=nums[0];
            int left=0,right=len-1;
            while (left<right){
                int mid=left+(right-left)/2;
                if(nums[mid]==target) return mid;
                if((target>nums[mid]&&nums[mid]<min)||target<nums[mid]){
                    right=mid-1;
                }else {
                    left=mid+1;
                }
            }
            return nums[left]==target?left:-1;
        }else {
            int max=nums[len-1];
            int left=0,right=len-1;
            while (left<right){
                int mid=left+(right-left)/2;
                if(nums[mid]==target) return mid;
                if((target<nums[mid]&&nums[mid]>max)||target>nums[mid]){
                    left=mid+1;
                }else {
                    right=mid-1;
                }
            }
            return nums[left]==target?left:-1;
        }
    }


    public static void main(String[] args) {
        System.out.println(new search().search(new int[]{4,5,6,7,0,1,2},3));
    }
}
