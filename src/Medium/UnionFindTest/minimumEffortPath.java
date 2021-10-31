package Medium.UnionFindTest;


/*
* 1631. 最小体力消耗路径
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。



示例 1：



输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
输出：2
解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
示例 2：



输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
输出：1
解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
示例 3：


输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
输出：0
解释：上图所示路径不需要消耗任何体力。


提示：

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106*/

/**
 * @author 马世臣
 * @// TODO: 2021/1/29
 * */


public class minimumEffortPath {


    private int row;
    private int col;
    private int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};


    public int minimumEffortPath(int[][] heights) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] height:heights){
            for (int h:height){
                min = Math.min(min,h);
                max = Math.max(max,h);
            }
        }
        row = heights.length;
        col = heights[0].length;
        int l = 0,r = max -min;
        boolean flag = false;
        while (l<r){
            int mid = (l+r)/2;
            boolean[][] visited = new boolean[row][col];
            visited[0][0] = true;
            if(dfs(heights,visited,mid,0,0)){
                flag = true;
                r = mid;
            }else {
                flag = false;
                l = mid + 1;
            }
        }
        return flag? l+1:l;
    }

    public boolean dfs(int[][] heights,boolean[][] visited,int val,int x,int y){
        if(x==heights.length-1&&y==heights[0].length-1){
            return true;
        }
        boolean flag = false;
        for (int[] ints : dir) {
            int nx = x+ints[0];
            int ny = y+ints[1];
            if(nx>=0&&nx<row&&ny>=0&&ny<col&&!visited[nx][ny]&&Math.abs(heights[x][y]-heights[nx][ny])<=val){
                visited[nx][ny] = true;
                flag|=dfs(heights,visited,val,nx,ny);
                if(flag)
                    return true;
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        int[][] heights = new int[][]{{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(new minimumEffortPath().minimumEffortPath(heights));
    }
}
