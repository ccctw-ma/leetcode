package BUPT;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {



    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int n = in.nextInt();
            int k = in.nextInt();
        }


    }


}

class reader {
    private static BufferedReader reader;
    private static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init() {
        reader = new BufferedReader(
                new InputStreamReader(System.in) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    public static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static boolean hasNext() throws  IOException{
        return reader.readLine().length()!=0;
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}






class test{



    /**
     * @// TODO: 2021/3/3
     * */
    private int[] arr = new int[64];
    private int mod = 1000000009;
    private void init(){
        arr[0] = 1;
        arr[1] = 2;
        for (int i=2;i<64;i++){
            arr[i] = (arr[i-1] + arr[i-2])%mod;
        }
    }
    private int cal(int[] b, int n){
        if(n<0) return 0;
        if(n==0) return 1;
        if(b[n]==0) return cal(b,n-1);
        if(n==1) return 2;
        if(b[n]==1&&b[n-1]==1){
            return (arr[n-1]+arr[n-2])%mod;
        }else {
            return (arr[n - 1] + cal(b, n - 2)) % mod;
        }
    }
    public void test() throws IOException {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            long n = in.nextLong();
            int[] b = new int[65];
            int num = 1;
            while (n!=0){
                if(n%2==1){
                    b[num++] = 1;
                }else {
                    b[num++] = 0;
                }
                n>>=1;
            }
            System.out.println(cal(b,num-1));
        }
    }

    /**
     * @apiNote 620. winoros的树
     * @// TODO: 2021/3/1  */
    public void test620() throws IOException {
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            int[] arr = new int[4];
            for (int i=0;i<4;i++){
                arr[i] = reader.nextInt();
            }
            Arrays.sort(arr);
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            int d = arr[3];
            if((a+b>c)||(a+c>d)||(b+c)>d){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }

        }
    }

    public void test1(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            int n = in.nextInt();

            int a = 2;
            int b = 3;
            List<Integer> list1 = new ArrayList<Integer>();
            list1.add(1);
            List<Integer> list2 = new ArrayList<Integer>();
            list2.add(1);
            while (a<=n){
                list1.add(a);
                a<<=1;
            }
            while (b<=n){
                list2.add(b);
                b<<=1;
            }
            if(list1.get(list1.size()-1)>=list2.get(list2.size()-1)){
                for (int i=0;i<list1.size();i++){
                    System.out.print(list1.get(i));
                    if(i!=list1.size()-1) System.out.print(" ");
                }
            }else {
                for (int i=0;i<list2.size();i++){
                    System.out.print(list2.get(i));
                    if(i!=list2.size()-1) System.out.print(" ");
                }
            }
            System.out.println();

        }
    }

    /**
     * @apiNote 604
     * @// TODO: 2021/3/11
     * */
    public void test604(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int[] arr = new int[12];
            for (int i=0;i<3;i++){
                String a = in.next();
                String b = in.next();
                String o = in.next();
                if(o.equals("even")){
                    for (char c:a.toCharArray()) arr[c-'A']=9;
                    for (char c:b.toCharArray()) arr[c-'A']=9;
                }else if(o.equals("up")){
                    for (char c:a.toCharArray()) arr[c-'A']+=1;
                    for (char c:b.toCharArray()) arr[c-'A']-=1;
                }else {
                    for (char c:a.toCharArray()) arr[c-'A']-=1;
                    for (char c:b.toCharArray()) arr[c-'A']+=1;
                }
            }
            int max=0,maxi=-1;
            for(int i=0; i<12; i++)
                if(arr[i]<4)
                {
                    int t=Math.abs(arr[i]);
                    if(t==max){
                        maxi=-1;
                    } else if(t>max) {
                        max=t;
                        maxi=i;
                    }
                }
            if(maxi>=0) {
                char ch= (char) (maxi+'A');
                if(arr[maxi]<0){
                    System.out.println(ch+" is light.");
                } else{
                    System.out.println(ch+" is heavy.");
                }
            }else {
                System.out.println("cannot judge.");
            }
        }
    }

    /**
     * @apiNote 605. 陈队的树 贪心即可
     * @// TODO: 2021/3/12  */
    public void test605() throws IOException {
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            int n = reader.nextInt();//树的数量
            int m = reader.nextInt();//修剪器的数量
            int[] trees = new int[n];
            int[] clippers = new int[m];
            for (int i=0;i<n;i++) trees[i] = reader.nextInt();
            for (int i=0;i<m;i++) clippers[i] = reader.nextInt();
            Arrays.sort(trees);
            Arrays.sort(clippers);
            int index = n-1;//树
            for (int i=0;i<m;i++){
                if(index>=0&&trees[index]>=clippers[i]){
                    trees[index] = clippers[i];
                    index--;
                }
            }
            int sum = 0;
            for (int i:trees) sum+=i;
            System.out.println(sum);
        }
    }
}





