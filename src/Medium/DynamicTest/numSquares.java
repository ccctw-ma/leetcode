package Medium.DynamicTest;



/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/14  */

public class numSquares {


    public int numSquares(int n) {
        int size=(int)Math.sqrt(n);
        int[] nums=new int[size+1];
        for (int i=1;i<=size;i++){
            nums[i]=i*i;
        }
        int[] dp=new int[n+1];
        for (int i=1;i<=n;i++){
            int min=Integer.MAX_VALUE;
            for (int j=1;j<=size;j++){
                if(i-nums[j]>=0){
                    min=Math.min(dp[i-nums[j]]+1,min);
                }
            }
            dp[i]=min;
        }
        return dp[n];
    }


    //拉格朗日定理
    /**
     * 任何正整数都可以拆分成不超过4个数的平方和 ---> 答案只可能是1,2,3,4
     * 如果一个数最少可以拆成4个数的平方和，则这个数还满足 n = (4^a)*(8b+7) --->
     * 因此可以先看这个数是否满足上述公式，如果不满足，答案就是1,2,3了
     * 如果这个数本来就是某个数的平方，那么答案就是1，否则答案就只剩2,3了
     * 如果答案是2，即n=a^2+b^2，那么我们可以枚举a，来验证，如果验证通过则答案是2
     * 只能是3*/
    public int numSquares2(int n) {
        while(n % 4 == 0) {
            n /= 4;
        }
        //如果满足公式 则返回4
        if(n % 8 == 7) {
            return 4;
        }
        //在判断缩小后的数是否可以由一个数的平方或者两个数平方的和组成
        int a = 0;
        while ((a * a) <= n) {
            int b = (int)Math.sqrt((n - a * a));
            if(a * a + b * b == n) {
                //如果可以 在这里返回
                if(a != 0 && b != 0) {
                    return 2;
                } else{
                    return 1;
                }
            }
            a++;
        }
        //如果不行 返回3
        return 3;
    }

    public static void main(String[] args) {
        System.out.println(new numSquares().numSquares(43));
    }
}
