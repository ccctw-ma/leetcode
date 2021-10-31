package BUPT.FormalTest;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int q = in.nextInt();
        //找好每个客人来的时候的温度可控范围即可
        while ((--q)>=0){
            int n = in.nextInt();//顾客数
            int m = in.nextInt();//初试温度
            int[] times = new int[n];
            int[][] ranges=  new int[n][2];
            for (int i=0;i<n;i++){
                times[i] = in.nextInt();
                ranges[i][0] = in.nextInt();
                ranges[i][1] = in.nextInt();
            }
            boolean flag = true;
            int preTime = 0;
            int left =m,right = m;
            for (int i=0;i<n;i++){
                int t = times[i] - preTime;
                left = left - t;
                right = right + t;
                if(ranges[i][0]>right||ranges[i][1]<left){
                    flag = false;
                    break;
                }
                preTime = times[i];
                left = Math.max(left,ranges[i][0]);
                right = Math.min(right,ranges[i][1]);
            }
            if(flag){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
