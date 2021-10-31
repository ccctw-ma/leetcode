package Medium.ArrayTest;

import java.util.Arrays;

public class searchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0, r = matrix.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (matrix[mid][0] > target)
                r = mid - 1;
            else
                l = mid;
        }
        return Arrays.binarySearch(matrix[l], target) >= 0;
    }


    public static void main(String[] args) {

    }
}
