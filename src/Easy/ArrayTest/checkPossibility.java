package Easy.ArrayTest;



/**
 *给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 *
 * 示例 1:
 *
 * 输入: [4,2,3]
 * 输出: True
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: [4,2,1]
 * 输出: False
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * 说明:  n 的范围为 [1, 10,000]。
 * */

/**
 * @author 马世臣
 * @// TODO: 2020/1/31 665. 非递减数列 */

public class checkPossibility {


    public boolean checkPossibility(int[] nums) {
        int i=0,j=nums.length-1;
        while (i+1<nums.length&&nums[i]<=nums[i+1]){
            i++;
        }
        if(i==j) return true;
        //now nums[i]>nums[i+1]
        while (j-1>=0&&nums[j-1]<=nums[j]){
            j--;
        }
        //now nums[j-1]>nums[j]
        if(j-i>1)   return false;
        if(i==0||j==nums.length-1){
            return true;
        }else {
            int max=Math.max(nums[i],nums[j]);
            int min=Math.min(nums[i],nums[j]);
            if((max>nums[i-1]&&max>nums[j+1])&&(min<nums[i-1]&&min<nums[j+1])){
                return false;
            }else {
                return true;
            }
        }
    }

    public boolean checkPossibility2(int[] nums) {
        boolean flag = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (!flag) {
                    return false;
                }
                flag = false;
                if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i - 1] = nums[i];
                }
                //这两次替换是将当前矛盾的排序进行调整的最佳方案
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{-1,4,2,3};
        System.out.println(new checkPossibility().checkPossibility(nums));
    }
}
