package Medium.DynamicTest;


/**
 * 1191. K 次串联后最大子数组之和
 * 给你一个整数数组 arr 和一个整数 k。
 *
 * 首先，我们要对该数组进行修改，即把原数组 arr 重复 k 次。
 *
 * 举个例子，如果 arr = [1, 2] 且 k = 3，那么修改后的数组就是 [1, 2, 1, 2, 1, 2]。
 *
 * 然后，请你返回修改后的数组中的最大的子数组之和。
 *
 * 注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
 *
 * 由于 结果可能会很大，所以需要 模（mod） 10^9 + 7 后再返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2], k = 3
 * 输出：9
 * 示例 2：
 *
 * 输入：arr = [1,-2,1], k = 5
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [-1,-2], k = 7
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/7  */

public class kConcatenationMaxSum {


    public int kConcatenationMaxSum(int[] arr, int k) {
        long mod = (long) (Math.pow(10, 9) + 7);
        int minv = 0, maxv = 0, maxd = 0, sum = 0;
        for (int value : arr) {
            sum += value;
            if (sum < minv) minv = sum;
            if (sum > maxv) maxv = sum;
            if (sum - minv > maxd) maxd = sum - minv;
        }
        long k1 = maxd, k2 = maxv + sum - minv;
        long kn = (maxd + (k - 1)*(long)sum) % mod;
        return (int) ((k == 1)? k1 : (Math.max(k1, Math.max(k2, kn))));
    }


    //这个比较好理解，先求出当前数组k为1时的最大值，并求出最大前缀和和最大后缀和，
    //最后比较一下answer=Math.max(answer,(long)(k-2)*iterAdd+pre+post)就可以得出最加答案了
    public int kConcatenationMaxSum2(int[] arr, int k) {
        int len=arr.length;
        int[] sum=new int[len+1];//一定要多一位0，计算最大后缀和需要一个0
        int pre=0,post=0,iterAdd;
        int maxOne=0;
        long answer=0;
        sum[0]=0;
        for(int i=0;i<len;i++){
            sum[i+1]=arr[i]+sum[i];
            maxOne=Math.max(0,maxOne+arr[i]);
            if(maxOne>answer){
                answer=maxOne;
            }
        }
        if(k==1)return (int)answer;
        iterAdd=Math.max(0,sum[len]);
        for(int i=0;i<len;i++){
            pre=Math.max(pre,sum[i+1]);
            post=Math.max(post,sum[len]-sum[i]);
        }
        answer=Math.max(answer,(long)(k-2)*iterAdd+pre+post);//一定要用long
        return (int)(answer%1000000007);
    }



    public static void main(String[] args) {
        System.out.println(new kConcatenationMaxSum().kConcatenationMaxSum(new int[]{1,-2,1},5));
    }
}
