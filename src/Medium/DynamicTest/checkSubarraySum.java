package Medium.DynamicTest;


/**
 * 523. 连续的子数组和
 * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 *
 * 示例 1:
 *
 * 输入: [23,2,4,6,7], k = 6
 * 输出: True
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2:
 *
 * 输入: [23,2,6,4,7], k = 6
 * 输出: True
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * 说明:
 *
 * 数组的长度不会超过10,000。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。*/

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 马世臣
 * @// TODO: 2020/3/18  */

public class checkSubarraySum {


    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length==0) return false;
        int[] dp=new int[nums.length];
        for (int i=nums.length-1;i>=0;i--){
            dp[i]=nums[i];
            for (int j=i+1;j<nums.length;j++){
                dp[j]=dp[j-1]+nums[j];
                if(k==0&&dp[j]==0){
                    return true;
                }else if(k!=0&&dp[j]%Math.abs(k)==0){
                    return true;
                }
            }
            Arrays.fill(dp,0);
        }
        return false;
    }

    /*
    *
       在每个索引位置i, 计算当前和对k的mod值, 假设在索引x处, sum[0~x] = m*k + mod_x;
        在索引y处, sum[0~y] = n*k + mod_y; 如果mod_x == mod_y且y-x > 1说明sum[x~y]
        即为一个符合要求的连续子数组, 用map来保存每个mod值对应的索引, 一旦出现新的mod值出现
        在map中, 判断索引差是否大于1.
        注意特殊情况:
        1) 当nums中有连续0, 无论k为何值都是正确的;
        2) 除情况1之外出现k为0都是错误的;
        3) k为负数也是可能的, 但是要将其变为对应正数来求mod.
        此外需要在map中初始化<0,-1>, 其原因在于解决到达某个i时sum恰好可以整除k的情况, 选择-1
        的原因是要求连续子数组长度大于等于2, 这样可以避免第一个数字为0的情况
       */
    public boolean checkSubarraySum3(int[] nums, int k) {
        int sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new checkSubarraySum().checkSubarraySum(new int[]{23,2,4,6,7},-6));
    }
}
