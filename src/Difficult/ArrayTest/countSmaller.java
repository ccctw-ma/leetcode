package Difficult.ArrayTest;

import java.util.ArrayList;
import java.util.List;


/*
* 315. 计算右侧小于当前元素的个数
给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

输入: [5,2,6,1]
输出: [2,1,1,0]
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/11  */


public class countSmaller {

    public List<Integer> countSmaller(int[] nums) {
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        int n=nums.length;
        if(n==0) return new ArrayList<>();
        for (int i:nums)    min=Math.min(min,i);
        for (int i=0;i<n;i++)   nums[i]-=min;
        for (int i:nums)    max=Math.max(max,i);
        int[] bucket=new int[max+1];
        int[] res=new int[n];
        for (int i=n-1;i>=0;i--){
            int a=nums[i];
            res[i]=bucket[a];
            update(bucket,a+1);
        }
        List<Integer> list=new ArrayList<>();
        for (int i:res) list.add(i);
        return list;
    }

    private void update(int[] buc,int val){
        int n=buc.length;
        for (int i=val;i<n;i++){
            buc[i]++;
        }
    }

    public List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        int min = Integer.MAX_VALUE; // nums数组最小值
        for (int value : nums) {
            if (value < min) {
                min = value;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - min + 1;
        }

        int max = Integer.MIN_VALUE;
        for (int value : nums) {
            if (value > max) {
                max = value;
            }
        }

        int[] BITree = new int[max + 1];
        BITree[0] = 0;
        int[] countArr = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int count = getSum(nums[i] - 1, BITree);
            countArr[i] = count;
            update(nums[i], BITree);
        }
        List<Integer> result = new ArrayList<>();
        for (int value : countArr) {
            result.add(value);
        }
        return result;
    }

    public static int getSum(int value, int[] BITree) { // 获得a[i]从1，value的和
        int sum = 0;
        while (value > 0) {
            sum += BITree[value];
            value -= (value & -value);
        }
        return sum;
    }

    public static void update(int value, int[] BITree) {
        while (value <= BITree.length - 1) {
            BITree[value] += 1;
            value += (value & -value);
        }
    }



    public static void main(String[] args) {
        int[] arr=new int[]{9,8,7,5,4,2,1,0};
        System.out.println(new countSmaller().countSmaller2(arr));
    }
}
