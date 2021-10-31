package Easy.BitOperationTest;


/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 * 示例 1:
 *
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 *
 * 输入: a = -2, b = 3
 * 输出: 1
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/17  371. 两整数之和
 * */
public class getSum {

    public static int getSum(int a, int b) {
        int sum, carry;
        while (b!=0) {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
    public static int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        int sum = a ^ b;            //加操作
        int carry = (a & b) << 1;   //进位操作
        return add(sum, carry);     //将加操作结果加上进位操作结果
    }

    public static void main(String[] args) {
        System.out.println(getSum(-2,3));
    }
}
