package Medium.ArrayTest;


/*
* 59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。



示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]


提示：

1 <= n <= 20*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2021/3/16  */


public class generateMatrix {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n-1, left = 0, right = n-1, index = 1;
        while (true){
            for (int i=left;i<=right;i++) matrix[top][i] = index++;
            if(++top>bottom) break;
            for (int i=top;i<=bottom;i++) matrix[i][right] = index++;
            if(left>--right) break;
            for (int i=right;i>=left;i--) matrix[bottom][i] = index++;
            if(top>--bottom) break;
            for (int i=bottom;i>=top;i--) matrix[i][left] = index++;
            if(++left>right) break;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] arr = new generateMatrix().generateMatrix(2);
        for (int[] a:arr){
            System.out.println(Arrays.toString(a));
        }
        System.out.println(Integer.toBinaryString(10).toCharArray());

    }
}

