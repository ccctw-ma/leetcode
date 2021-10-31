package Match.zzu;

import java.util.Scanner;

public class Main {


    /**
     * #include <map>
     * #include <iostream>
     * using namespace std;
     *
     * #define long long long
     * const long M = 1000000007; // modulo
     * map<long, long> F;
     *
     * long f(long n) {
     * 	if (F.count(n)) return F[n];
     * 	long k=n/2;
     * 	if (n%2==0) { // n=2*k
     * 		return F[n] = (f(k)*f(k) + f(k-1)*f(k-1)) % M;
     *        } else { // n=2*k+1
     * 		return F[n] = (f(k)*f(k+1) + f(k-1)*f(k)) % M;
     *    }
     * }
     *
     * main(){
     * 	long n;
     * 	F[0]=F[1]=1;
     * 	while (cin >> n)
     * 	cout << (n==0 ? 0 : f(n-1)) << endl;
     * }*/

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int temp=0;
        for(int i = b; i <= a*b; i += b){
            if(i % a == 0){
                temp=i;
                break;
            }
        }
        int c=(n%b)+1;

//        long n=scanner.nextInt();
//        long a=scanner.nextInt();
//        long b=scanner.nextInt();
//        long count=0;
//        long max = (a > b)? a : b;
//        long temp=0;
//        for(long i = max;i <= a * b;i++) {
//            if((i % a == 0)&&(i % b == 0)) {
//                temp=i;
//            }
//        }
//        long j=(n%b)

//        int n=scanner.nextInt();
//        int q=scanner.nextInt();
//        int[] hours=new int[n+q];
//        int[] mins=new int[n+q];
//
//        for (int i=0;i<n;i++){
//            hours[i]=scanner.nextInt();
//            mins[i]=scanner.nextInt();
//        }
//        int[] sumh=new int[n+q+1];
//        int[] summ=new int[n+q+1];
//        for (int i=0;i<hours.length;i++){
//            sumh[i+1]=sumh[i]+hours[i];
//            summ[i+1]=summ[i]+mins[i];
//        }
////        System.out.println(Arrays.toString(sumh));
////        System.out.println(Arrays.toString(summ));
//        for (int i=0;i<q;i++){
//            int a=scanner.nextInt();
//            int b=scanner.nextInt();
//            int c=scanner.nextInt();
//            if(a==1){
//                hours[n]=b;
//                mins[n]=c;
//                sumh[n+1]=sumh[n]+b;
//                summ[n+1]=summ[n]+c;
//                n++;
////                System.out.println(Arrays.toString(sumh));
////                System.out.println(Arrays.toString(summ));
//            }else {
//                int h=sumh[c]-sumh[b-1];
//                int m=summ[c]-summ[b-1];
//                h+=(m/60);
//                m%=60;
//                System.out.println(h+":"+m);
//            }
//        }

    }
}
