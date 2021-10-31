package Easy.ArrayTest;


/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 167. 两数之和 II - 输入有序数组 */

public class twoSum {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<numbers.length;i++){
            map.put(numbers[i],i);
        }
        for (int i=0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])&&i<map.get(target-numbers[i])){
                return new int[]{i+1,map.get(target-numbers[i])+1};
            }
        }
        return new int[2];
    }

    /**
     * @implNote 双指针
     * class Solution {
     * public:
     *     vector<int> twoSum(vector<int>& numbers, int target) {
     *         int low = 0, high = numbers.size() - 1;
     *         while (low < high) {
     *             int sum = numbers[low] + numbers[high];
     *             if (sum == target)
     *                 return {low + 1, high + 1};
     *             else if (sum < target)
     *                 ++low;
     *             else
     *                 --high;
     *         }
     *         return {-1, -1};
     *     }
     * };
     **/

    
    public static void main(String[] args) {

    }
}
