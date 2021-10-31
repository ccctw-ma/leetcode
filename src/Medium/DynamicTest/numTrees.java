package Medium.DynamicTest;
/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 通过次数32,979提交次数50,690*/

/**
 * @author 马世臣 
 * @// TODO: 2020/3/10  */

public class numTrees {
    
    public int numTrees(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        for (int i=1;i<=n;i++){
            for (int j=0;j<i;j++){
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        System.out.println(new numTrees().numTrees(3));
    }
}
