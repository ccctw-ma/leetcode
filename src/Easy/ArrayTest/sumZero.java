package Easy.ArrayTest;


/**
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：[-7,-1,1,3,4]
 * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：[-1,0,1]
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1000
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/5 1304. 和为零的N个唯一整数 */

public class sumZero {

    public int[] sumZero(int n) {
        int[] arr=new int[n];
        for (int i=0;i<n/2;i++){
            arr[2*i]=i+1;
            arr[2*i+1]=-(i+1);
        }
        if(n%2!=0) arr[n-1]=0;
        return arr;
    }

    public int[] sumZero2(int n) {
        int[] ans=new int[n];
        int k=1;
        for(int i=n/2-1,j=n/2;i>-1&&j<n;i--,j++) {
            ans[i]=k; ans[j]=-k;
            k++;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
