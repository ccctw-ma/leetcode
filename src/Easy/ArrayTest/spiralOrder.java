package Easy.ArrayTest;


/*
* 面试题29. 顺时针打印矩阵
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。



示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]


限制：

0 <= matrix.length <= 100
0 <= matrix[i].length <= 100*/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/6/5  */


public class spiralOrder {


    public int[] spiralOrder2(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return new int[]{};
        int[][] dirs=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int a=matrix.length;
        int b=matrix[0].length;
        int[] res=new int[a*b];
        boolean[][] visited=new boolean[a][b];
        int index=0;
        int d=0,x=0,y=0;
        while (index<res.length){
            if(x>=0&&x<a&&y>=0&&y<b&&!visited[x][y]){
                res[index++]=matrix[x][y];
                visited[x][y]=true;
            }else {
                x-=dirs[d][0];
                y-=dirs[d][1];
                d=(d+1)%4;
            }
            x+=dirs[d][0];
            y+=dirs[d][1];
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<>();
        if(matrix.length==0||matrix[0].length==0) return res;
        int a=matrix.length,b=matrix[0].length;
        int left=0,right=b-1,top=0,bottom=a-1;
        while (true){
            for (int i=left;i<=right;i++) res.add(matrix[top][i]);
            if(++top>bottom) break;
            for (int i=top;i<=bottom;i++) res.add(matrix[i][right]);
            if(--right<left) break;
            for (int i=right;i>=left;i--) res.add(matrix[bottom][i]);
            if(--bottom<top) break;
            for (int i=bottom;i>=top;i--) res.add(matrix[i][left]);
            if(++left>right) break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(new spiralOrder().spiralOrder(matrix));
    }
}
