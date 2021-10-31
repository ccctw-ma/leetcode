package Medium.DynamicTest;


/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 *
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：[100]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/24
 * */

public class maxTurbulenceSize {


    public int maxTurbulenceSize(int[] A) {
        if(A.length==1) return 1;
        int flag=0;//=->0,<->1,>->2
        int count=1,max=1;
        for (int i=1;i<A.length;i++){
            if(A[i]>A[i-1]){
                if(flag==1){
                    count++;
                }else {
                    count=2;
                }
                flag=2;
            }else if(A[i]<A[i-1]){
                if(flag==2){
                    count++;
                }else {
                    count=2;
                }
                flag=1;
            }else {
                flag=0;
            }
            max=Math.max(max,count);
        }
        return max;
    }

    public int maxTurbulenceSize2(int[] A) {
        int N = A.length;
        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < N; ++i) {
            int c = Integer.compare(A[i-1], A[i]);
            if (i == N-1 || c * Integer.compare(A[i], A[i+1]) != -1) {
                if (c != 0) ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }

        return ans;
    }



    public static void main(String[] args) {
        System.out.println(new maxTurbulenceSize().maxTurbulenceSize(new int[]{4,8,12,16}));
    }
}
