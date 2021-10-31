package Match.match187;


/**
 * 5401. 是否所有 1 都至少相隔 k 个元素
 * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：nums = [1,0,0,0,1,0,0,1], k = 2
 * 输出：true
 * 解释：每个 1 都至少相隔 2 个元素。
 * 示例 2：
 *
 *
 *
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：false
 * 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。
 * 示例 3：
 *
 * 输入：nums = [1,1,1,1,1], k = 0
 * 输出：true
 * 示例 4：
 *
 * 输入：nums = [0,1,0,1], k = 1
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] 的值为 0 或 1*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/3  */



public class kLengthApart {


    public boolean kLengthApart(int[] nums, int k) {
        int index=0;
        while (index<nums.length&&nums[index]==0) index++;
        if(index==nums.length) return true;
        int i=index+1;
        while (i<nums.length){
            while (i<nums.length&&nums[i]==0) i++;
            if(i==nums.length) break;
            if((i-index)<k) return false;
            index=i;
            i++;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
