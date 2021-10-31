package Medium.ArrayTest;


/**
 * 289. 生命游戏
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 *
 * 进阶：
 *
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：
 * 你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，
 * 但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？*/


/**
 * @author 马世臣
 * @// TODO: 2020/4/2  */

public class gameOfLife {

    public void gameOfLife(int[][] board) {
        int[][] dir=new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int[][] temp=new int[board.length][board[0].length];
        int a=board.length,b=board[0].length;
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                int count=0;
                for (int k=0;k<8;k++){
                    int nx=i+dir[k][0],ny=j+dir[k][1];
                    if(nx>=0&&nx<a&&ny>=0&&ny<b&&board[nx][ny]==1){
                        count++;
                    }
                }
                if(board[i][j]==1&&(count<2||count>3)){
                    temp[i][j]=0;
                }else if(board[i][j]==0&&count==3){
                    temp[i][j]=1;
                }else {
                    temp[i][j]=board[i][j];
                }
            }
        }
        for (int i=0;i<a;i++){
            System.arraycopy(temp[i], 0, board[i], 0, b);
        }
    }


    //没有什么简便方法，因为只有0和1两种状态，所以可以增加两个状态表示0->1和1->0
    //这样就可以不用额外的空间在原地即可
    public void gameOfLif2(int[][] board) {
        // 对应技巧2，用一个长度为8的数组来保存遍历每一个位置时，其周边位置的相对坐标偏移
        int[] x = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] y = {1, -1, 1, -1, 0, 1, -1, 0};
        // 对应技巧1，第一次遍历，把需要变化状态的位置保存为中间值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int curX, curY;
                int live = 0;
                // 对应技巧2，通过之前保存的相对坐标偏移的数组，方便的遍历所有的周边位置。
                for (int k = 0; k < 8; k++) {
                    curX = i + x[k];
                    curY = j + y[k];
                    if (curX < 0 || curX >= board.length || curY < 0 || curY >= board[0].length) {
                        continue;
                    }
                    // 对应技巧1,这里的0,1是题目里合理的值，然后如果0要变1，我们用中间值-1记录，如果1要变0
                    // ，我们用中间值2来记录。
                    if (board[curX][curY] == 1 || board[curX][curY] == 2) {
                        live++;
                    }
                }
                if (board[i][j] == 0) {
                    if (live == 3) {
                        board[i][j] = -1;
                    }
                } else {
                    if (live < 2 || live > 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        // 对应技巧1，第二次遍历，把中间值-1和2刷新为1和0
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }


    public static void main(String[] args) {

    }
}
