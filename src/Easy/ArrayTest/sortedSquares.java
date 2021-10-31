package Easy.ArrayTest;



/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 **/

import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 977. 有序数组的平方 */

public class sortedSquares {


    public int[] sortedSquares(int[] A) {
        int min=A[0],i=0;
        if(min>=0){
            for (int j=0;j<A.length;j++){
                A[j]=A[j]*A[j];
            }
            return A;
        }
        while (i<A.length&&A[i]<0){
            A[i]=-A[i];
            i++;
        }
        if(i<A.length){
            min=-min;
            while (i<A.length&&A[i]<min) i++;
        }
        Arrays.sort(A,0,i);
        for (int j=0;j<A.length;j++){
            A[j]=A[j]*A[j];
        }
        return A;
    }

    //双指针
    public int[] sortedSquares2(int[] A) {
        int l =0,r=A.length-1,idx = r;
        int[] res = new int[A.length];
        while (l<=r){
            if (-A[l]>A[r]){
                res[idx--] = A[l]*A[l++];
            }else {
                res[idx--] = A[r]*A[r--];
            }
        }
        return res;
    }
    
    public static void main(String[] args) {

    }
}
