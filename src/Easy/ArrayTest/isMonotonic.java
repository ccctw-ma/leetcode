package Easy.ArrayTest;




/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 *
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 *
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 *
 * 输入：[1,1,1]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/2/3 896. 单调数列 */

public class isMonotonic {


    public boolean isMonotonic(int[] A) {
        if(A.length<=1) return true;
        int i=0;
        while (++i<A.length&&A[i]==A[i-1]);
        if(i==A.length) return true;
        boolean flag= (A[i] - A[i - 1]) > 0;
        int j=i+1;
        while (j<A.length){
            if(A[j]-A[j-1]==0){
                j++;
                continue;
            }
            if(flag!=(A[j] - A[j - 1]) > 0){
                return false;
            }
            j++;
        }
        return true;
    }

    public boolean isMonotonic2(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i+1]);
            if (c != 0) {
                if (c != store && store != 0)
                    return false;
                store = c;
            }
        }
        return true;
    }


    public static void main(String[] args) {

    }
    
}
