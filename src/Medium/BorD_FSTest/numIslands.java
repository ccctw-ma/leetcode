package Medium.BorD_FSTest;


/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/20  */


public class numIslands {


    private int[][] dir=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    private int a;
    private int b;
    public int numIslands(char[][] grid) {
        int sum=0;
        this.a=grid.length;
        if(a==0) return sum;
        this.b=grid[0].length;
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                if(grid[i][j]=='1'){
                    grid[i][j]='0';
                    sum++;
                    dfs(grid,i,j);
                }
            }
        }
        return sum;
    }

    public void dfs(char[][] grid,int x,int y){
        for (int i=0;i<dir.length;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];
            if(nx>=0&&nx<a&&ny>=0&&ny<b){
                if(grid[nx][ny]=='1'){
                    grid[nx][ny]='0';
                    dfs(grid,nx,ny);
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] chars=new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'},
        };
        System.out.println(new numIslands().numIslands(chars));
    }
}
