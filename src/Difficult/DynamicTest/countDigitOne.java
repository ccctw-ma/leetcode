package Difficult.DynamicTest;

/**
 * @author msc
 * @version 1.0
 * @date 2021/8/13 15:49
 */

/*233. 数字 1 的个数
给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。



示例 1：

输入：n = 13
输出：6
示例 2：

输入：n = 0
输出：0


提示：

0 <= n <= 2 * 109

* */


public class countDigitOne {

    /*
     *  9->1
     *  99-> 20
     *  999-> 300
     *  9999-> 4000
     *  99999 -> 50000
     *  999999-> 600000
     *   ...
     *  199999999 -> 260000000
     * */
    public int countDigitOne(int n) {
        int[] arr = new int[]{1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000};
        int temp = n % 10, origin = n;
        int index = 0, multiply = 10, count = 0;
        if (temp >= 1) count++;
        n /= 10;
        while (n != 0) {
            temp = n % 10;
            if (temp == 1) {
                count++;
                count += arr[index];
                count += (origin % multiply);
            } else if (temp > 1) {
                count += multiply;
                count += (arr[index] * temp);
            }
            n /= 10;
            multiply *= 10;
            index++;
        }
        return count;
    }

    /*主要考虑每一个位置上1出现的次数*/
    public int countDigitOne2(int n) {
        int res = 0;
        for (int i = 1; i <= n; i *= 10) {
            int div = i * 10;
            res += (n / div) * i + Math.min(Math.max(n % div - i + 1, 0), i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new countDigitOne().countDigitOne(11111));
    }
}
