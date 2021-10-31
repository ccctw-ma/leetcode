package Medium.BackTrackingTest;


/*
* 79. 单词搜索
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false


提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/13  */



public class exist {

    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        boolean[][] visited=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                 if(trace(board,visited,word,0,i,j)){
                     return true;
                 }
            }
        }
        return false;
    }


    //can simplify, the judge statement is too long to understand
    private boolean trace(char[][] board,boolean[][] visited,String word,int index,int x,int y){
        if(index==word.length())    return true;
        if(x<0||x>=board.length||y<0||y>=board[0].length||visited[x][y]||word.charAt(index)!=board[x][y]) return false;
        visited[x][y]=true;
        if(trace(board,visited,word,index+1,x+1,y)||
                trace(board,visited,word,index+1,x-1,y)||
                trace(board,visited,word,index+1,x,y+1)||
                trace(board,visited,word,index+1,x,y-1))
            return true;
        visited[x][y]=false;
        return false;
    }

    public static void main(String[] args) {

    }
}
