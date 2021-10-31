package Medium.DynamicTest;

public class NumMatrix {

    int[][] arr;

    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        this.arr = new int[row][col+1];
        for (int i=0;i<row;i++){
            int sum = 0;
            for (int j=1;j<=col;j++){
                sum+=matrix[i][j-1];
                arr[i][j] = sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i=row1;i<=row2;i++){
            sum += arr[i][col2+1]-arr[i][col1];
        }
        return sum;
    }


    class NumMatrix2 {

        private int[][] sum;
        public NumMatrix2(int[][] matrix) {
            if(matrix.length == 0 || matrix[0].length == 0) return;
            sum = new int[matrix.length + 1][matrix[0].length + 1];
            for(int i = 0; i < matrix.length; ++i){
                for(int j = 0; j < matrix[0].length; ++j){
                    sum[i + 1][j + 1] = matrix[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */

    public static void main(String[] args) {

    }
}
