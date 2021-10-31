package Difficult.TraceBackTest;


/*
* 52. N-Queens II
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
  *
  *
  *
* */

/**
 * @author 马世臣
 * @// TODO: 2020/10/17  */



public class totalNQueens {


    private int n;
    private int res;
    private boolean[] judge;
    private boolean[] judge1;
    private boolean[] judge2;
    public int totalNQueens(int n) {
        this.n=n;
        res=0;
        judge = new boolean[n];
        judge1 = new boolean[2*n];
        judge2 = new boolean[2*n];
        trace(0);
        return res;
    }

    private void trace(int step){
        if(step==n){
            res++;
            return;
        }
        for (int i=0;i<n;i++){
            if(judge[i]||judge1[step+i]|| judge2[step-i+n]) continue;
            judge[i]=true;
            judge1[step+i]=true;
            judge2[step-i+n]=true;
            trace(step+1);
            judge2[step-i+n]=false;
            judge1[step+i]=false;
            judge[i]=false;
        }
    }


    public static void main(String[] args) {
        System.out.println(new totalNQueens().totalNQueens(4));
    }
}
