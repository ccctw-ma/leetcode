package Difficult.TraceBackTest;


/*
* 37. 解数独
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。



一个数独。



答案被标成红色。

Note:

给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 马世臣
 * @// TODO: 2020/9/15
 * */


public class solveSudoku {


    /**
     * @implNote  The result that cost of time seems out of  expected
     * we can use boolean array to record each column, each row  and
     * each sub-box in order to save time and space.
     * The HashSet is a not bad data set ,however, it costs more time and space.*/

    private List<Set<Integer>> row_sets;
    private List<Set<Integer>> col_sets;
    private List<Set<Integer>> div_sets;
    private int m;
    private int n;
    private boolean[][] can_not_change;
    private boolean flag;

    public void solveSudoku(char[][] board) {

        //Initial operation
        row_sets = new ArrayList<>();
        col_sets = new ArrayList<>();
        div_sets = new ArrayList<>();
        for (int i=0;i<9;i++){
            row_sets.add(new HashSet<>());
            col_sets.add(new HashSet<>());
            div_sets.add(new HashSet<>());
        }
        m = board.length;
        n = board[0].length;
        can_not_change =new boolean[m][n];
        flag=false;
        for (int i = 0; i< m; i++){
            for (int j = 0; j< n; j++){
                char c=board[i][j];
                if(c!='.'){
                    can_not_change[i][j]=true;
                    row_sets.get(i).add(c-'0');
                    col_sets.get(j).add(c-'0');
                    int index=(i/3)*3+(j/3);
                    div_sets.get(index).add(c-'0');
                }
            }
        }
        trace(board,0,0);

    }

    private void trace(char[][] board,int x,int y){
        if(x==m){
            flag=true;
            return;
        }

        int nx=(y==n-1?x+1:x);
        int ny=(y==n-1?0:y+1);

        if(can_not_change[x][y]) {
            trace(board, nx, ny);
        }else {
            for (int i=1;i<=9&&!flag;i++){
                if(check(x,y,i)){
                    board[x][y]=(char)(i+'0');
                    row_sets.get(x).add(i);
                    col_sets.get(y).add(i);
                    int index=(x/3)*3+(y/3);
                    div_sets.get(index).add(i);
                    trace(board, nx, ny);
                    div_sets.get(index).remove(i);
                    col_sets.get(y).remove(i);
                    row_sets.get(x).remove(i);
                }
            }
        }
    }


    private boolean check(int x, int y, int val){
        if(row_sets.get(x).contains(val)) return false;
        if(col_sets.get(y).contains(val)) return false;
        int index=(x/3)*3+(y/3);
        return !div_sets.get(index).contains(val);
    }




    public void solveSudoku2(char[][] board) {
        /**
         * 记录某行，某位数字是否已经被摆放
         */
        boolean[][] row = new boolean[9][9];
        /**
         * 记录某列，某位数字是否已经被摆放
         */
        boolean[][] col = new boolean[9][9];
        /**
         * 记录某 3x3 宫格内，某位数字是否已经被摆放
         */
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    row[i][num] = true;
                    col[j][num] = true;
                    // blockIndex = i / 3 * 3 + j / 3，取整
                    block[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, row, col, block, 0, 0);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
        // 找寻空位置
        while (board[i][j] != '.') {
            if (++j >= 9) {
                i++;
                j = 0;
            }
            if (i >= 9) {
                return true;
            }
        }
        for (int num = 0; num < 9; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                // 递归
                board[i][j] = (char) ('1' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (dfs(board, row, col, block, i, j)) {
                    return true;
                } else {
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board=new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        new solveSudoku().solveSudoku(board);
        for (char[] chars:board){
            System.out.println(chars);
        }
    }
}
