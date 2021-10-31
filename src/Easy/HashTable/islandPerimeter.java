package Easy.HashTable;



/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *  
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 *
 *
 **/


/**
 * @author 马世臣
 * @// TODO: 2020/2/2 463. 岛屿的周长
 *
 * */

public class islandPerimeter {


    public int islandPerimeter(int[][] grid) {
        int[] x=new int[]{1,0,-1,0};
        int[] y=new int[]{0,1,0,-1};
        int sum=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    int n=4;
                    for (int k=0;k<4;k++){
                        int curx=i+x[k];
                        int cury=j+y[k];
                        if(curx>=0&&curx<grid.length&&cury>=0&&cury<grid[0].length&&grid[curx][cury]==1){
                            n--;
                        }
                    }
                    sum+=n;
                }
            }
        }
        return sum;
    }
    public int islandPerimeter2(int[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1){
                    count+=4;
                    if(i>0 && grid[i-1][j]==1)count-=2;
                    if(j>0 && grid[i][j-1]==1) count-=2;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid=new int[][]{{0,1,0,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(new islandPerimeter().islandPerimeter2(grid));
    }
}
