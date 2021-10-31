package Easy.StackTest;



/***
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 注意:
 *
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 *
  */


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**496. 下一个更大元素 I  2020.1.9*/
public class nextGreaterElement {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums2.length>0){
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<nums2.length;i++){
                map.put(nums2[i],-1);
            }
            Stack<Integer> stack=new Stack<>();
            stack.push(nums2[0]);
            for(int i=1;i<nums2.length;i++){
                if(nums2[i]>stack.peek()){
                    map.replace(stack.peek(),nums2[i]);
                    stack.pop();
                    while (!stack.empty()){
                        if(nums2[i]>stack.peek()){
                            map.replace(stack.peek(),nums2[i]);
                            stack.pop();
                        }else {
                            break;
                        }
                    }
                }
                stack.push(nums2[i]);
            }
            for(int i=0;i<nums1.length;i++){
                nums1[i]=map.get(nums1[i]);
            }
        }
        return nums1;
    }


    /***
     *  public int[] nextGreaterElement(int[] findNums, int[] nums) {
     *         Stack < Integer > stack = new Stack < > ();
     *         HashMap < Integer, Integer > map = new HashMap < > ();
     *         int[] res = new int[findNums.length];
     *         for (int i = 0; i < nums.length; i++) {
     *             while (!stack.empty() && nums[i] > stack.peek())
     *                 map.put(stack.pop(), nums[i]);
     *             stack.push(nums[i]);
     *         }
     *         while (!stack.empty())
     *             map.put(stack.pop(), -1);
     *         for (int i = 0; i < findNums.length; i++) {
     *             res[i] = map.get(findNums[i]);
     *         }
     *         return res;
     *     }
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public static void main(String[] args) {
        //int[] nums1 = {4,1,2}, nums2= {1,3,4,2};
        int[] nums1 = {2,4}, nums2= {1,2,3,4};
        for (Integer integer:nextGreaterElement(nums1,nums2)){
            System.out.println(integer);
        }
    }
}
