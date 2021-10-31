package Medium.DynamicTest;


/**
 * 1139. 最大的以 1 为边界的正方形
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 *
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/27  */

public class largest1BorderedSquare {


    public int largest1BorderedSquare(int[][] grid) {
        int len=grid.length,llen=grid[0].length;
        int[][][] dp=new int[len][llen][2];
        int max=0;
        int[][] dir=new int[][]{{-1,0},{0,-1},{0,1},{1,0}};
        for (int i=0;i<len;i++){
            for (int j=0;j<llen;j++){
                if(grid[i][j]==1){
                    dp[i][j][0]=1;
                    dp[i][j][1]=1;
                    max=Math.max(max,1);
                    int a=len,b=len;
                    for (int d=0;d<2;d++){
                        int step=0;
                        int nx=i+dir[d][0],ny=j+dir[d][1];
                        while ((nx>=0&&nx<len)&&(ny>=0&&ny<llen)&&grid[nx][ny]==1){
                            step++;
                            nx+=dir[d][0];
                            ny+=dir[d][1];
                        }
                        a=Math.min(a,step);
                    }
                    for (int d=2;d<4;d++){
                        int step=0;
                        int nx=i+dir[d][0],ny=j+dir[d][1];
                        while ((nx>=0&&nx<len)&&(ny>=0&&ny<llen)&&grid[nx][ny]==1){
                            step++;
                            nx+=dir[d][0];
                            ny+=dir[d][1];
                        }
                        b=Math.min(b,step);
                    }
                    dp[i][j][0]=1+a;
                    dp[i][j][1]=1+b;
                    for (int l=a;l>0;l--){
                        if(i-l>=0&&j-l>=0&&dp[i-l][j-l][1]>=(1+l)){
                            max=Math.max(max,(1+l)*(1+l));
                        }
                    }
                }
            }
        }
        return max;
    }


    public int squareSide(int[][] grid, int x, int y) {
        int side = 0;
        /** 通过左、上边, 最大公共长度 */
        for (int i = x, j = y; i < grid.length && j < grid[0].length; i++, j++) {
            if (grid[i][y] != 1 || grid[x][j] != 1) {
                break;
            }
            side++;
        }

        /** 通过右、下边, 确定最终合适边长 */
        /** 以对角线为起点, 确定最大长度 */
        for (int len = side; len > 0; len--) {
            int right_x = x + len - 1;
            int right_y = y + len - 1;
            int m, n;
            for (m = right_x, n = right_y; m > x; m--, n--) {
                if (grid[m][right_y] != 1 || grid[right_x][n] != 1) {
                    break;
                }
            }
            /* 循环自然结束, 则找到了最大长度 */
            if (m == x) {
                side = len;
                break;
            }
        }

        return side;
    }


    //从左上角找右下角，并通过最大值减少运算量很好
    public int largest1BorderedSquare2(int[][] grid) {
        int maxSide = 0;
        for (int i = 0; i < grid.length; i++) {
            /** 剩余长度不够, 直接跳出 */
            if (i + maxSide > grid.length) {
                break;
            }
            for (int j = 0; j < grid[0].length; j++) {
                /** 剩余长度不够, 直接跳出 */
                if (j + maxSide > grid[0].length) {
                    break;
                }
                int sideLength = squareSide(grid, i, j);
                maxSide = maxSide < sideLength ? sideLength : maxSide;
            }
        }
        return maxSide * maxSide;
    }



    public int largest1BorderedSquare3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m+1][n+1][2];
        // 0 up 1 left;
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i-1][j-1] == 0) continue; // 如果是0则不要继续了
                dp[i][j][0] = dp[i-1][j][0] + 1; // 求出up情况下连续的个数
                dp[i][j][1] = dp[i][j-1][1] + 1; // 求出left情况下连续的个数
                int min = Math.min(dp[i][j][0], dp[i][j][1]); // 拿出两者较小的长度，因为四条边都要相等。
                for (int k = 0; k < min; k++) {//拿出后并不一定就是min这个长度，有可能另外两条边比较短，没有min长，所以要一个一个判断。
                    // 判断另外的两条边是否都比当前长度大。
                    if (dp[i-k][j][1] >= k + 1 && dp[i][j-k][0] >= k + 1) ans = Math.max(ans, k + 1);
                }
            }
        }
        return ans * ans;
    }

    public static void main(String[] args) {
        System.out.println(new largest1BorderedSquare().largest1BorderedSquare(new int[][]{{0,1,1,1,1,0},{1,1,0,1,1,0},{1,1,0,1,0,1},{1,1,0,1,1,1},{1,1,0,1,1,1},{1,1,1,1,1,1},{1,0,1,1,1,1},{0,0,1,1,1,1},{1,1,1,1,1,1}}));
    }
}
