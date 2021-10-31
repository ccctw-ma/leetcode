package BUPT.FormalTest;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class D {

    public static boolean check(int[][] matrices,int n){
        for (int i=0;i<n/2;i++){
            for (int j=0;j<n/2;j++){
                if(matrices[i][j]==1){
                    if(matrices[n-i-1][n-j-1]!=1) return false;
                    if(i>0&&j>0){
                        if(matrices[i-1][j]==1) return false;
                        if(matrices[i][j-1]==1) return false;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int x= in.nextInt();
        /*
        *  x = 1, n = 1;
        *  x = 2, n = 3;
        *  x = 3, n = 3;
        *  x = 4, n = 3;
        *  x = 5,6,7,8,9,10 , n = 5,
        *   暂时找不到规律，有可能是动态规划，如果数量集小的话可以暴力试一下
        * */
    }
}
