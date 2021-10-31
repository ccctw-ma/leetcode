package Match.match196;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class match196 {

    public int numSubmat(int[][] mat) {
        int a=mat.length;
        int b=mat[0].length;
        //0->left 1->top
        int[][][] dp=new int[a][b][2];
        int sum=0;
        if(mat[0][0]==1){
            dp[0][0][0]=1;
            dp[0][0][1]=1;
            sum++;
        }
        for (int i=1;i<a;i++){
            if(mat[i][0]==1){
                dp[i][0][0]=1;
                dp[i][0][1]=1+dp[i-1][0][1];
                sum+=dp[i][0][1];
            }
        }
        for (int j=1;j<b;j++){
            if(mat[0][j]==1){
                dp[0][j][1]=1;
                dp[0][j][0]=1+dp[0][j-1][0];
                sum+=dp[0][j][0];
            }
        }
        for (int i=1;i<a;i++){
            for (int j=1;j<b;j++){
                if(mat[i][j]==1){
                    dp[i][j][0]=1+dp[i][j-1][0];
                    dp[i][j][1]=1+dp[i-1][j][1];
                    sum+=(dp[i][j][0]+dp[i][j][1]-1);
                    int left=dp[i][j][0];
                    int top=dp[i][j][1];
                    int min=top;
                    for (int k=j-1,t=0;t<left-1;t++,k--){
                        int temp=dp[i][k][1];
                        if(temp==1) break;
                        if(temp>min){
                            temp=min;
                        }
                        min=Math.min(min,temp);
                        sum+=(min-1);
                    }

                }
            }
        }
        return sum;

    }


    public String minInteger(String num, int k) {
        char[] s = num.toCharArray();
        int n = s.length;

        IntegerBIT bit = new IntegerBIT(s.length);
        for(int i = 1; i <= s.length; i++){
            bit.update(i, 1);
        }
        Deque<Integer>[] dq = new Deque[10];
        for(int i = 0; i < 10; i++){
            dq[i] = new ArrayDeque<>(n);
        }
        for(int i = 0; i < n; i++){
            dq[s[i] - '0'].add(i);
        }

        StringBuilder ans = new StringBuilder(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 10; j++){
                if(!dq[j].isEmpty() && bit.query(dq[j].getFirst()) <= k)
                {
                    k -= bit.query(dq[j].getFirst());
                    bit.update(dq[j].getFirst() + 1, -1);
                    dq[j].removeFirst();
                    ans.append((char)(j + '0'));
                    break;
                }
            }
        }

        return ans.toString();
    }

    public class IntegerBIT {
        private int[] data;
        private int n;

        /**
         * 创建大小A[1...n]
         */
        public IntegerBIT(int n) {
            this.n = n;
            data = new int[n + 1];
        }

        /**
         * 查询A[1]+A[2]+...+A[i]
         */
        public int query(int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i) {
                sum += data[i];
            }
            return sum;
        }

        public int query(int l, int r) {
            return query(r) - query(l - 1);
        }

        /**
         * 将A[i]更新为A[i]+mod
         */
        public void update(int i, int mod) {
            if (i <= 0) {
                return;
            }
            for (; i <= n; i += i & -i) {
                data[i] += mod;
            }
        }

        /**
         * 将A全部清0
         */
        public void clear() {
            Arrays.fill(data, 0);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                builder.append(query(i) - query(i - 1)).append(' ');
            }
            return builder.toString();
        }
    }


    public static void main(String[] args) {
        int[][] mat=new int[][]{{1,0,1,1,1,1,1},{1,1,0,0,0,1,1},{1,1,1,0,0,1,1},{1,0,1,0,1,0,1},{1,0,1,1,1,0,1},{1,1,0,1,1,1,1},{1,0,0,1,1,0,1}};
        System.out.println(new match196().minInteger("4321",4));

    }
}
