package Medium.ArrayTest;


/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/15  */

public class maxAreaOfIsland {


    private int[] dx=new int[]{0,-1,0,1};
    private int[] dy=new int[]{1,0,-1,0};
    public int maxAreaOfIsland(int[][] grid) {
        int max=0,m=grid.length,n=grid[0].length,right,bottom;
        int[][] visited=new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(grid[i][j]==1&&visited[i][j]==0){
                    visited[i][j]=1;
                    right=DeepSearchIsland(grid,visited,i,j,0);
                    bottom=DeepSearchIsland(grid,visited,i,j,3);
                    max=Math.max(max,right+bottom+1);
                }
            }
        }
        return max;
    }

    //改进：遍历过得元素可以至0，不用另外开辟空间存储遍历状态
    private int DeepSearchIsland(int[][] grid,int[][] visited,int x,int y,int direction){
        int nx=x+dx[direction],ny=y+dy[direction];
        if((nx>=0&&nx<grid.length)&&(ny>=0&&ny<grid[0].length)){
            if(grid[nx][ny]==1&&visited[nx][ny]==0){
                visited[nx][ny]=1;
                int sum=0;
                for (int i=0;i<4;i++){
                   sum+=DeepSearchIsland(grid,visited,nx,ny,i);
                }
                return sum+1;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        System.out.println(new maxAreaOfIsland().maxAreaOfIsland(new int[][]{{1,1}}));
    }
}
