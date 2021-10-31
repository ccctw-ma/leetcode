package Medium.GreadyTest;

import java.util.*;


/*
*
* 659. 分割数组为连续子序列
给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。

如果可以完成上述分割，则返回 true ；否则，返回 false 。



示例 1：

输入: [1,2,3,3,4,5]
输出: True
解释:
你可以分割出这样两个连续子序列 :
1, 2, 3
3, 4, 5


示例 2：

输入: [1,2,3,3,4,4,5,5]
输出: True
解释:
你可以分割出这样两个连续子序列 :
1, 2, 3, 4, 5
3, 4, 5


示例 3：

输入: [1,2,3,4,4,5]
输出: False


提示：

输入的数组长度范围为 [1, 10000]*/

/**
 * @author 马世臣
 * @// TODO: 2020/12/4  */



public class isPossible {


    public boolean isPossible(int[] nums) {
        int n=nums.length;
        if(n<3) return false;
        Set<Integer> set=new HashSet<>();
        for(int i:nums)
            set.add(i);
        int size=set.size();
        int[][] arr=new int[set.size()][2];
        int index=0;
        arr[index][0]=nums[0];
        arr[index][1]=1;
        for(int i=1;i<n;i++){
            if(arr[index][0]==nums[i]){
                arr[index][1]++;
            }else{
                arr[++index][0]=nums[i];
                arr[index][1]=1;
            }
        }
        int start=0,tar=0;
        while(start<size){
            boolean flag=true;
            int count=0;
            for(int i=tar;i<size;i++){
                if(count==0){
                    count++;
                    arr[i][1]--;
                }else if(arr[i-1][0]+1==arr[i][0]){
                    if(arr[i-1][1]>=1&&arr[i][1]==1){
                        break;
                    }else{
                        count++;
                        arr[i][1]--;
                    }
                }else if(arr[i-1][0]+1!=arr[i][0]&&count<=2){
                    return false;
                }
                if(flag&&arr[i][1]!=0){
                    tar=i;
                    flag=false;
                }
            }
            if(count<3){
                System.out.println(Arrays.deepToString(arr));
                return false;
            }
            if(count>=3&&arr[size-1][1]==0) return true;
            start=tar;
        }
        return true;
    }

    class Solution {
        // difficulty
        public boolean isPossible(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
            Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();
            for (int x : nums) {
                int count = countMap.getOrDefault(x, 0) + 1;
                countMap.put(x, count);
            }
            for (int x : nums) {
                int count = countMap.getOrDefault(x, 0);
                if (count > 0) {
                    int prevEndCount = endMap.getOrDefault(x - 1, 0);
                    if (prevEndCount > 0) {
                        countMap.put(x, count - 1);
                        endMap.put(x - 1, prevEndCount - 1);
                        endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                    } else {
                        int count1 = countMap.getOrDefault(x + 1, 0);
                        int count2 = countMap.getOrDefault(x + 2, 0);
                        if (count1 > 0 && count2 > 0) {
                            countMap.put(x, count - 1);
                            countMap.put(x + 1, count1 - 1);
                            countMap.put(x + 2, count2 - 1);
                            endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }



    public static void main(String[] args) {

    }
}
