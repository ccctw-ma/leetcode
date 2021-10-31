package Medium.DynamicTest;


/**
 * 1218. 最长定差子序列
 * 给你一个整数数组 arr 和一个整数 difference，请你找出 arr 中所有相邻元素之间的差等于给定 difference 的等差子序列，并返回其中最长的等差子序列的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/7*/


public class longestSubsequence {


    public int longestSubsequence(int[] arr, int difference) {
        if(arr.length==1) return 1;
        int[] dp=new int[20001];
        int max=1;
        for (int i:arr){
            if(i-difference<=10000&&i-difference>=-10000){
                dp[i+10000]=dp[i-difference+10000]+1;
                max=Math.max(max,dp[i+10000]);
            }else {
                dp[i+10000]=1;
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
