package Medium.DynamicTest;



/**
 * 801. 使序列递增的最小交换次数
 * 我们有两个长度相等且不为空的整型数组 A 和 B 。
 *
 * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
 *
 * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
 *
 * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
 *
 * 示例:
 * 输入: A = [1,3,5,4], B = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 * 注意:
 *
 * A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。
 * A[i], B[i] 均为 [0, 2000]区间内的整数。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/3/21  */

public class minSwap {


    //动态规划
    public int minSwap(int[] A, int[] B) {
        if(A.length==1) return 0;
        int[][] dp=new int[A.length][2];//0不交换，1交换
        dp[0][1]=1;
        for (int i=1;i<A.length;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for (int i=1;i<A.length;i++){
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dp[i][0]=dp[i-1][0];
                dp[i][1]=dp[i-1][1]+1;
            }
            if(A[i]>B[i-1]&&B[i]>A[i-1]){
                dp[i][0]=Math.min(dp[i][0],dp[i-1][1]);
                dp[i][1]=Math.min(dp[i][1],dp[i-1][0]+1);
            }
        }
        return Math.min(dp[A.length - 1][0], dp[A.length - 1][1]);
    }

//    回溯算法超时，没有找到合适的减枝方法
//    int min=Integer.MAX_VALUE;
//    public int minSwap(int[] A, int[] B) {
//        if(A.length==1) return 0;
//        swap(A,B,1,0);
//        if(A[0]!=B[0]){
//            change(A,B,0);
//            swap(A,B,1,1);
//        }
//        return min;
//    }
//
//    private void swap(int[] A,int[] B,int index,int step){
//        if(index==A.length){
//            System.out.println(step+" "+ Arrays.toString(B) +" "+ Arrays.toString(A));
//            min=Math.min(min,step);
//            return;
//        }
//        if(A[index]>A[index-1]&&B[index]>B[index-1]){
//            swap(A,B,index+1,step);
//        }
//        if(A[index]>B[index-1]&&B[index]>A[index-1]&&A[index]!=B[index]){
//            change(A,B,index);
//            swap(A,B,index+1,step+1);
//        }
//
//    }
//
//    private void change(int[] A,int[] B,int index){
//        int temp=A[index];
//        A[index]=B[index];
//        B[index]=temp;
//    }

    public int minSwap2(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0, s1 = 1;
        for (int i = 1; i < A.length; ++i) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }

    public static void main(String[] args) {
        long begin=System.currentTimeMillis();
        System.out.println(new minSwap().minSwap(new int[]{4,10,13,14,17,19,21,24,26,27,28,29,34,37,38,42,44,46,48,51,52,53,54,57,58,59,64,65,66,67,71,73,75,76,80,81,82,83,86,88,89,90,95,97,98,99,101,105,106,108,109,110,111,112,115,119,121,122,123,124,125,126,127,128,129,130,131,133,136,138,143,145,147,149,150,153,158,160,163,164,165,167,168,169,172,173,174,176,178,179,183,184,186,188,189,192,193,194,198,200},new int[]{0,1,3,5,6,7,11,13,15,16,17,21,37,39,41,42,43,45,47,50,53,55,56,57,64,66,67,68,69,70,71,72,74,75,76,77,79,80,87,88,89,95,96,97,98,100,101,105,106,107,108,112,113,115,116,118,119,122,124,125,126,127,128,131,135,136,137,138,139,140,144,145,148,150,151,154,159,160,161,162,163,167,168,170,171,174,176,178,179,180,181,185,187,189,190,191,192,198,199,200}));
        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }
}
