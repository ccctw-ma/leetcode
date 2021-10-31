package Medium.DynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 368. 最大整除子集
 * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 *
 * 如果有多个目标子集，返回其中任何一个均可。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2] (当然, [1,3] 也正确)
 * 示例 2:
 *
 * 输入: [1,2,4,8]
 * 输出: [1,2,4,8]*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/15  */

public class largestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list=new ArrayList<>();
        if(nums.length==0) return list;
        if(nums.length==1){
            list.add(nums[0]);
            return list;
        }
        Arrays.sort(nums);
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        int max=1,index=0;
        for (int i=1;i<nums.length;i++){
            for (int j=i-1;j>=0;j--){
                if(nums[i]%nums[j]==0) dp[i]=Math.max(dp[i],dp[j]+1);
            }
            if(dp[i]>max){
                max=dp[i];
                index=i;
            }
        }
        int[] res=new int[max];
        int temp=nums[index];
        for (int i=index;i>=0;i--){
            if(temp%nums[i]==0&&dp[i]==max){
                res[max-1]=nums[i];
                max--;
            }
        }
        for (int i:res) list.add(i);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new largestDivisibleSubset().largestDivisibleSubset(new int[]{1,2,4,8}));
    }
}
