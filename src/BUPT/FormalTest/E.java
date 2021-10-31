package BUPT.FormalTest;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        //超时了，可能是java算的慢的原因吧，我也不太清楚，能过前9的测试案例
        //
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int n = in.nextInt();
        long[] arr = new long[n + 1];
        for (int i=1;i<=n;i++){
            arr[i] = in.nextLong();
        }
        int o = in.nextInt();
        while ((--o)>=0){
            int type = in.nextInt();
            if(type==1){
                int index = in.nextInt();
                long value = in.nextLong();
                arr[index] = value;
            }else if(type==2){
                int l = in.nextInt();
                int r = in.nextInt();
                int z = in.nextInt();
                long sum = 0;
                for(int i=l;i<=r;i++){
                    long ai = arr[i];
                    int index = i-l+1;
                    int res = index % (2*(z-1));
                    int bi = 0;
                    if(res==0){
                        bi = 2;
                    }else if(res>0&&res<=z){
                        bi = res;
                    }else {
                        bi = 2*z - res;
                    }
                    sum += ai*bi;
                }
                System.out.println(sum);
            }
        }
    }
}
