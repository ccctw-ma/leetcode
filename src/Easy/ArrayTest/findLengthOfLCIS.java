package Easy.ArrayTest;



/**
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。 
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 674. 最长连续递增序列 */

public class findLengthOfLCIS {


    public int findLengthOfLCIS(int[] nums) {
        int max=0,i=0,count=0;
        while (i<nums.length){
            if(++i<nums.length&&nums[i-1]<nums[i]){
                count++;
            }else {
                if(count+1>max) max=count+1;
                count=0;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {

    }
}
