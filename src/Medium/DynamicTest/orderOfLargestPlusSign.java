package Medium.DynamicTest;


/**
 * 764. 最大加号标志
 * 在一个大小在 (0, 0) 到 (N-1, N-1) 的2D网格 grid 中，除了在 mines 中给出的单元为 0，其他每个单元都是 1。网格中包含 1 的最大的轴对齐加号标志是多少阶？返回加号标志的阶数。如果未找到加号标志，则返回 0。
 *
 * 一个 k" 阶由 1 组成的“轴对称”加号标志具有中心网格  grid[x][y] = 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。下面给出 k" 阶“轴对称”加号标志的示例。注意，只有加号标志的所有网格要求为 1，别的网格可能为 0 也可能为 1。
 *
 *
 *
 * k 阶轴对称加号标志示例:
 *
 * 阶 1:
 * 000
 * 010
 * 000
 *
 * 阶 2:
 * 00000
 * 00100
 * 01110
 * 00100
 * 00000
 *
 * 阶 3:
 * 0000000
 * 0001000
 * 0001000
 * 0111110
 * 0001000
 * 0001000
 * 0000000
 *
 *
 * 示例 1：
 *
 * 输入: N = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释:
 *
 * 11111
 * 11111
 * 11111
 * 11111
 * 11011
 *
 * 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 *
 *
 * 示例 2：
 *
 * 输入: N = 2, mines = []
 * 输出: 1
 * 解释:
 *
 * 11
 * 11
 *
 * 没有 2 阶加号标志，有 1 阶加号标志。
 *
 *
 * 示例 3：
 *
 * 输入: N = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释:
 *
 * 0
 *
 * 没有加号标志，返回 0 。
 *
 *
 * 提示：
 *
 * 整数N 的范围： [1, 500].
 * mines 的最大长度为 5000.
 * mines[i] 是长度为2的由2个 [0, N-1] 中的数组成.
 * (另外,使用 C, C++, 或者 C# 编程将以稍小的时间限制进行​​判断.)*/

import java.util.HashSet;
import java.util.Set;

/**
 * @author 马世臣
 * @// TODO: 2020/3/20  */

public class orderOfLargestPlusSign {


    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> banned = new HashSet();
        int[][] dp = new int[N][N];

        for (int[] mine: mines)
            banned.add(mine[0] * N + mine[1]);
        int ans = 0, count;

        for (int r = 0; r < N; ++r) {
            count = 0;
            for (int c = 0; c < N; ++c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = N-1; c >= 0; --c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; ++c) {
            count = 0;
            for (int r = 0; r < N; ++r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N-1; r >= 0; --r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
