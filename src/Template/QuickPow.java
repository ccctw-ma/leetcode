package sort;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class QuickPow {


    //非递归写法
    public long qpow(long a,long n,long mod){
        // 这里的a 为基数
        long res = 1;
        while (n!=0){
            if(n%2==1){
                res = res * a % mod;
            }
            a = a * a % mod;
            n>>=1;
        }
        return res;
    }

    //递归写法
    public  long Qpow(long a,long n,long mod){
        if(n==0) return 1;
        if(n%2==1){
            return Qpow(a,n-1,mod)*a%mod;
        }else {
            long temp = Qpow(a,n/2,mod)%mod;
            return temp*temp%mod;
        }
    }

    //快速幂的递归写法

    private static long qpow(long a,long n){
        if(n==0) return 1;
        if(n%2==1){
            return qpow(a,n-1)*a%1000_000_007;
        } else{
            long temp = qpow(a,n/2)%1000_000_007;
            return temp*temp%1000_000_007;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNext()){
            builder.append(scanner.nextLine());
        }
        System.out.println(builder.toString());
    }
}
