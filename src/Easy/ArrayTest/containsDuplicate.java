package Easy.ArrayTest;



/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 **/

import java.util.HashSet;
import java.util.Set;

/**
 * @author 马世臣
 * @// TODO: 2020/1/29 217. 存在重复元素 */

public class containsDuplicate {


    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int i:nums){
            if(set.contains(i)){
                return true;
            }
            set.add(i);
        }
        return false;
    }


    //bucketSort
    public boolean containsDuplicate2(int[] nums) {
        if(nums.length <= 1) return false;
        int min = nums[0], max = nums[0];
        for(int num : nums) {
            min = min < num ? min : num;
            max = max > num ? max : num;
        }
        // System.out.print(min + "  " + max + "\n");
        boolean[] repeat = new boolean[max - min + 1];
        for(int num : nums) {
            if(repeat[num - min]) return true;
            else repeat[num - min] = true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }



    public static void main(String[] args) {

    }
}
