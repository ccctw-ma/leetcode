package Medium.DynamicTest;


/**
 * 1039. 多边形三角剖分的最低得分
 * 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。
 *
 * 假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。
 *
 * 返回多边形进行三角剖分后可以得到的最低分。
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 * 示例 2：
 *
 *
 *
 * 输入：[3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 * 示例 3：
 *
 * 输入：[1,3,1,4,1,5]
 * 输出：13
 * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 *
 *
 * 提示：
 *
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/25
 * */


public class minScoreTriangulation {

    public int minScoreTriangulation(int[] A) {
        int minScore = Integer.MAX_VALUE;
        int[][]  dp = new int[A.length][A.length];
        for (int j = 2; j < A.length; j++) {
            for (int i = j - 2 ; i >= 0; i --) {
                // 在顶点i - j中，找一个中间点k，使  dyna[i][k] + dyna[k][j] + A[i] * A[k] * A[j]最小
                int min = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k ++) {
                    int temp = dp[i][k] + dp[k][j] + A[i] * A[k] * A[j];
                    if (temp < min) {
                        min = temp;
                    }
                }
                dp[i][j] = min;
            }
        }
        return dp[0][A.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new minScoreTriangulation().minScoreTriangulation(new int[]{1,3,1,4}));
    }

}
