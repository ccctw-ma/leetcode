package Easy.ArrayTest;



/**
 * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 *
 * 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
 *
 *  
 *
 * 示例：
 *
 * 输入: [[4,3,8,4],
 *       [9,5,1,9],
 *       [2,7,6,2]]
 * 输出: 1
 * 解释: 
 * 下面的子矩阵是一个 3 x 3 的幻方：
 * 438
 * 951
 * 276
 *
 * 而这一个不是：
 * 384
 * 519
 * 762
 *
 * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 * 提示:
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * 0 <= grid[i][j] <= 15
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/2/2 840. 矩阵中的幻方 */

public class numMagicSquaresInside {


    public int numMagicSquaresInside(int[][] grid) {
        int  n=0;
        for (int i=0;i<grid.length-2;i++){
            for (int j=0;j<grid[0].length-2;j++){
                if(isMagic(grid,i,j)) n++;
            }
        }
        return n;
    }

    public boolean isMagic(int[][] grid,int x,int y){
        int[] num=new int[10];
        for (int i=x;i<x+3;i++){
            for (int j=y;j<y+3;j++){
                int temp=grid[i][j];
                if(temp<1||temp>9||num[temp]==1){
                    return false;
                }
                num[temp]++;
            }
        }
        int ave=grid[x+1][y+1]*3;
        for (int i=x;i<x+3;i++){
            int sum=0;
            for (int j=y;j<y+3;j++){
                sum+=grid[i][j];
            }
            if(sum!=ave) return false;
        }
        for (int j=y;j<y+3;j++){
            int sum=0;
            for (int i=x;i<x+3;i++){
                sum+=grid[i][j];
            }
            if(sum!=ave) return false;
        }
        int sum=grid[x][y]+grid[x+1][y+1]+grid[x+2][y+2];
        if(sum!=ave) return false;
        int sum2=grid[x][y+2]+grid[x+1][y+1]+grid[x+2][y];
        return sum2==ave;
    }
    
    public static void main(String[] args) {
        int[][] grid=new int[][]{{4,3,8,4},{9,5,1,9},{2,7,6,2}};
        System.out.println(new numMagicSquaresInside().numMagicSquaresInside(grid));
    }
}
