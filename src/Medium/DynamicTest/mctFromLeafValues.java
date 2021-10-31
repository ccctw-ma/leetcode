package Medium.DynamicTest;


/**
 * 1130. 叶值的最小代价生成树
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 *
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 *
 *
 * 示例：
 *
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：
 * 有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 *
 * 提示：
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/27  */

public class mctFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int len=arr.length;
        if(len==2) return arr[0]*arr[1];
        int[][] dp=new int[len][len];
        for (int i=0;i<len;i++) Arrays.fill(dp[i],Integer.MAX_VALUE);
        dp[0][0]=0;
        for (int i=1;i<len;i++){
            dp[i][i]=0;
            for (int j=i-1;j>=0;j--){
                for (int k=j;k<i;k++){
                    int max1=0,max2=0;
                    for (int a=j;a<=k;a++) max1=Math.max(max1,arr[a]);
                    for (int b=k+1;b<=i;b++) max2=Math.max(max2,arr[b]);
                    dp[j][i]=Math.min(dp[j][i],max1*max2+dp[j][k]+dp[k+1][i]);
                }
            }
        }
        return dp[0][len-1];
    }

    public int mctFromLeafValues2(int[] arr) {
        //仿照最小生成树的思想，最大的值放在最迟执行
        int[] s = new int[arr.length];
        int top = -1;
        int sum = 0;
        for (int num : arr) {
            if (top > -1 && s[top] < num) {
                //出栈所有比当前小的元素
                int a = s[top--];
                while (top > -1 && s[top] < num) {
                    int b = s[top--];
                    sum += a * b;
                    a = b;
                }
                sum += a * num;
            }
            s[++top] = num;
        }
        //处理剩余元素
        while (top > 0) {
            int a = s[top--];
            int b = s[top--];
            sum += a * b;
            if (top == -1) {
                break;
            }
            s[++top] = b;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new mctFromLeafValues().mctFromLeafValues2(new int[]{6,2,4}));
    }

}
