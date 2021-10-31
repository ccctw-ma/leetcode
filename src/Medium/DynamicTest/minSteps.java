package Medium.DynamicTest;


/**
 * 650. 只有两个键的键盘
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 *
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * 说明:
 *
 * n 的取值范围是 [1, 1000] 。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/19  */

public class minSteps {


    public int minSteps(int n) {
        int[] dp=new int[n+1];
        dp[1]=0;
        for (int i=2;i<=n;i++){
            if(dp[i]==0){
                dp[i]=i;
                int j=i*2,temp=i+2;
                while (j<=n){
                    if(dp[j]==0){
                        dp[j]=temp;
                    }else {
                        dp[j]=Math.min(dp[j],temp);
                    }
                    temp+=2;
                    j*=2;
                }
            }
            int k=i*2,step=dp[i]+2;
            while (k<=n){
                if(dp[k]==0){
                    dp[k]=step;
                }else {
                    dp[k]=Math.min(dp[k],step);
                }
                step++;
                k+=i;
            }
        }
        return dp[n];
    }

    public int minSteps2(int n) {
        if(n == 1) return 0;

        for (int i = n >> 1; i >= 1; i--) {
            if(n % i == 0)
                return minSteps2(i) + n / i;
        }
        return -1;
    }



    //考到最后还是看数学
    public int minSteps3(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i=1;i<=1000;i++){
            System.out.println(new minSteps().minSteps(i));

        }
    }
}
