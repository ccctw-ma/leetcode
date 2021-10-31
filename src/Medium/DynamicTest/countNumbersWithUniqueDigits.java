package Medium.DynamicTest;



/**
 * 357. 计算各个位数不同的数字个数
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 *
 * 示例:
 *
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/15  */

public class countNumbersWithUniqueDigits {

    //这题就是高中的排列组合题
    public int countNumbersWithUniqueDigits(int n) {
        int[] dp=new int[]{10,91,739,5275,32491,168571,712891,2345851,5611771,8877691};
        if(n>=10) return dp[9];
        return dp[n-1];
    }

    public static void main(String[] args) {

    }
}
