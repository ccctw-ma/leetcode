package Medium.DynamicTest;



/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/26  */

public class lastStoneWeightII {


    public int lastStoneWeightII(int[] stones) {
        if (stones.length==1) return stones[0];
        if (stones.length==2) return Math.abs(stones[0]-stones[1]);
        int len=stones.length;
        int[][] dp=new int[len][len];
        for (int i=0;i<len;i++) Arrays.fill(dp[i],Integer.MAX_VALUE);
        int a,b,c;
        for (int i=0;i<len;i++){
            dp[i][i]=stones[i];
            for (int j=i;j>=0;j--){
                a=(j==0?0:dp[0][j-1]);
                b=(i-j<=1?0:dp[j+1][i-1]);
                c=Math.abs(stones[i]-stones[j]);
                dp[j][i]=Math.min(dp[j][i],Math.abs(c-b));
                int min=Math.min(Math.min(Math.abs(Math.abs(a-c)-b),Math.abs(Math.abs(c-b)-a)),Math.abs(Math.abs(a-b)-c));
                dp[0][i]=Math.min(dp[0][i],min);
            }
        }
        return dp[0][len-1];
    }


    /**
     * 如果把一个例子写成算式，会发现其实是用加号和减号把石头门的重量连起来，并使结果最小：
     * 例子[2,7,4,1,8,1]中：
     * “组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
     * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
     * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
     * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     * ”
     * 即
     * 1-（（4-2）-（8-7））
     * 也就是
     * 1+2+8-4-7
     *
     * 换一种想法，就是 将这些数字分成两拨，使得他们的和的差最小
     *
     * 在进一步，可以变成 选出一些数字，使得它们最逼近整个数组和除以二的值，而最后的结果，就是abs（这个数-总和除以二）*2
     **/
    public int lastStoneWeightII2(int[] stones) {
        int sum=0;
        for(int st:stones) sum+=st;
        for(int i=(sum>>1);;i--){
            if(helper(stones,0,0,i))
                return sum-2*i;
        }
    }


    private boolean helper(int[] nums, int idx, int sum, int target){
        if(sum==target) return true;
        if(sum>target) return false;
        if(idx==nums.length) return false;
        return helper(nums,idx+1,sum+nums[idx],target) ||helper(nums,idx+1,sum,target);
    }

    public static void main(String[] args) {
        System.out.println(new lastStoneWeightII().lastStoneWeightII(new int[]{2,7,4,1,8,1,2,3,4,5,6,7,8,9}));
    }
}
