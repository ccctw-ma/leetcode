package Medium.ArrayTest;


/*
* 442. 数组中重复的数据
给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

找到所有出现两次的元素。

你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

示例：

输入:
[4,3,2,7,8,2,3,1]

输出:
[2,3]
* */


import java.util.ArrayList;
import java.util.List;


/**
 * @author 马世臣
 *  @// TODO: 2020/6/23  */


public class findDuplicates {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            int x= Math.abs(nums[i]);
            if(nums[x-1]>0){
                nums[x-1]*=-1;
            }else {
                res.add(x);
            }
        }
        return res;
    }

    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        int n = nums.length;
        for(int i:nums){
            nums[(i-1)%n] += n;
        }

        for(int i=0;i<n;i++){
            if(nums[i] > 2*n)
                result.add(i+1);
        }

        return result;
    }


    public static void main(String[] args) {

    }
}
