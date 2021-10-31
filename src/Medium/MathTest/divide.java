package Medium.MathTest;

/**
 * @author msc
 * @version 1.0
 * @date 2021/10/12 9:26
 */
public class divide {


    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        boolean flag = (dividend ^ divisor) < 0;
        long a = Math.abs(dividend);
        long b = Math.abs(divisor);
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {
                a -= b << i;
                res += 1 << i;
            }
        }
        return flag ? -res : res;
    }


    public static void main(String[] args) {

    }
}
