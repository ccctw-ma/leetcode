package Medium.ArrayTest;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
*
* 18. 四数之和
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
* */


/**
 * @author 马世臣
 * @// TODO: 2020/6/13  */



public class fourSum {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums.length<4) return res;

        Arrays.sort(nums);


        //减去一些极端情况
        int minSum=nums[0]+nums[1]+nums[2]+nums[3];
        int maxSum=nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]+nums[nums.length-4];

        if(minSum>target||maxSum<target){
            return res;
        }

        for (int i=0;i<nums.length-3;i++){

            //避免出现重复元素
            int a=nums[i];
            if(i>0&&a==nums[i-1]) continue;

            for (int j=i+1;j<nums.length-2;j++){

                //避免出现重复元素
                int b=nums[j];
                if(j>i+1&&b==nums[j-1]) continue;
                int left=j+1;
                int right=nums.length-1;


                //判断两个极端，不满足直接跳过
                minSum=nums[i]+nums[j]+nums[left]+nums[left+1];
                maxSum=nums[i]+nums[j]+nums[right]+nums[right-1];

                if(minSum>target||maxSum<target) continue;

                while (left<right){
                    int sum=a+b+nums[left]+nums[right];
                    if(sum==target){
                        List<Integer> list=new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        left++;
                        right--;
                        while (left<right&&nums[left]==nums[left-1]) left++;
                        while (left<right&&nums[right]==nums[right+1]) right--;
                    }else if(sum<target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return res;
    }


    public List<List<Integer>> fourSum2(int[] nums, int target) {


        List<List<Integer>> list= new ArrayList<>();

        if(null==nums||nums.length<4){
            return list;
        }

        Arrays.sort(nums);

        int minSum=nums[0]+nums[1]+nums[2]+nums[3];
        int maxSum=nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]+nums[nums.length-4];

        if(minSum>target||maxSum<target){
            return list;
        }

        for(int i=0;i<nums.length-3;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){

                if(j>i+1&&nums[j]==nums[j-1])continue;

                int l=j+1;
                int r=nums.length-1;

                minSum=nums[i]+nums[j]+nums[l]+nums[l+1];
                maxSum=nums[i]+nums[j]+nums[r]+nums[r-1];

                if(minSum>target||maxSum<target) continue;

                while(l<r){
                    int sum=nums[i]+nums[j]+nums[r]+nums[l];
                    if(sum>target){
                        r--;
                    } else if(sum<target){
                        l++;
                    }else{
                        List<Integer> numList= new ArrayList<>();
                        numList.add(nums[i]);
                        numList.add(nums[j]);
                        numList.add(nums[r]);
                        numList.add(nums[l]);
                        list.add(numList);
                        while(l<r&&nums[l]==nums[l+1]) l++;
                        while(l<r&&nums[r]==nums[r-1]) r--;

                        l++;
                        r--;
                    }
                }

            }
        }

        return list;

    }


    /**
     * @// TODO: 2020/10/5  third time */
    public List<List<Integer>> fourSum3(int[] nums, int target) {

        List<List<Integer>> res=new ArrayList<>();
        int len=nums.length;
        if(len<4) return res;

        Arrays.sort(nums);

        int min=nums[0]+nums[1]+nums[2]+nums[3];
        int max=nums[len-1]+nums[len-2]+nums[len-3]+nums[len-4];
        if(min>target||max<target) return res;

        for (int i=0;i<len-3;i++){
            int a=nums[i];
            if(i>0&&nums[i-1]==a) continue;

            for (int j=i+1;j<len-2;j++){

                int b=nums[j];
                if(j>i+1&&nums[j-1]==b) continue;

                int l=j+1;
                int r=len-1;
                if(a+b+nums[l]+nums[l+1]>target) continue;
                if(a+b+nums[r]+nums[r-1]<target) continue;

                while (l<r){
                    int temp=a+b+nums[l]+nums[r];
                    if(temp==target){
                        List<Integer> list=new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        res.add(list);
                        l++;
                        r--;
                        while (l<r&&nums[l]==nums[l-1]) l++;
                        while (l<r&&nums[l]==nums[r+1]) r--;
                    }else if(temp>target){
                        r--;
                    }else {
                        l++;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums=new int[]{1,-2,-5,-4,-3,3,3,5};
        System.out.println(new fourSum().fourSum(nums,-11));
    }
}
