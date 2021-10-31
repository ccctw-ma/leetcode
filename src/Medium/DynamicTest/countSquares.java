package Medium.DynamicTest;


/**
 * 1277. 统计全为 1 的正方形子矩阵
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/10  */

public class countSquares {


    public int countSquares(int[][] matrix) {
        int count=0,m=matrix.length,n=matrix[0].length;
        for (int i=0;i<n;i++) if(matrix[0][i]==1) count++;
        for (int i=1;i<m;i++) if(matrix[i][0]==1) count++;
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if(matrix[i][j]==1){
                    int a=matrix[i-1][j-1],b=matrix[i][j-1],c=matrix[i-1][j];
                    int min=Math.min(Math.min(a,b),c);
                    matrix[i][j]+=min;
                    count+=matrix[i][j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
