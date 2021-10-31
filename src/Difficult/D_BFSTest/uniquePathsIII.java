package Difficult.D_BFSTest;


/*
* 980. 不同路径 III
在二维网格 grid 上，有 4 种类型的方格：

1 表示起始方格。且只有一个起始方格。
2 表示结束方格，且只有一个结束方格。
0 表示我们可以走过的空方格。
-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。



示例 1：

输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
输出：2
解释：我们有以下两条路径：
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
示例 2：

输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
输出：4
解释：我们有以下四条路径：
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
示例 3：

输入：[[0,1],[2,0]]
输出：0
解释：
没有一条路能完全穿过每一个空的方格一次。
请注意，起始和结束方格可以位于网格中的任意位置。


提示：

1 <= grid.length * grid[0].length <= 20*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/6  */


public class uniquePathsIII {


    private int res;
    private int a;
    private int b;
    private int count;
    public int uniquePathsIII(int[][] grid) {
        count=0;
        a=grid.length;
        b=grid[0].length;
        res=0;
        for (int[] arr:grid){
            for (int i:arr){
                if(i==0) count++;
            }
        }
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                if(grid[i][j]==1){
                    grid[i][j]=0;
                    dfs(grid,i,j,0);
                    break;
                }
            }
        }
        return res;
    }


    private int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private void dfs(int[][] grid,int x,int y,int sum){
        if(x>=0&&x<a&&y>=0&&y<b){
            if(grid[x][y]==2){
//                System.out.println(sum);
                if(sum-1==count){
                    res++;
                }
            }else if(grid[x][y]==0){
                for (int i=0;i<4;i++){
                    int nx=x+dir[i][0];
                    int ny=y+dir[i][1];
                    grid[x][y]=-1;
                    dfs(grid,nx,ny,sum+1);
                    grid[x][y]=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid=new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println(new uniquePathsIII().uniquePathsIII(grid));
    }
}
