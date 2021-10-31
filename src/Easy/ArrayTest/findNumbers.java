package Easy.ArrayTest;


/**
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [12,345,2,6,7896]
 * 输出：2
 * 解释：
 * 12 是 2 位数字（位数为偶数） 
 * 345 是 3 位数字（位数为奇数）  
 * 2 是 1 位数字（位数为奇数） 
 * 6 是 1 位数字 位数为奇数） 
 * 7896 是 4 位数字（位数为偶数）  
 * 因此只有 12 和 7896 是位数为偶数的数字
 * 示例 2：
 *
 * 输入：nums = [555,901,482,1771]
 * 输出：1 
 * 解释： 
 * 只有 1771 是位数为偶数的数字。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 10^5
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/5 1295. 统计位数为偶数的数字 */


public class findNumbers {

    
    public int findNumbers(int[] nums) {
        int sum=0;
        for (int i:nums){
            if((i/10>=1&&i/10<=9)||(i/1000>=1&&i/1000<=9)||(i==100000)) sum++;
        }
        return sum;
    }

    //通过求log来算位数
    public int findNumbers2(int[] nums) {
        int count = 0;
        for (int i : nums) {
            if ((log(i, 10) + 1) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int log(int value, int base) {
        double b = Math.log(value) / Math.log(base);
        return (int) b;
    }

    public static void main(String[] args) {

    }
}
