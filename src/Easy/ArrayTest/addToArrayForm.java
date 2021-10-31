package Easy.ArrayTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/4 989. 数组形式的整数加法 */

public class addToArrayForm {


    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list=new ArrayList<>();
        int i=A.length-1,carry=0,left,right,sum;
        while (i>=0||K!=0){
            left=0;
            right=0;
            if(i>=0){
                left=A[i--];
            }
            if(K!=0){
                right=K%10;
                K/=10;
            }
            sum=left+right+carry;
            list.add(sum%10);
            carry=sum/10;
        }
        if(carry==1) list.add(1);
        Collections.reverse(list);
        return list;
    }


    //直接把数组的具体数字加到K上,不必再在纠结进位问题
    public List<Integer> addToArrayForm2(int[] A, int K) {
        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList<>();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {

    }
}
