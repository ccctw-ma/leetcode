package Easy.ArrayTest;




/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 **/


/**
 * @author 马世臣
 * @// TODO: 2020/1/29 283. 移动零 */

public class moveZeroes {


    public void moveZeroes(int[] nums) {
        int nullZero=0,zero=0,length=nums.length;
        while (nullZero<length&&zero<length){
            while (nullZero<length&&nums[nullZero]==0) nullZero++;
            while (zero<nums.length&&nums[zero]!=0) zero++;
            if(zero<length&&nullZero<length){
                nums[zero]=nums[nullZero];
                nums[nullZero]=0;
                zero++;
                nullZero++;
            }
        }
    }


    //只要找到非零的就接着填值，最后补0就行了
    public void moveZeroes2(int[] nums) {
        int i=0,j=0;
        int count=0;
        while(j<nums.length){
            if(nums[j]!=0)
                nums[i++]=nums[j++];
            else{
                j++;
                count++;
            }
        }
        for(int k=0;k<count;k++){
            nums[i+k]=0;
        }
    }

    public static void main(String[] args) {

    }
}
