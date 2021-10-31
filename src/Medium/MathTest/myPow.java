package Medium.MathTest;


/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/9  */


public class myPow {


    public double myPow(double x, int n) {
        if(n==0) return 1;
        if(n==1) return x;
        if(n==2) return x*x;
        boolean flag=(n > 0);
        long temp=n;
        temp=Math.abs(temp);
        int size= (int) (Math.log(temp)/Math.log(2))+1;
        double[] dp=new double[size];
        dp[0]=x;
        for (int i=1;i<size;i++){
            dp[i]=dp[i-1]*dp[i-1];
        }

        double sum=1;
        for (int i=size-1;i>=0;i--){
            if(temp>=(int) Math.pow(2,i)){
                sum*=dp[i];
                temp%=(int)Math.pow(2,i);
            }
            if(temp==0) break;
        }
        if(flag) return sum;
        if((n&1)==0){
            return 1/Math.abs(sum);
        }
        return 1/sum;
    }

    //optimization
    public double myPow2(double x, int n) {
        double res = 1.0;
        for(int i = n; i != 0; i /= 2){
            if(i % 2 != 0){
                res *= x;
            }
            x *= x;
        }
        return  n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        System.out.println(new myPow().myPow2(2,-10));
        System.out.println(Integer.bitCount(-10));
        System.out.println(Integer.bitCount(-5/8));

    }
}
