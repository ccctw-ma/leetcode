package Medium.ArrayTest;


/*
* 1300. 转变数组后最接近目标值的数组和
给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

请注意，答案不一定是 arr 中的数字。



示例 1：

输入：arr = [4,9,3], target = 10
输出：3
解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
示例 2：

输入：arr = [2,3,5], target = 10
输出：5
示例 3：

输入：arr = [60864,25176,27249,21296,20204], target = 56803
输出：11361


提示：

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/14
 * */


public class findBestValue {

    public int findBestValue(int[] arr, int target) {
        int max = arr[0];
        for(int i = 1; i< arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int min = 0;
        while(min < max) {
            int mid = (min+max)/2;
            int sum = sum(arr, mid);
            if(sum < target) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        //反复横跳
        int sum1 = sum(arr, min);
        int sum2 = sum(arr, min-1);
        if(sum1 - target < target-sum2)
            return min;
        return min-1;
    }

    public int sum(int[] arr, int num) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum+=Math.min(arr[i], num);
        }
        return sum;
    }




    public static void main(String[] args) {
        System.out.println(new findBestValue().findBestValue(new int[]{4,9,3},10));
    }
}
