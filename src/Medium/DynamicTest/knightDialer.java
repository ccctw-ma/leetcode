package Medium.DynamicTest;


/**
 * 935. 骑士拨号器
 * 国际象棋中的骑士可以按下图所示进行移动：
 *
 *  .
 *
 *
 * 这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。
 *
 * 每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。
 *
 * 你能用这种方式拨出多少个不同的号码？
 *
 * 因为答案可能很大，所以输出答案模 10^9 + 7。
 *
 *
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：10
 * 示例 2：
 *
 * 输入：2
 * 输出：20
 * 示例 3：
 *
 * 输入：3
 * 输出：46
 *
 *
 * 提示：
 *
 * 1 <= N <= 5000*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/24
 * */

public class knightDialer {

    public int knightDialer(int N) {
        if(N==1) return 10;
        int mod=1000000007;
        int[] dp=new int[10];
        Arrays.fill(dp,1);
        for (int i=1;i<N;i++){
            int[] temp=new int[10];
            temp[0]=(dp[4]+dp[6])%mod;
            temp[1]=(dp[6]+dp[8])%mod;
            temp[2]=(dp[7]+dp[9])%mod;
            temp[3]=(dp[4]+dp[8])%mod;
            temp[4]=((dp[0]+dp[3])%mod+dp[9])%mod;
            temp[5]=0;
            temp[6]=((dp[0]+dp[1])%mod+dp[7])%mod;
            temp[7]=(dp[2]+dp[6])%mod;
            temp[8]=(dp[1]+dp[3])%mod;
            temp[9]=(dp[2]+dp[4])%mod;
            dp=temp;
        }
        int sum=0;
        for (int i:dp){
            sum=(sum+i)%mod;
        }
        return sum;
    }


    //有些值是相等的，所以使用变量进行代替，使用long来减少mod运算，节省运算时间
    public int knightDialer2(int N) {
        int[] res = new int[5001];
        res[1] = 10;
        int mod = 1000_000_007;
        long f0 = 1, f1379 = 4, f28 = 2, f46 = 2;
        for (int i = 2; i < res.length; ++i) {
            long b0 = f46;
            long b1379 = (f28 << 1) + (f46 << 1);
            long b28 = f1379;
            long b46 = (f0 << 1) + f1379;
            f0 = b0 % mod;
            f1379 = b1379 % mod;
            f28 = b28 % mod;
            f46 = b46 % mod;
            res[i] = (int)((f0 + f1379 + f28 + f46) % mod);
        }
        return res[N];
    }


    public static void main(String[] args) {
        System.out.println(new knightDialer().knightDialer2(5000));
    }
}
