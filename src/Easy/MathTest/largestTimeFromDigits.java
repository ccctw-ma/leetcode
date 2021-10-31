package Easy.MathTest;


/**
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 *
 * 最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
 *
 * 以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4]
 * 输出："23:41"
 * 示例 2：
 *
 * 输入：[5,5,5,5]
 * 输出：""
 *  
 *
 * 提示：
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 **/


import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 949. 给定数字能组成的最大时间 */

public class largestTimeFromDigits {


    public String largestTimeFromDigits(int[] A) {
            int ans = -1;

            // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 4; ++j) if (j != i)
                    for (int k = 0; k < 4; ++k) if (k != i && k != j) {
                        int l = 6 - i - j - k;

                        // For each permutation of A[i], read out the time and
                        // record the largest legal time.
                        int hours = 10 * A[i] + A[j];
                        int mins = 10 * A[k] + A[l];
                        if (hours < 24 && mins < 60)
                            ans = Math.max(ans, hours * 60 + mins);
                    }

            return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";

    }



    //妙呀
    public String largestTimeFromDigits2(int[] A) {
        Arrays.sort(A);
        for(int i=3; i>=0; i--){
            if(A[i]>2) continue;
            for(int j=3; j>=0; j--) {
                if (j==i || A[i]==2 && A[j]>3) continue;
                for (int k=3; k>=0; k--) {
                    if (k==i || k==j || A[k]>5) continue;
                    return "" + A[i] + A[j] + ':' + A[k] + A[6-i-j-k];
                }
            }
        }
        return "";
    }
    public static void main(String[] args) {

    }
}
