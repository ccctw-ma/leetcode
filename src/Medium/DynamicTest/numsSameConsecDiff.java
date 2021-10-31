package Medium.DynamicTest;


/**
 * 967. 连续差相同的数字
 * 返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
 *
 * 请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
 *
 * 你可以按任何顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：N = 3, K = 7
 * 输出：[181,292,707,818,929]
 * 解释：注意，070 不是一个有效的数字，因为它有前导零。
 * 示例 2：
 *
 * 输入：N = 2, K = 1
 * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * 提示：
 *
 * 1 <= N <= 9
 * 0 <= K <= 9*/

import java.util.*;

/**
 * @author 马世臣
 * @// TODO: 2020/3/24  */

public class numsSameConsecDiff {

    private Set<String> set;
    public int[] numsSameConsecDiff(int N, int K) {
        if(N==1) return new int[]{0,1,2,3,4,5,6,7,8,9};
        set=new HashSet<>();
        for (int i=1;i<10;i++){
            search(new StringBuilder(),i,N,K);
        }
        int[] res=new int[set.size()];
        int i=0;
        for (String s:set){
            res[i++]=Integer.valueOf(s);
        }
        return res;
    }

    private void search(StringBuilder s,int peek,int n,int k){
        if(n==0){
            set.add(s.toString());
            return;
        }
        if(peek>=0&&peek<10){
            s.append(peek);
            search(new StringBuilder(s),peek+k,n-1,k);
            search(new StringBuilder(s),peek-k,n-1,k);
        }
    }




    //数值在int的范围内使用int进行运算回比String类型快
    private List<Integer> lis = new ArrayList<>();
    public int[] numsSameConsecDiff2(int N, int K) {
        if (N <= 0) return new int[0];
        if (N == 1) lis.add(0);
        for (int i = 1; i <= 9; i ++) {
            subNum(N - 1, K, i);
        }
        int[] res = new int[lis.size()];

        for (int i = 0; i < lis.size(); i ++) {
            res[i] = lis.get(i);
        }
        return res;
    }
    private void subNum(int n, int k, int pre) {
        if (n == 0) {
            lis.add(pre);
            return;
        }
        int last = pre % 10;
        if (last - k >= 0) subNum(n - 1, k, pre * 10 + last - k);
        if (last + k < 10 && last + k != last - k) subNum(n - 1, k, pre * 10 + last + k);
    }









    public static void main(String[] args) {
        System.out.println(Arrays.toString(new numsSameConsecDiff().numsSameConsecDiff(2, 1)));
    }
}
