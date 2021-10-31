package Easy.ArrayTest;

public class isToeplitzMatrix {


    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length==1) return true;
        int a=matrix[0].length,b=matrix.length;
        for (int i=1;i<a;i++){
            int difference=i-1;
            for (int j=1;j<b;j++){
                if((j+difference)<a&&matrix[j][j+difference]!=matrix[j-1][(j + difference) - 1]){
                    return false;
                }
            }
        }
        for (int j=1;j<b;j++){
            for (int i=0;i<a;i++){
                if(i+ j +1<b&&i+1<a&&matrix[i+ j +1][i+1]!=matrix[i+ j][i]) return false;
            }
        }
        return true;
    }



    //循环遍历二维数组即可
    public boolean isToeplitzMatrix2(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
                    return false;
        return true;
    }


    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        System.out.println(new isToeplitzMatrix().isToeplitzMatrix(matrix));
    }
}
