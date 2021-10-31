package BUPT;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class test413_433 {

    /**
     * @apiNote 413. 学姐的桌面
     * @// TODO: 2021/2/22  */
    public void test413(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int n = in.nextInt();
            int key = in.nextInt();
            int count = 0;
            for (int i=0;i<n;i++){
                if(in.nextInt()<key) {
                    count++;
                }
            }
            double res = count*100.0/n;
            System.out.println(String.format("%.2f",res)+"%");
        }
    }

    /**
     * @apiNote 414. 学姐去学车
     * @// TODO: 2021/2/23  */
    public void test414(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int n = in.nextInt();
            int m = in.nextInt();
            int q = in.nextInt();
            for (int i=0;i<q;i++){
                int t = in.nextInt();
                if(t<=n){
                    System.out.println(1);
                }else {
                    t-=n;
                    t=t%(n+1);
                    if(t<=m&&t>=1){
                        System.out.println(2);
                    }else {
                        System.out.println(1);
                    }
                }
            }
        }
    }

    public void test415() throws IOException {
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            double eps = 1e-8;
            int x0 = reader.nextInt();
            int y0 = reader.nextInt();
            int x1 = 0 , y1 = 30;
            int x2 = 0 , y2 = 38;
            int a1 = y1-y0, b1 = x0 - x1, c1 = x1*y0 - x0*y1;
            int a2 = y2-y0, b2 = x0 - x2, c2 = x2*y0 - x0*y2;
            boolean flag = true;
            for (int i=0;i<1;i++){
                int tx = reader.nextInt();
                int ty = reader.nextInt();
                double d = reader.nextDouble();
                if(flag){
                    double d1 = Math.abs((a1*tx+b1*ty+c1)/(Math.sqrt(a1*a1+b1*b1)));
                    double d2 = Math.abs((a2*tx+b2*ty+c2)/(Math.sqrt(a2*a2+b2*b2)));
                    if((d1-d)<=eps&&(d2-d)<=eps){
                        flag = false;
                    }
                }
            }

            String res = flag ? "Shoot!":"Poor Mays";
            System.out.println(res);
        }
    }


    /**
     * @apiNote 416. 丁神去谷歌
     * @// TODO: 2021/2/23*/
    public void test416(){
        //java计算超时，没办法
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        double eps = 1e-8;
        while ((--T)>=0){
            int n = in.nextInt();
            int index = 1;
            if(n==1){
                System.out.println(1);
                continue;
            }
            double a = in.nextDouble();
            double b = in.nextDouble();
            double max = b/a;
            for (int i=1;i<n;i++){
                double ta = in.nextDouble();
                double tb = in.nextDouble();
                double c = tb/ta;
                if(c-max>=eps){
                    max = c;
                    index = i;
                    a = ta;
                }else if(Math.abs(max-c)<eps&&ta<a){
                    index = i;
                    a = ta;
                }
            }
            System.out.println(index+1);
        }
    }

    /**
     * @apiNote 417. 丁神又去谷歌
     * @// TODO: 2021/2/23*/
    public void test417() throws IOException {
        //  不可重复的01背包问题
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            int t = reader.nextInt();
            int n = reader.nextInt();
            int[] w = new int[n];
            int[] v = new int[n];
            for (int i=0;i<n;i++){
                int a = reader.nextInt();
                int b = reader.nextInt();
                w[i] = a;
                v[i] = b;
            }
            long[] dp = new long[t + 1];
            for(int i=0;i<n;i++){
                for (int j=t;j>=w[i];j--){
                    dp[j] = Math.max(dp[j],dp[j-w[i]]+v[i]);
                }
            }
            System.out.println(dp[t]);
        }
    }


    /**
     * @apiNote 428. 田田的账号
     * @// TODO: 2021/2/25*/
    public void test428() throws IOException {
        //就是求4的快速幂
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            int n = reader.nextInt()-1;
            long mod = 1000000007;
            long res = 1, a = 4;
            while (n!=0){
                if(n%2==1){
                    res = res*a%mod;
                }
                a = a*a%mod;
                n>>=1;
            }
            System.out.println(res);
        }
    }


    /**
     * @apiNote 430. 学姐的旋转图像
     * @// TODO: 2021/2/25  */
    public void test430(){
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            int h = in.nextInt();
            int w = in.nextInt();
            int d = in.nextInt()%360;
            d = (d+360)%360;
            int[][] matrix = new int[h][w];
            for (int i=0;i<h;i++){
                for (int j=0;j<w;j++){
                    matrix[i][j] = in.nextInt();
                }
            }
            if(d==0){
                //直接输出原数组
                for (int i=0;i<h;i++){
                    for (int j=0;j<w;j++){
                        System.out.print(matrix[i][j]);
                        if(j!=w-1) System.out.print(" ");
                    }
                    if(i!=h-1)System.out.println();
                }
            }else if(d==90){
                //90度旋转
                for (int i=0;i<w;i++){
                    for (int j=0;j<h;j++){
                        System.out.print(matrix[h-j-1][i]);
                        if(j!=h-1) System.out.print(" ");
                    }
                    if(i!=w-1)System.out.println();
                }
            }else if(d==180){
                //180度旋转 等价于左右翻转再上下翻转
                for(int i=0;i<h;i++){
                    for (int j=0;j<w/2;j++){
                        int temp = matrix[i][w-j-1];
                        matrix[i][w-j-1] = matrix[i][j];
                        matrix[i][j] = temp;
                    }
                }
                for (int j=0;j<w;j++){
                    for (int i=0;i<h/2;i++){
                        int temp = matrix[h-i-1][j];
                        matrix[h-i-1][j] = matrix[i][j];
                        matrix[i][j] = temp;
                    }
                }
                for (int i=0;i<h;i++){
                    for (int j=0;j<w;j++){
                        System.out.print(matrix[i][j]);
                        if(j!=w-1) System.out.print(" ");
                    }
                    if(i!=h-1)System.out.println();
                }
            }else if(d==270){
                //270度旋转
                for (int i=0;i<w;i++){
                    for (int j=0;j<h;j++){
                        System.out.print(matrix[j][w-i-1]);
                        if(j!=h-1) System.out.print(" ");
                    }
                    if(i!=w-1)System.out.println();
                }
            }
        }



    }


    /**
     * @apiNote 431. 立方体
     * @// TODO: 2021/3/1  */
    public void test431() throws IOException {
        //这题主要是巧妙使用正立方体的性质，求出重心
        class point{
            int x;
            int y;
            int z;

            public point(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }

            double dis(point c){
                return (x-c.x)*(x-c.x)+(y-c.y)*(y-c.y)+(z-c.z)*(z-c.z);
            }
        }
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            point[] points = new point[7];
            double[][] dis = new double[7][7];
            double len = 0;
            int mx = 0, my=0, mz=0;
            for (int i=0;i<7;i++){
                points[i] = new point(reader.nextInt(),reader.nextInt(),reader.nextInt());
            }
            for (int i=0;i<7;i++){
                for (int j=0;j<i;j++){
                    dis[i][j]=dis[j][i]=points[i].dis(points[j]);
                    if(dis[i][j]>len){
                        len = dis[i][j];
                        mx = points[i].x+points[j].x;
                        my = points[i].y+points[j].y;
                        mz = points[i].z+points[j].z;
                    }
                }
            }
            boolean[] used = new boolean[7];
            for (int i=0;i<7;i++){
                for (int j=0;j<i;j++){
                    if(dis[i][j]==len){
                        used[i]=used[j]=true;
                    }
                }
            }
            int index = 0;
            for (int i=0;i<7;i++){
                if(!used[i]){
                    index = i;
                    break;
                }
            }
            int x = mx - points[index].x;
            int y = my - points[index].y;
            int z = mz - points[index].z;
            System.out.println(x+" "+y+" "+z);
        }
    }


    /**
     * @apiNote 432.
     * @// TODO: 2021/3/1  */
    public void test432() throws IOException{
        reader.init();
        int T = reader.nextInt();
        while ((--T)>=0){
            String s = reader.next().trim();
            int[] arr = new int[26];
            int max = 0, n = s.length();
            for (char c:s.toCharArray()){
                max = Math.max(max,++arr[c-'a']);
            }
            if(max>((n+1)/2)){
                System.out.println(-1);
                continue;
            }
            int pre = -1;
            StringBuilder builder = new StringBuilder();
            while (n!=0){
                int flag = 0, t = -1;
                for(int i=0;i<26;i++){
                    if((n&1)==1&&arr[i]==(n+1)/2){
                        flag = 1;
                        t = i;
                    }
                    if(flag==0&&arr[i]>0&&t==-1&&i!=pre){
                        t =i;
                    }
                }
                builder.append((char)(t+'a'));
                arr[t]--;
                n--;
                pre = t;
            }
            System.out.println(builder.toString());
        }
    }


    /**
     * @apiNote 433 Fibnacci
     * @// TODO: 2021/3/2  */
    public void test433() throws IOException {
        //矩阵的快速幂
        class Matrix{
            private int row, col;
            private long[][] a = new long[4][4];
            private Matrix(int row, int col){
                this.row = row;
                this.col = col;
            }

            private Matrix mul(Matrix b){
                Matrix c = new Matrix(row,b.col);
                for (int i=0;i<c.row;i++){
                    for (int j=0;j<c.col;j++){
                        for (int k=0;k<col;k++){
                            int mod = 1000000007;
                            c.a[i][j] = (c.a[i][j]+(a[i][k]*b.a[k][j])% mod + mod)% mod;
                        }
                    }
                }
                return c;
            }

            private Matrix QuickPower(int n){
                Matrix c = new Matrix(row,col);
                //这里的c就是矩阵E
                for (int i=0;i<c.col;i++){
                    c.a[i][i]=1;
                }
                Matrix b = this;
                while (n!=0){
                    if((n&1)==1){
                        c = c.mul(b);
                    }
                    b = b.mul(b);
                    n>>=1;
                }
                return c;
            }


        }

/*
*
*  但是由于题意给出的是带n的递推式，无法直接得到只含常数的矩阵，需要对递推式变形。可以得到： f(n+3) = 2f(n+2) - f(n) +1.

           所以矩阵为：             2 0 -1 1                 初始的列向量为： 4

                                   1 0 0 0                                 1

                                   0 1 0 0                                 1

                                   0 0 0 1                                 1

坑：1.由于矩阵中有负数，使结果出现负数，需要先加一个模，在除模。

       2. 矩阵相乘，注意顺序。
*
* */
        reader.init();
        int T = reader.nextInt();

        while ((--T)>=0){
            int n = reader.nextInt();
            if(n<=1){
                System.out.println(1);
                continue;
            }
            Matrix A = new Matrix(4,1);
            A.a[0][0] = 4;
            A.a[1][0] = 1;
            A.a[2][0] = 1;
            A.a[3][0] = 1;
            Matrix B = new Matrix(4,4);
            B.a[0][0] = 2;
            B.a[0][2] = -1;
            B.a[0][3] = B.a[1][0] = B.a[2][1] = B.a[3][3] = 1;
            B = B.QuickPower(n-2);
            System.out.println(B.mul(A).a[0][0]);
        }
    }

}
