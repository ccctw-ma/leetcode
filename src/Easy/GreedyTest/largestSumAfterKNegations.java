package Easy.GreedyTest;

import java.util.Arrays;


/**
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 *
 * 以这种方式修改数组后，返回数组可能的最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [4,2,3], K = 1
 * 输出：5
 * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 * 示例 2：
 *
 * 输入：A = [3,-1,0,2], K = 3
 * 输出：6
 * 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
 * 示例 3：
 *
 * 输入：A = [2,-3,-1,5,-4], K = 2
 * 输出：13
 * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/14  1005. K 次取反后最大化的数组和
 * @implNote 就是每次对最小的值取反*/
public class largestSumAfterKNegations {
    public static int largestSumAfterKNegations(int[] A, int K){
        Arrays.sort(A);
        int num=0;
        for (int i=0;i<A.length;i++){
            if(A[i]<0){
                num++;
            }else {
                break;
            }
        }
        if(K<=num){
            for (int i=0;i<K;i++){
                A[i]=-A[i];
            }
        }else {
            for (int i=0;i<num;i++){
                A[i]=-A[i];
            }
            if((K-num)%2==1){
                Arrays.sort(A);
                A[0]=-A[0];
            }
        }
        int sum=0;
        for (int i:A){
            sum+=i;
        }
        return sum;
    }

    /**
     *  int[] number = new int[201];//-100 <= A[i] <= 100,这个范围的大小是201
     *         for (int t : A) {
     *             number[t + 100]++;//将[-100,100]映射到[0,200]上
     *         }
     *         int i = 0;
     *         while (K > 0) {
     *             while (number[i] == 0)//找到A[]中最小的数字
     *                 i++;
     *             number[i]--;//此数字个数-1
     *             number[200 - i]++;//其相反数个数+1
     *             if (i > 100) {//若原最小数索引>100,则新的最小数索引应为200-i.(索引即number[]数组的下标)
     *                 i = 200 - i;
     *             }
     *             K--;
     *         }
     *         int sum = 0;
     *         for (int j = i; j <number.length ; j++) {//遍历number[]求和
     *             sum += (j-100)*number[j];//j-100是数字大小,number[j]是该数字出现次数.
     *         }
     *         return sum;
     **/

    public static void main(String[] args) {
        int[] array=new int[]{-4,-3,-2,-1,0,1,2,3,4};
        System.out.println(largestSumAfterKNegations(array,6));
    }
}
