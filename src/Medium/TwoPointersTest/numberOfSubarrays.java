package Medium.TwoPointersTest;



/**
 * 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 *
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 *
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/21
 * */

public class numberOfSubarrays {


    public int numberOfSubarrays(int[] nums, int k) {
        int evenLeftLen,evenRightLen;
        int oddLeft=0,oddRight;
        int len=nums.length,sum=0;
        while (oddLeft<len&&(nums[oddLeft]&1)==0){
            oddLeft++;
        }
        if(oddLeft==len) return 0;
        oddRight=oddLeft;
        while (oddRight<len&&k>0){
            if((nums[oddRight]&1)==1){
                k--;
            }
            if(k>0){
                oddRight++;
            }
        }
        if(oddRight==len) return 0;
        evenLeftLen= oddLeft;
        while (oddRight<len){
            int temp=oddRight;
            while (temp+1<len&&(nums[temp+1]&1)==0){
                temp++;
            }
            evenRightLen=temp-oddRight;
            sum+=(evenLeftLen+1)*(evenRightLen+1);
            int l=oddLeft;
            oddLeft++;
            while (oddLeft<len&&(nums[oddLeft]&1)==0){
                oddLeft++;
            }
            evenLeftLen=oddLeft-l-1;
            oddRight=temp+1;
        }
        return sum;
    }


    //妙呀
    public int numberOfSubarrays2(int[] nums, int k) {
        if(nums == null || nums.length == 0) return -0;
        int ans = 0, sum = 0;
        int[] map = new int[nums.length + 1];
        map[0] = 1;
        for(int i : nums) {
            sum += i&1;
            map[sum]++;
            if(sum >= k) ans += map[sum - k];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new numberOfSubarrays().numberOfSubarrays2(new int[]{2,2,2,1,2,2,1,2,1,2},2));
    }
}
