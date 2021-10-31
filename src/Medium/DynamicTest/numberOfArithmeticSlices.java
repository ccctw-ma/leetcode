package Medium.DynamicTest;

public class numberOfArithmeticSlices {


    public int numberOfArithmeticSlices(int[] A) {
        if(A.length<=2) return 0;
        int diff=A[1]-A[0];
        int[][] dp=new int[A.length][2];//0放长度，1放数量
        dp[0][0]=1;
        dp[0][1]=0;
        dp[1][0]=2;
        dp[1][1]=0;
        for (int i=2;i<A.length;i++){
            if(A[i]-A[i-1]==diff){
                dp[i][0]=dp[i-1][0]+1;
                if(dp[i][0]>=3){
                    dp[i][1]=dp[i-1][1]+1;
                }
            }else {
                diff=A[i]-A[i-1];
                dp[i][0]=2;
                dp[i][1]=0;
            }
        }
        int sum=0;
        for (int[] ints : dp) sum += ints[1];
        return sum;
    }

    //可以进行优化，减少空间复杂度
    public int numberOfArithmeticSlices2(int[] A) {
        int res=0;
        int add=0;
        if (A.length<=2){
            return 0;
        }
        for (int i = 2; i <A.length ; i++) {
            if (A[i]-A[i-1]==A[i-1]-A[i-2]){
                res+=++add;
            }else {
                add=0;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
