package Difficult.DynamicTest;


/*
* 174. 地下城游戏
一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

为了尽快到达公主，骑士决定每次只向右或向下移动一步。



编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。

例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


说明:

骑士的健康点数没有上限。

任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/7/12  */


public class calculateMinimumHP {


    private int[][] dir=new int[][]{{0,1},{1,0}};
    private int res;
    private int col,row;
    public int calculateMinimumHP2(int[][] dungeon) {
        this.res=Integer.MAX_VALUE;
        col=dungeon.length;
        row=dungeon[0].length;
        dfs(dungeon,0,0,Integer.MAX_VALUE,0);
        return res<0?1:res+1;
    }

    private void dfs(int[][] dungeon,int x,int y,int min,int sum){
        if(x>=0&&x<col&&y>=0&&y<row){
            sum+=dungeon[x][y];
            min=Math.min(sum,min);
            if(x==col-1&&y==row-1){
                res=Math.min(res,-min);
                return;
            }
            for (int[] ints : dir) {
                int nx = x + ints[0];
                int ny = y + ints[1];
                dfs(dungeon, nx, ny, min, sum);
            }
        }
    }


    //错的
    public int calculateMinimumHP3(int[][] dungeon) {
        int m=dungeon.length;
        int n=dungeon[0].length;
        //0-> sum 1->cur_min
        int[][] dp=new int[n][2];
        dp[0][0]=dungeon[0][0];
        dp[0][1]=dungeon[0][0];
        for (int i=1;i<n;i++){
            dp[i][0]=dungeon[0][i]+dp[i-1][0];
            dp[i][1]=Math.min(dp[i][0],dp[i-1][1]);
        }
        for (int i=1;i<m;i++){
            int[][] temp=new int[n][2];
            temp[0][0]=dp[0][0]+dungeon[i][0];
            temp[0][1]=Math.min(temp[0][0],dp[0][1]);
            for (int j=1;j<n;j++){
                int a=Math.min(dungeon[i][j]+dp[j][0],dp[j][1]);
                int b=Math.min(dungeon[i][j]+temp[j-1][0],temp[j-1][1]);
                if(a==b){
                    temp[j][0]=Math.max(dungeon[i][j]+dp[j][0],dungeon[i][j]+temp[j-1][0]);
                    temp[j][1]=a;
                }else if(a<b){
                    temp[j][1]=b;
                    temp[j][0]=dungeon[i][j]+temp[j-1][0];
                }else {
                    temp[j][1]=a;
                    temp[j][0]=dungeon[i][j]+dp[j][0];
                }
            }
            dp=temp;
        }
        return dp[n-1][1]>0?1:-dp[n-1][1]+1;
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] arrs=new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}};

        System.out.println(new calculateMinimumHP().calculateMinimumHP(arrs));
    }
}
