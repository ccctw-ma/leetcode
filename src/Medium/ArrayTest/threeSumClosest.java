package Medium.ArrayTest;


/*
* 16. 最接近的三数之和
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。



示例：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。


提示：

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/6/12  */


public class threeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff=Integer.MAX_VALUE;
        int ans=target;
        for (int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            int j=i+1;
            int k=nums.length-1;
            while (j<k){
                int sum=nums[j]+nums[k]+nums[i];
                if(sum==target) return target;
                int cur=Math.abs(target-sum);

                if(cur<diff){
                    diff=cur;
                    ans=sum;
                }

                if(sum<target){
                    j++;
                    while (j<k&&nums[j]==nums[j-1]) j++;
                }else if(sum>target){
                    k--;
                    while (j<k&&nums[k]==nums[k+1]) k--;
                }

            }

        }
        return ans;
    }



    public static void main(String[] args) {
        System.out.println(new threeSumClosest().threeSumClosest(new int[]{-1,2,1,-4},1));
    }
}
