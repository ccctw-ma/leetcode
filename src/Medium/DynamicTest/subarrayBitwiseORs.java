package Medium.DynamicTest;


/**
 * 898. 子数组按位或操作
 * 我们有一个非负整数数组 A。
 *
 * 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B 中的每个元素进行按位或操作，获得结果 A[i] | A[i+1] | ... | A[j]。
 *
 * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
 *
 *
 *
 * 示例 1：
 *
 * 输入：[0]
 * 输出：1
 * 解释：
 * 只有一个可能的结果 0 。
 * 示例 2：
 *
 * 输入：[1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 * 示例 3：
 *
 * 输入：[1,2,4]
 * 输出：6
 * 解释：
 * 可能的结果是 1，2，3，4，6，以及 7 。
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9*/

import java.util.HashSet;
import java.util.Set;

/**
 * @author 马世臣
 * @// TODO: 2020/3/22
 * */

public class subarrayBitwiseORs {


    public int subarrayBitwiseORs(int[] A) {
        int len=A.length;
        if(len==1) return 1;
        int[][] dp=new int[len][len];
        Set<Integer> set=new HashSet<>();
        int max= (int) (Math.pow(2,30)-1);
        for (int i=0;i<len;i++){
            dp[i][i]=A[i];
            set.add(A[i]);
        }
        for (int i=len-1;i>=0;i--){
            for (int j=i+1;j<len;j++){
                if(dp[i][j-1]<max){
                    dp[i][j]=dp[i][j-1]|A[j];
                    set.add(dp[i][j]);
                }
            }
        }
        return set.size();
    }


    public int subarrayBitwiseORs2(int[] A) {
        HashSet<Integer> set = new HashSet<>();
        int n = A.length;
        for(int i = 0; i < n; i++){
            set.add(A[i]);
            for(int j = i - 1; j >= 0; j--){
                if((A[i] & A[j]) == A[i]) break;//这个说明，A[j]已经为32位全是一个状态了，无需进行后续操作了
                A[j] |= A[i];   //这里的A[j]的值表示从A[j]|...|A[i]的结果，存贮下来下一轮使用 dp思想
                set.add(A[j]);  //比如这里i=4,那么A[j] j>=0&&j<=3 就表示A[0]|..|A[4] A[1]|..|A[4] A[2]|..|A[4] A[3]|A[4]
             }
        }
        return set.size();
    }

    public static void main(String[] args) {
//        System.out.println(new subarrayBitwiseORs().subarrayBitwiseORs(new int[]{1}));
        System.out.println( Integer.bitCount(1));
    }
}
