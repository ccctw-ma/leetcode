package Medium.ArrayTest;


/*
* 978. 最长湍流子数组
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

返回 A 的最大湍流子数组的长度。



示例 1：

输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
示例 2：

输入：[4,8,12,16]
输出：2
示例 3：

输入：[100]
输出：1


提示：

1 <= A.length <= 40000
0 <= A[i] <= 10^9*/

/**
 * @author 马世臣
 * @// TODO: 2021/2/8
 * */

public class maxTurbulenceSize {

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if(n==1) return 1;
        int l = 0;
        int flag = -1, max = 1;
        for (int r=1;r<n;r++){
            int temp = arr[r] == arr[r-1] ? 1 : arr[r-1] < arr[r] ? 0 : 2;
            if(temp==1)
                l = r;
            else if(flag==-1||temp == flag)
                l = r-1;
            flag = temp;
            max = Math.max(max,r-l+1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new maxTurbulenceSize().maxTurbulenceSize(new int[]{
                8,8,9,10,6,8,2,4,2,2,10,6,6,
                10,10,2,3,5,1,2,10,4,2,0,9,4,9
                ,3,0,6,3,2,3,10,10,6,4,6,4,4,2,
                5,1,4,1,1,9,8,9,5,3,5,5,4,5,5,6,
                5,3,3,7,2,0,10,9,7,7,3,5,1,0,9,6,
                3,1,3,4,4,3,6,3,2,1,4,10,2,3,4,4,3
                ,6,7,6,2,1,7,0,6,8,10}));
    }
}
