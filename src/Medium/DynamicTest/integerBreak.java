package Medium.DynamicTest;


/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/15  */

public class integerBreak {


    public int integerBreak(int n) {
        if(n==1) return 1;
        int[] dp=new int[n+1];
        dp[1]=1;
        for (int i=2;i<=n;i++){
            int index=i/2;
            for (int j=index;j<i;j++){
                dp[i]=Math.max(dp[i],Math.max(dp[j],j)*Math.max(dp[i-j],i-j));
            }
        }
        return dp[n];
    }

    //数学定理：任何一个大于2的正整数都可以拆分为2和3组成的和，这样这些因子的乘积最大
    public int integerBreak2(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }


    public static void main(String[] args) {
        System.out.println(new integerBreak().integerBreak(10));
    }

}
