package Medium.BackTrackingTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
  46. 全排列
  给定一个 没有重复 数字的序列，返回其所有可能的全排列。

  示例:

  输入: [1,2,3]
  输出:
  [
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
  ]*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/25
 * */


public class permute {

    private List<List<Integer>> list;
    private int len;
    private List<List<Integer>> permute(int[] nums) {
        this.list=new ArrayList<>();
        len=nums.length;
        Arrays.sort(nums);
        if(len==0){
            list.add(new ArrayList<>());
            return list;
        }else {
            backTrack(nums,new ArrayList<>(),0);
            return list;
        }
    }

    private void backTrack(int[] nums,List<Integer> integers,int len){
        if(len==this.len){
            this.list.add(new ArrayList<>(integers));
            return;
        }
        for (int i:nums){
            if(!integers.contains(i)){
                integers.add(i);
                backTrack(nums,integers,len+1);
                integers.remove(len);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new permute().permute(new int[]{1,1,1,2,2}));
    }
}
