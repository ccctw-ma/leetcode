package Medium.DynamicTest;



/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/16  */

public class canPartition {

    public boolean canPartition(int[] nums) {
        if (nums.length==1) return false;
        int sum=0,half;
        for (int i:nums) sum+=i;
        if(sum%2!=0) return false;
        half=sum/2;
        int[] dp=new int[half+1];
        for (int num : nums) {
            if (num <= half) {
                for (int j = half; j >= 0; j--) {
                    if (dp[j] == 1 && num + j < half) {
                        dp[num + j] = 1;
                    } else if (dp[j] == 1 && num + j == half) {
                        return true;
                    }
                }
                dp[num] = 1;
            }
        }
        return dp[half]==1;
    }


    //使用回溯算法，进行深度遍历
    public boolean canPartition2(int[] nums) {
        int sum = 0, max = 0;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, num);
        }

        if(sum % 2 != 0 || max > sum/2)
            return false;

        sum >>= 1;

        return DFS(nums, nums.length - 1, sum);
    }

    public boolean DFS(int[] nums, int start, int sum) {
        if(start < 0 || sum < nums[start]) {
            return false;
        }
        if(nums[start] == sum) {
            return true;
        }
        return DFS(nums, start - 1, sum - nums[start]) ||
                DFS(nums, start - 1, sum);
    }



    private static int gcd (int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }

    public static void main(String[] args) {
//        System.out.println(new canPartition().canPartition(new int[]{1,2,5}));
        System.out.println(gcd(100,20));
    }
}
