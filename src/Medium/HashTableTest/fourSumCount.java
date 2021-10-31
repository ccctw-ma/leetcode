package Medium.HashTableTest;

import java.util.Arrays;


/*454. 四数相加 II
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:

输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
通过次数43,975提交次数76,450*/


/**
 * @author 马世臣
 * @// TODO: 2020/11/27  */



public class fourSumCount {


    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n=D.length;
        if(n==0) return 0;
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int count=0;
        int mina=A[0],minb=B[0],minc=C[0],mind=D[0];
        int maxa=A[n-1],maxb=B[n-1],maxc=C[n-1],maxd=D[n-1];
        if(mina+minb+minc+mind>0||maxa+maxb+maxc+maxd<0)
            return 0;
        for (int i=0;i<n;){
            if(A[i]+minb+minc+mind>0||A[i]+maxb+maxc+maxd<0) break;
            int a=1;
            while (++i<n&&A[i]==A[i-1]) a++;
            for (int j=0;j<n;){
                if(A[i-1]+B[j]+minc+mind>0||A[i-1]+B[j]+maxc+maxd<0) break;
                int b=1;
                while (++j<n&&B[j]==B[j-1]) b++;
                for (int k=0;k<n;){
                    if(A[i-1]+B[j-1]+C[k]+mind>0||A[i-1]+B[j-1]+C[k]+maxd<0) break;
                    int c=1;
                    while (++k<n&&C[k]==C[k-1]) c++;
                    int tar=-(A[i-1]+B[j-1]+C[k-1]);
                    int index=Arrays.binarySearch(D,tar);
                    if(index>=0){
                        int d=1;
                        for (int l=index-1;l>=0&&D[l]==tar;l--) d++;
                        for (int r=index+1;r<n&&D[r]==tar;r++) d++;
                        count+=(a*b*c*d);
                    }
                }
            }
        }
        return count;
    }

//        Map<Integer,Integer> map1=new HashMap<>();
//        Map<Integer,Integer> map2=new HashMap<>();
//        Map<Integer,Integer> map3=new HashMap<>();
//        Map<Integer,Integer> map4=new HashMap<>();
//        for (int i:A)
//            map1.put(i,map1.getOrDefault(i,0)+1);
//        for (int i:B)
//            map2.put(i,map2.getOrDefault(i,0)+1);
//        int minc=Integer.MAX_VALUE, maxc=Integer.MIN_VALUE;
//        for (int i:C){
//            map3.put(i,map3.getOrDefault(i,0)+1);
//            minc=Math.min(i,minc);
//            maxc=Math.max(i,maxc);
//        }
//        int minD=Integer.MAX_VALUE,maxd=Integer.MIN_VALUE;
//        for (int i:D){
//            map4.put(i,map4.getOrDefault(i,0)+1);
//            minD=Math.min(minD,i);
//            maxd=Math.max(maxd,i);
//        }
//        int count=0,sum=0;
//        for (int a:map1.keySet()){
//            sum+=a;
//            int num1=map1.get(a);
//            for (int b:map2.keySet()){
//                sum+=b;
//                int num2=map2.get(b);
//                int temp=sum+minc+minD;
//                int temp2=sum+maxc+maxd;
//                if(temp<=0||temp2>=0){
//                    for (int c:map3.keySet()){
//                        sum+=c;
//                        int num3=map3.get(c);
//                        if(sum+minD<=0||sum+maxd>=0){
//                            count+=(num1*num2*num3)*(map4.getOrDefault(-sum,0));
//                            sum-=c;
//                        }
//                    }
//                }
//                sum-=b;
//            }
//            sum-=a;
//        }

    public static void main(String[] args) {
        System.out.println(new fourSumCount().fourSumCount(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2}));
    }
}
