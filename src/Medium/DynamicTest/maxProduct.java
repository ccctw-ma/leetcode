package Medium.DynamicTest;


/**
 * 152. 乘积最大子序列
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。*/

/**
 * @author 马世臣
 * @TODO: 2020/3/13  */
public class maxProduct {

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);

            max = Math.max(max, imax);
        }
        return max;

    }


    /**
     * @implNote 思路： 求最大值，可以看成求被0拆分的各个子数组的最大值。
     *
     * 当一个数组中没有0存在，则分为两种情况：
     *
     * 1.负数为偶数个，则整个数组的各个值相乘为最大值；
     *
     * 2.负数为奇数个，则从左边开始，乘到最后一个负数停止有一个“最大值”，从右边也有一个“最大值”，比较，得出最大值。*/
    public int maxProduct2(int[] nums) {

        int a=1;
        int max=nums[0];
        for (int i = 0; i < nums.length; i++) {

            a*=nums[i];
            if (max<a)
                max=a;
            if (nums[i]==0)
                a=1;
        }
        a=1;
        for (int i = nums.length-1; i >=0 ; i--) {
            a*=nums[i];
            if (max<a)
                max=a;
            if (nums[i]==0)
                a=1;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new maxProduct().maxProduct(new int[]{-1,2,3,4,-2,1,2,-4,3,-2}));
    }
}
