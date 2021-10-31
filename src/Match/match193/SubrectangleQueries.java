package Match.match193;

public class SubrectangleQueries {



    private int[][] rectangle;
    public SubrectangleQueries(int[][] rectangle) {
        int a=rectangle.length;
        int b=rectangle[0].length;
        this.rectangle=rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i=row1;i<=row2;i++){
            for (int j=col1;j<=col2;j++){
                rectangle[i][j]=newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}
