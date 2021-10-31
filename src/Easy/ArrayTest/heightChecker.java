package Easy.ArrayTest;



/**
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 *
 * 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。
 *
 *  
 *
 * 示例：
 *
 * 输入：heights = [1,1,4,2,1,3]
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 **/


import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/2/4  1051. 高度检查器*/

public class heightChecker {

    public int heightChecker(int[] heights) {
        int[] arr=Arrays.copyOf(heights,heights.length);
        Arrays.sort(arr);
        int n=0;
        for (int i=0;i<arr.length;i++){
            if(arr[i]!=heights[i]) n++;
        }
        return n;
    }

    //利用桶排序，遍历的就是递增序列
    public int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {

    }
}
