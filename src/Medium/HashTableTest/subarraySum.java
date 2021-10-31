package Medium.HashTableTest;

import java.util.HashMap;
import java.util.Map;


/*
* 560. 和为K的子数组
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/15  */


public class subarraySum {


    public int subarraySum(int[] nums, int k) {
        if(nums.length==0) return 0;
        int count=0;
        for(int i=1;i<nums.length;i++){
            nums[i]+=nums[i-1];
        }
        Map<Integer,Integer> map=new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num - k)) {
                count += map.get(num - k);
            }
            map.put(num,map.getOrDefault(num,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE+" "+10000*30000);
    }
}
