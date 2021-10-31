package Medium.BitTest;

public class rangeBitwiseAnd {

    public int rangeBitwiseAnd(int m, int n) {
        if(m==0&&n==0) return 0;
        int a=Integer.highestOneBit(m);
        int b=Integer.highestOneBit(n);
        if(a!=b) return 0;
        return a+rangeBitwiseAnd(m%a,n%a);
    }


    public static void main(String[] args) {
        int a=0;
        System.out.println(16&15);
        //System.out.println(new rangeBitwiseAnd().rangeBitwiseAnd(6,7));
    }
}
