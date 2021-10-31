package Easy.ArrayTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 * 示例 1:
 *
 * 输入: [3, 1, 4, 1, 5], k = 2
 * 输出: 2
 * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * 示例 2:
 *
 * 输入:[1, 2, 3, 4, 5], k = 1
 * 输出: 4
 * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * 示例 3:
 *
 * 输入: [1, 3, 1, 5, 4], k = 0
 * 输出: 1
 * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
 * 注意:
 *
 * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 * 数组的长度不超过10,000。
 * 所有输入的整数的范围在 [-1e7, 1e7]。
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/1/30 532. 数组中的K-diff数对 */

public class findPairs {


    public int findPairs(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        int n=0;
        for (int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        for (Integer i:map.keySet()){
            if(k==0){
                if(map.get(i)>=2){
                    n++;
                }
                continue;
            }
            if(map.containsKey(i-k)&&map.get(i-k)!=-1){
                n++;
            }
            if(map.containsKey(i+k)&&map.get(i+k)!=-1){
                n++;
            }
            map.put(i,-1);
        }
        return n;
    }


    //太妙了，语言形容不出来
    public int findPairs2(int[] nums, int k) {
        if(k < 0) return 0;
        Arrays.sort(nums);
        int start = 0, count = 0, prev = 0x7fffffff;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[start] > k || prev == nums[start]) {
                if (++start != i) i--;
            }else if (nums[i] - nums[start] == k) {
                prev = nums[start++];
                count++;
            }
        }
        return count;
    }

    public int arrayPairSum(int[] nums) {
        int[] hash = new int[20001];
        for (int e : nums) hash[e + 10000]++;
        boolean tag = false;//这个tag是用来记录元素个数的奇偶，因为每次只要奇数位置的元素
        int res = 0;
        for (int i = 0; i < 20001; i++) {
            if (hash[i] == 0) continue;
            res += (hash[i] + (tag ? 0 : 1)) / 2 * (i - 10000);
            if (hash[i] % 2 == 1) tag = !tag;
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] a=new int[]{3,1,4,1,5};
        System.out.println(new findPairs().findPairs2(a,2));
    }
}
