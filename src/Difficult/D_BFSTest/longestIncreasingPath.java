package Difficult.D_BFSTest;


/*
* 329. 矩阵中的最长递增路径
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
输出: 4
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
输出: 4
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/26
 * */

public class longestIncreasingPath {


    private int max;
    public int longestIncreasingPath(int[][] matrix) {
        int a=matrix.length;
        if(a==0) return 0;
        int b=matrix[0].length;
        boolean[][] visit=new boolean[a][b];
        int[][] maxs=new int[a][b];
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                maxs[i][j]=1;
                dfs(matrix,visit,maxs,i,j,i,j,1);
                max=Math.max(max,maxs[i][j]);
            }
        }
        return max;
    }

    private int[][] dirs=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private void dfs(int[][] matrix,boolean[][] visit,int[][] maxs,int x,int y,int a,int b,int length){
        visit[x][y]=true;
        for (int[] dir:dirs){
            int nx=x+dir[0];
            int ny=y+dir[1];
            if(nx>=0&&nx<matrix.length&&ny>=0&&ny<matrix[0].length&&!visit[nx][ny]){
                if(matrix[nx][ny]>matrix[x][y]){
                    if(maxs[nx][ny]!=0){
                        maxs[a][b]=Math.max(maxs[a][b],maxs[nx][ny]+length);
                    }else {
                        dfs(matrix,visit,maxs,nx,ny,a,b,length+1);
                    }
                }else {
                    maxs[a][b]=Math.max(maxs[a][b],length);
                }
            }else {
                maxs[a][b]=Math.max(maxs[a][b],length);
            }
        }
        visit[x][y]=false;
    }


    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;

        int[][]dp = new int[row][col];
        int max = 0;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                max = Math.max(max, search(matrix, Integer.MIN_VALUE, i, j, dp));
            }
        }
        return max;
    }

    private int search(int[][]matrix, int curNum, int i , int j, int[][]dp){
        int row = matrix.length;
        int col = matrix[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] <= curNum) return 0;
        if (dp[i][j] != 0) return dp[i][j];

        int max = 0;
        max = Math.max(max, search(matrix, matrix[i][j], i - 1, j, dp));
        max = Math.max(max, search(matrix, matrix[i][j], i + 1, j, dp));
        max = Math.max(max, search(matrix, matrix[i][j], i, j - 1, dp));
        max = Math.max(max, search(matrix, matrix[i][j], i, j + 1, dp));
        dp[i][j] = max + 1;
        return max + 1;
    }


    public static void main(String[] args) {
        int[][] arr=new int[][]{{1,2,3},{6,5,4},{7,8,9}};
        System.out.println(new longestIncreasingPath().longestIncreasingPath(arr));
    }
}
