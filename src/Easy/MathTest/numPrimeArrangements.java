package Easy.MathTest;


/**
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 *
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 *
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 * 示例 2：
 *
 * 输入：n = 100
 * 输出：682289015
 *  
 *
 * 提示：
 *
 * 1 <= n <= 100
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 1175. 质数排列 */

public class numPrimeArrangements {


    public int numPrimeArrangements(int n) {
        int[] bucket=new int[n+1];
        for (int i=2;i<=Math.sqrt(n);i++){
            if(bucket[i]==0){
                for (int j=2*i;j<=n;j+=i){
                    bucket[j]++;
                }
            }
        }
        int primeNumber=0;
        long sum1 = 1,sum2=1;
        for (int i=2;i<bucket.length;i++){
            if(bucket[i]==0) primeNumber++;
        }
        for (int i=1;i<=primeNumber;i++){
            sum1=(i*sum1)%1000000007;
        }
        for (int i=1;i<=(n-primeNumber);i++){
            sum2=(i*sum2)%1000000007;
        }
        return (int) ((sum1*sum2)%1000000007);
    }


    
    public static void main(String[] args) {
        System.out.println(new numPrimeArrangements().numPrimeArrangements(100));
    }
}
