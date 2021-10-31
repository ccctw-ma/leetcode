package Easy.SortTest;



/**
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 *
 * 如果不能形成任何面积不为零的三角形，返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[2,1,2]
 * 输出：5
 * 示例 2：
 *
 * 输入：[1,2,1]
 * 输出：0
 * 示例 3：
 *
 * 输入：[3,2,3,4]
 * 输出：10
 * 示例 4：
 *
 * 输入：[3,6,2,3]
 * 输出：8
 *  
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 **/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/1/16  976. 三角形的最大周长*/
public class largestPerimeter {

    public static int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int i=A.length-1;
        while (i>=2){
            if((A[i-2]+A[i-1])>A[i]){
                return A[i-2]+A[i-1]+A[i];
            }else {
                i--;
            }
        }
        return 0;
    }

    /**
     *  public int largestPerimeter(int[] A) {
     *         int result = 0;
     *         int maxA = max(A);
     *         int maxB = max(A);
     *         int maxC = max(A);
     *         while (maxC != 0) {
     *             if (maxB + maxC > maxA) {
     *                 result = maxA + maxB + maxC;
     *                 break;
     *             } else {
     *                 maxA = maxB;
     *                 maxB = maxC;
     *                 maxC = max(A);
     *             }
     *         }
     *         return result;
     *     }
     *
     *     public int max(int[] A) {
     *         int max = 0;
     *         int maxIndex = -1;
     *         for (int i = 0; i < A.length; i++) {
     *             if (max < A[i]) {
     *                 max = A[i];
     *                 maxIndex = i;
     *             }
     *         }
     *         if (maxIndex != -1) A[maxIndex] = -1;
     *         return max;
     *     }*/

    public static void main(String[] args) {
        int[] a=new int[]{1,2,1,2,3,3,4};
        System.out.println(largestPerimeter(a));
    }
}
