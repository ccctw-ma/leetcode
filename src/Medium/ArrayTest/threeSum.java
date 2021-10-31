package Medium.ArrayTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
* 15. 三数之和
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。



示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/21  */


public class threeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        if(nums.length<3) return lists;
        int min=nums[0],max=nums[0];
        for (int i:nums){
            min=Math.min(min,i);
            max=Math.max(max,i);
        }
        int[] bucket=new int[max-min+1];
        for (int i:nums) bucket[i-min]++;
        for (int i=0;i<bucket.length;i++){
            if(bucket[i]!=0){
                bucket[i]--;
                for (int j=i;j<bucket.length;j++){
                    if(bucket[j]!=0){
                        bucket[j]--;
                        int target=-i-j-3*min;
                        if(target>=j&&target<bucket.length&&bucket[target]!=0){
                            List<Integer> list=new ArrayList<>();
                            list.add(i+min);
                            list.add(j+min);
                            list.add(target+min);
                            lists.add(list);
                        }
                        bucket[j]++;
                    }
                }
                bucket[i]++;
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> nlist = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {

                if (nums[j] + nums[k] < -nums[i]) {
                    j++;

                } else if (nums[j] + nums[k] > -nums[i]) {
                    k--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    nlist.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }

        }

        return nlist;
    }






    public static void main(String[] args) {
        for (List<Integer> list:new threeSum().threeSum2(new int[]{-1, 0, 1, 2, -1, -4})){
            System.out.println(list);
        }
    }
}
