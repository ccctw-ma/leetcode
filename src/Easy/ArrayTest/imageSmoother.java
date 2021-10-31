package Easy.ArrayTest;



/**
 * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 *
 * 示例 1:
 *
 * 输入:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * 输出:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * 注意:
 *
 * 给定矩阵中的整数范围为 [0, 255]。
 * 矩阵的长和宽的范围均为 [1, 150]。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/31 661. 图片平滑器 */

public class imageSmoother {


    public int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r-1; nr <= r+1; ++nr)
                    for (int nc = c-1; nc <= c+1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                ans[r][c] /= count;
            }
        return ans;
    }


    //暴力解法，列举所有情况
    public int[][] imageSmoother2(int[][] M) {
        int rows = M.length;
        int cols = M[0].length;
        if (rows == 1 && cols == 1) {
            return M;
        }
        int[][] ans = new int[rows][cols];
        if (rows == 1) {
            ans[0][0] = (M[0][0] + M[0][1]) / 2;
            ans[0][cols-1] = (M[0][cols-1] + M[0][cols-2]) / 2;
            for (int i = 1; i < cols-1; i++) {
                ans[0][i] = (M[0][i-1] + M[0][i] + M[0][i+1]) / 3;
            }
            return ans;
        }
        if (cols == 1) {
            ans[0][0] = (M[0][0] + M[1][0]) / 2;
            ans[rows-1][0] = (M[rows-2][0] + M[rows-1][0]) / 2;
            for (int i = 1; i < rows-1; i++) {
                ans[i][0] = (M[i-1][0] + M[i][0] + M[i+1][0]) / 3;
            }
            return ans;
        }

        //rows和cols都不为1的情况下
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                int temp_sum = (M[i-1][j-1] + M[i-1][j] + M[i-1][j+1] +
                        M[i][j-1] + M[i][j] + M[i][j+1] +
                        M[i+1][j-1] + M[i+1][j] + M[i+1][j+1]);
                ans[i][j] = temp_sum / 9;
            }
        }
        ans[0][0] = (M[0][0] + M[0][1] + M[1][0] + M[1][1]) / 4;
        ans[0][cols-1] = (M[0][cols-1] + M[0][cols-2] + M[1][cols-1] + M[1][cols-2]) / 4;
        ans[rows-1][0] = (M[rows-1][0] + M[rows-1][1] + M[rows-2][0] + M[rows-2][1]) / 4;
        ans[rows-1][cols-1] = (M[rows-1][cols-1] + M[rows-1][cols-2] + M[rows-2][cols-1] + M[rows-2][cols-2]) / 4;
        for (int i = 1; i < cols-1; i++) {
            ans[0][i] = (M[0][i-1] + M[0][i] + M[0][i+1] + M[1][i-1] + M[1][i] + M[1][i+1]) / 6;
        }
        for (int i = 1; i < cols-1; i++) {
            ans[rows-1][i] = (M[rows-1][i-1] + M[rows-1][i] + M[rows-1][i+1] + M[rows-2][i-1] + M[rows-2][i] + M[rows-2][i+1]) / 6;
        }
        for (int i = 1; i < rows-1; i++) {
            ans[i][0] = (M[i-1][0] + M[i][0] + M[i+1][0] + M[i-1][1] + M[i][1] + M[i+1][1]) / 6;
        }
        for (int i = 1; i < rows-1; i++) {
            ans[i][cols-1] = (M[i-1][cols-1] + M[i][cols-1] + M[i+1][cols-1] + M[i-1][cols-2] + M[i][cols-2] + M[i+1][cols-2]) / 6;
        }
        return ans;
    }
    
    public static void main(String[] args) {

    }
}
