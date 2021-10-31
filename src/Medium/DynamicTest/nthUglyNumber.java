package Medium.DynamicTest;



/**
 * 264. 丑数 II
 * 编写一个程序，找出第 n 个丑数。
 *
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1 是丑数。
 * n 不超过1690。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/14  */

public class nthUglyNumber {



    //三指针这个思想太强了
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int temp ;
        for (int i = 1; i < n; i++) {
            temp = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = temp;
            if (temp == nums[i2] * 2) {
                i2++;
            }
            if (temp == nums[i3] * 3) {
                i3++;
            }
            if (temp == nums[i5] * 5) {
                i5++;
            }
        }

        return nums[n - 1];
    }

    public static void main(String[] args) {

    }
}
