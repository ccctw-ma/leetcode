package Medium.ArrayTest;


/*
* 1014. 最佳观光组合
给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

返回一对观光景点能取得的最高分。



示例：

输入：[8,1,5,2,6]
输出：11
解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11


提示：

2 <= A.length <= 50000
1 <= A[i] <= 1000*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/17
 * */


public class maxScoreSightseeingPair {

    public int maxScoreSightseeingPair(int[] arr) {
        int max=arr[0];
        int res=Integer.MIN_VALUE;
        for (int i=1;i<arr.length;i++){
            res=Math.max(res,max+arr[i]-i);
            max=Math.max(max,arr[i]+i);
        }
        return res;
    }

    /*
    暴力破解超时
    * int max=Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            for (int j=i-1;j>=0;j--){
                int sum=arr[i]+arr[j]+j-i;
                max=Math.max(max,sum);
            }
        }
        return max;*/



    public static void main(String[] args) {
        int[] arr=new int[]{8,1,5,2,6};
        System.out.println(new maxScoreSightseeingPair().maxScoreSightseeingPair(arr));
    }
}
