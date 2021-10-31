package Easy.MathTest;


/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 *
 * 输入：16
 * 输出：True
 * 示例 2：
 *
 * 输入：14
 * 输出：False
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 367. 有效的完全平方数 */

public class isPerfectSquare {


    public boolean isPerfectSquare(int num) {
        long i=1;
        while (i*i<num) i++;
        return num==i*i;
    }


    //二分法
    public boolean isPerfectSquare2(int num) {
        if (num < 2) {
            return true;
        }

        long left = 2, right = num / 2, x, guessSquared;
        while (left <= right) {
            x = left + (right - left) / 2;//防溢出
            guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            }
            if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }


    //牛顿法
    public boolean isPerfectSquare3(int num) {
        if (num < 2) return true;

        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }


    public static void main(String[] args) {
        System.out.println(new isPerfectSquare().isPerfectSquare(Integer.MAX_VALUE));
    }
}
