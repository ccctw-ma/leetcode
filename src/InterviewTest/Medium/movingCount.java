package InterviewTest.Medium;


/**
 * 面试题13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/8  */

public class movingCount {


    private int[][] dir=new int[][]{{1,0},{0,1}};
    private int m;
    private int n;
    private int k;
    private int count=1;
    public int movingCount(int m, int n, int k) {
        int[][] dp=new int[m][n];
        this.m=m;
        this.n=n;
        this.k=k;
        search(dp,0,0);
        return count;
    }

    private void search(int[][] dp,int x,int y){
        for (int[] ints : dir) {
            int nx = x + ints[0];
            int ny = y + ints[1];
            if (nx < m && ny < n && dp[nx][ny] == 0) {
                if (canReach(nx, ny, k)) {
                    dp[nx][ny] = 1;
                    count++;
                    search(dp, nx, ny);
                } else {
                    dp[nx][ny] = 2;
                }
            }
        }
    }

    private boolean canReach(int x,int y,int k){
        while (x!=0){
            k=k-(x%10);
            x/=10;
        }
        while (y!=0){
            k=k-(y%10);
            y/=10;
        }
        return k>=0;
    }




    //仔细查看题目可以看到，数据是有限制的，并且[100,100]是肯定到不了的所以不用考虑，用bfs即可
    boolean[][] visited;
    public int movingCount2(int m, int n, int k) {
        visited = new boolean[m][n];
        return dfs(0, 0, m, n, k);
    }

    private int dfs(int x, int y, int m, int n, int k) {
        if (x >= m || y >= n || visited[x][y]
                || (x % 10 + x / 10 + y % 10 + y / 10) > k) {
            return 0;
        }
        visited[x][y] = true;
        return 1 + dfs(x + 1, y, m, n, k) + dfs(x, y + 1, m, n, k);
    }

    public static void main(String[] args) {
        System.out.println(new movingCount().movingCount(38,15,9));
    }
}
