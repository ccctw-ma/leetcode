package Easy.HashTable;


/**
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 *
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 * 说明: 输入的数组长度最大不超过20,000.
 **/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/5 594. 最长和谐子序列 */

public class findLHS {

    public int findLHS(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int res=0;
        for (int i:nums){
            if(map.containsKey(i+1)){
                int temp=map.get(i)+map.get(i+1);
                if(temp>res) res=temp;
            }
        }
        return res;
    }

    public int findLHS2(int[] nums) {
        Arrays.sort(nums);
        int begin = 0,res = 0;
        for(int end = 0;end < nums.length;end++){
            while(nums[end] - nums[begin] > 1)
                begin++;
            if(nums[end] - nums[begin] == 1)
                res = Math.max(res,end - begin + 1);
        }
        return res;
    }
    /*int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        for (int i:nums){
            if(i<min) min=i;
            if(i>max) max=i;
        }
        int[] bucket=new int[max-min+1];
        for (int i:nums){
            bucket[i-min]++;
        }
        int res=0;
        for (int i=1;i<bucket.length;i++){
            if(bucket[i]!=0&&bucket[i-1]!=0){
                int temp=bucket[i]+bucket[i-1];
                if(temp>res) res=temp;
            }
        }
        return res;*/
    
    public static void main(String[] args) {

    }
}
