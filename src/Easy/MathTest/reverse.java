package Easy.MathTest;


/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 **/

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 7. 整数反转 */

public class reverse {

    public int reverse(int x) {
        long max=Integer.MAX_VALUE;
        int flag=(x>0?1:-1);
        x=Math.abs(x);
        Queue<Integer> stack=new LinkedList<>();
        while (x!=0){
            stack.offer(x%10);
            x/=10;
        }
        long sum=0;
        while (!stack.isEmpty()){
            sum*=10;
            sum+=stack.poll();
        }
        if(Math.abs(sum)>max) return 0;
        return (int) sum*flag;
    }


    //妙呀
    public int reverse2(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;//每次取余求末尾数，然后加上之前求的数*10;如此重复，
            // 直到求出最高位数+之前所求的数
            x = x/10;       //小数点向左一位，逐位向左
        }
        return (int)n==n? (int)n:0;//判断是否溢出；若溢出返回0，否则逆转该整数

    }

    public static void main(String[] args) {

    }
}
