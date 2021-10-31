package Medium.ArrayTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 90. 子集 II
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。



示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10*/

/**
 * @author 马世臣
 * @todo 2021.3.31*/
public class subsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        int[] arr = new int[21];
        Set<Integer> set = new HashSet<>();
        for (int i:nums) {
            arr[i + 10]++;
            set.add(i);
        }

        return ans;
    }


    public static void main(String[] args) {

    }
}
