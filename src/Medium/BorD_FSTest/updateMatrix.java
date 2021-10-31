package Medium.BorD_FSTest;


/**
 * 542. 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 *
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。*/

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 马世臣
 * @// TODO: 2020/4/15
 * */


public class updateMatrix {




    private int[][] dir=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue=new LinkedList<>();
        for(int i = 0; i< m; i++){
            for(int j = 0; j< n; j++){
                if(matrix[i][j]==0){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int[][] res=new int[m][n];
        int step=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            step++;
            while (size-->0){
                int[] t=queue.poll();
                for (int i=0;i<4;i++){
                    assert t != null;
                    int nx=t[0]+dir[i][0];
                    int ny=t[1]+dir[i][1];
                    if(nx>=0&&nx< m &&ny>=0&&ny< n &&matrix[nx][ny]==1){
                        matrix[nx][ny]=0;
                        res[nx][ny]=step;
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return res;
    }





    private int lenX;
    private int lenY;

    public int[][] updateMatrix2(int[][] matrix) {
        lenX = matrix.length;
        lenY = matrix[0].length;
        for (int i = 0; i < lenX; i++) {
            for (int j = 0; j < lenY; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = findZero(matrix, i, j);
                }
            }
        }
        return matrix;
    }

    private int findZero(int[][] matrix, int x, int y) {
        int result = 0;
        int curX = x;
        int curY = y;
        while (true) {
            result++;
            int c = result;
            int i;
            //p1 left
            curX--;
            for (i = 0; i < c; i++) {
                curX++;
                curY--;
                if (curX >= 0 && curX < lenX && curY >= 0 && curY < lenY) {
                    if (matrix[curX][curY] == 0) {
                        return result;
                    }
                }
            }
            for (i = 0; i < c; i++) {
                curX++;
                curY++;
                if (curX >= 0 && curX < lenX && curY >= 0 && curY < lenY) {
                    if (matrix[curX][curY] == 0) {
                        return result;
                    }
                }
            }
            for (i = 0; i < c; i++) {
                curX--;
                curY++;
                if (curX >= 0 && curX < lenX && curY >= 0 && curY < lenY) {
                    if (matrix[curX][curY] == 0) {
                        return result;
                    }
                }
            }
            for (i = 0; i < c; i++) {
                curX--;
                curY--;
                if (curX >= 0 && curX < lenX && curY >= 0 && curY < lenY) {
                    if (matrix[curX][curY] == 0) {
                        return result;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
