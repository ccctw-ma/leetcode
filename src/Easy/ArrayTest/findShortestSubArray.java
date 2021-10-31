package Easy.ArrayTest;



/**
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 *
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释: 
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 *
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 *
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 **/


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/1 697. 数组的度 */

public class findShortestSubArray {


    public int findShortestSubArray(int[] nums) {
        int[] bucket=new int[50001];
        for (int i:nums){
            bucket[i]++;
        }
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int i:bucket){
            if(i>max) max=i;
        }
        for (int i=0;i<bucket.length;i++){
            if(bucket[i]==max){
                int temp=findNLength(i,nums);
                if(temp<min) min=temp;
            }
        }
        return min;
    }

    public int findNLength(int n,int[] nums){
        int i=0,j=nums.length-1;
        while (i<=j&&nums[i]!=n) i++;
        while (i<=j&&nums[j]!=n) j--;
        return j-i+1;
    }


    //巧用hashmap
    public int findShortestSubArray2(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>(),
                right = new HashMap<>(), count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            left.putIfAbsent(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
    public int findShortestSubArray3(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);//节省数组空间
        }

        int[] leftMap = new int[max + 1];//用于元素最左索引（+1）
        int[] rightMap = new int[max + 1];//用于元素最右索引
        int[] countMap = new int[max + 1];//用于元素计数
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (leftMap[num] == 0) {
                leftMap[num] = i + 1;
            }
            rightMap[num] = i;
            countMap[num]++;
            if (countMap[num] > maxCount) {
                maxCount = countMap[num];
            }
        }

        int res = nums.length;
        for (int num = 0; num < countMap.length; num++) {
            int count = countMap[num];
            if (count == maxCount) {
                int length = rightMap[num] - leftMap[num] + 2;
                if (length < res) {
                    res = length;
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {

    }
}
