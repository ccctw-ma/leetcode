package Medium.ArrayTest;


/*
* 73. 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:

输入:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
示例 2:

输入:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
进阶:

一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？*/

/**
 * @author 马世臣
 *
 * @// TODO: 2020/6/15
 * */

public class setZeroes {



    public void setZeroes(int[][] matrix) {
        int col=0;
        int row=0;
        int a=matrix.length;
        if(a==0) return;
        int b=matrix[0].length;
        for (int[] ints : matrix) {
            if (ints[0] == 0) row++;
        }
        for (int j=0;j<b;j++){
            if(matrix[0][j]==0) col++;
        }
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }

        for (int i=1;i<a;i++){
            if(matrix[i][0]==0){
                for (int j=0;j<b;j++){
                    matrix[i][j]=0;
                }
            }
        }
        for (int j=1;j<b;j++){
            if(matrix[0][j]==0){
                for (int i=0;i<a;i++){
                    matrix[i][j]=0;
                }
            }
        }
        if(row>0){
            for (int i=0;i<a;i++){
                matrix[i][0]=0;
            }
        }
        if(col>0){
            for (int i=0;i<b;i++){
                matrix[0][i]=0;
            }
        }
    }


    public static void main(String[] args) {

    }
}
