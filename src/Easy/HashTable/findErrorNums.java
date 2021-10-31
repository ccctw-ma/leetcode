package Easy.HashTable;


/**
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 *
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/5 645. 错误的集合 */

public class findErrorNums {

    public int[] findErrorNums(int[] nums) {
        int[] bucket=new int[nums.length+1];
        bucket[0]=1;
        for (int i:nums) bucket[i]++;
        int[] res=new int[2];
        for (int i=1;i<=nums.length;i++){
            if(bucket[i]==0){
                res[1]=i;
            }else if(bucket[i]==2){
                res[0]=i;
            }
        }
        return res;
    }


    //妙呀
    public int[] findErrorNums2(int[] nums) {
        int dup = -1, missing = 1;
        for (int n: nums) {
            if (nums[Math.abs(n) - 1] < 0)
                dup = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0)
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }

    
    public static void main(String[] args) {

    }
}
