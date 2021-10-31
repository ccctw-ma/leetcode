package Medium.DynamicTest;


/**
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例 :
 *
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * 注意：
 *
 * 给出数对的个数在 [1, 1000] 范围内。*/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 马世臣
 * @// TODO: 2020/3/18  */

public class findLongestChain {



    //
    public int findLongestChain(int[][] pairs) {
        if(pairs.length<=1) return pairs.length;
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]-o2[0]<0){
                    return -1;
                }else if(o1[0]==o2[0]&&o1[1]<o2[1]){
                    return -1;
                }
                return 0;
            }
        });
        int[] dp=new int[pairs.length];
        dp[0]=1;
        int max=1;
        for (int i=1;i<dp.length;i++){
            dp[i]=1;
            for (int j=i-1;j>=0;j--){
                if(pairs[i][0]>pairs[j][1]&&dp[j]+1>dp[i]){
                    dp[i]=dp[j]+1;
                    max=Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }

//    if(pairs.length<=1) return pairs.length;
//        int[][] dp=new int[pairs.length][2];
//        dp[0][0]=1;
//        dp[0][1]=-1;//dp[0,1]表示前一个与之相连的数对的索引，没有就是-1;
//        int max=1;
//        for (int i=1;i<dp.length;i++){
//            for (int j=i-1;j>=0;j--){
//                if(pairs[i][0]>pairs[j][1]&&dp[j][0]+1>dp[i][0]){
//                    dp[i][0]=dp[j][0]+1;
//                    dp[i][1]=j;
//                }else if(pairs[i][1]<pairs[j][0]){
//                    int index=j,pre=j;
//                    while (index!=-1&&pairs[i][1]<pairs[index][0]){
//                        pre=index;
//                        index=dp[index][1];
//                    }
//                    if(index==-1){
//                        dp[i][0]=1;
//                        dp[i][1]=-1;
//                        dp[j][0]++;
//                        dp[pre][1]=i;
//                    }else if(pairs[i][0]>pairs[index][1]&&pairs[i][1]<pairs[pre][0]){
//                        dp[i][0]=dp[index][0]+1;
//                        dp[i][1]=index;
//                        dp[pre][1]=i;
//                        dp[pre][0]=dp[i][0]+1;
//                        dp[j][0]++;
//                    }
//                }
//            }
//        }
//        for (int[] ints : dp) max = Math.max(max, ints[0]);
//        return max;



    //贪心算法更加高效，典型的根据开始和结束时间进行最大化使用的案例
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs,(a,b)-> a[1]-b[1]);
        int res = 1,tmp = pairs[0][1];
        for(int i = 1;i < pairs.length;i++){
            if(pairs[i][0] > tmp){
                res++;
                tmp = pairs[i][1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] nums=new int[][]{{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        System.out.println(Arrays.deepToString(nums));
    }
}
