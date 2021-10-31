package BUPT.FormalTest;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        //过了
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int n = in.nextInt();
        int s = in.nextInt();
        int[] volumes = new int[n];
        for (int i=0;i<n;i++){
            volumes[i] = in.nextInt();
        }
        //简单的排序与求和
        Arrays.sort(volumes);
        int sum = 0;
        for (int i=0;i<n-1;i++){
            sum += volumes[i];
        }
        if(sum>s){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }

    }
}
