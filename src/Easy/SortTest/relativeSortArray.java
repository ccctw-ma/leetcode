package Easy.SortTest;


/**
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *  
 *
 * 提示：
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 **/

import java.util.Arrays;

/**
 *@author 马世臣
 * @// TODO: 2020/1/16 1122. 数组的相对排序
 * @implNote 可以在接下来的排序中尝试一下桶排序*/
public class relativeSortArray {

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index=0;
        for (int item:arr2){
            for (int i=index;i<arr1.length;i++){
                if(arr1[i]==item){
                    int temp=arr1[i];
                    arr1[i]=arr1[index];
                    arr1[index]=temp;
                    index++;
                }
            }
        }
        Arrays.sort(arr1,index,arr1.length);
        return arr1;
    }

    /**
     * public int[] relativeSortArray(int[] arr1, int[] arr2) {
     *         int[] nums = new int[1001];
     *         int[] res = new int[arr1.length];
     *         //遍历arr1,统计每个元素的数量
     *         for (int i : arr1) {
     *             nums[i]++;
     *         }
     *         //遍历arr2,处理arr2中出现的元素
     *         int index = 0;
     *         for (int i : arr2) {
     *             while (nums[i]>0){
     *                 res[index++] = i;
     *                 nums[i]--;
     *             }
     *         }
     *         //遍历nums,处理剩下arr2中未出现的元素
     *         for (int i = 0; i < nums.length; i++) {
     *             while (nums[i]>0){
     *                 res[index++] = i;
     *                 nums[i]--;
     *             }
     *         }
     *         return res;
     *  }*/

    public static void main(String[] args) {
        int[] arr1={2,3,1,3,2,4,6,19,9,2,7,11,15,12,19};
        int[] arr2={1,2,3,4,6,9};
        for (int i:relativeSortArray(arr1,arr2)){
            System.out.println(i);
        }
    }
}
