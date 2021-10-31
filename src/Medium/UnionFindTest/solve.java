package Medium.UnionFindTest;


/*
* 130. 被围绕的区域
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。*/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/6/2  */


public class solve {


    private int a;
    private int b;
    private int[][] direction=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public void solve(char[][] board) {
        a=board.length;
        b=board[0].length;
        for (int i=0;i<b;i++){
            if(board[0][i]=='O'){
                board[0][i]='#';
                dfs(board,0,i);
            }
            if(board[a-1][i]=='O'){
                board[a-1][i]='#';
                dfs(board,a-1,i);
            }
        }
        for (int i=0;i<a;i++){
            if(board[i][0]=='O'){
                board[i][0]='#';
                dfs(board,i,0);
            }
            if(board[i][b-1]=='O'){
                board[i][b-1]='#';
                dfs(board,i,a-1);
            }
        }
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                if(board[i][j]=='O') board[i][j]='X';
                if(board[i][j]=='#') board[i][j]='O';
            }
        }
    }


    public void dfs(char[][] board,int x,int y){
        for (int[] dir:direction){
            int nx=x+dir[0];
            int ny=y+dir[1];
            if(nx>=0&&nx<a&&ny>=0&&ny<b&&board[nx][ny]=='O'){
                board[nx][ny]='#';
                dfs(board,nx,ny);
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();

    }
}
