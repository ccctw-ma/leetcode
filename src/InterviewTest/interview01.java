package InterviewTest;

import java.util.*;

public class interview01 {

    public String toHex(int num) {
        int[] arr=new int[32];
        char[] r=new char[8];
        for(int i=31;i>=0;i--){
            if((num&1)==1){
                arr[i]=1;
            }else {
                arr[i]=0;
            }
            num>>=1;
        }
        int index=0;
        for(int i=0;i<32;i+=4){
            int temp=0;
            for(int j=0;j<4;j++){
                temp=temp*2+arr[i+j];
            }
            if(temp>=0&&temp<10){
                r[index++]= (char) (temp+'0');
            }else{
                r[index++]= (char) (temp-10+'a');
            }
        }
        int s=0;
        while(r[s]=='0') s++;
        return String.valueOf(r,s,8-s);
    }

    public boolean isNStraightHand(int[] hand, int W) {
        int len=hand.length,n=len/W;
        if(len%W!=0) return false;
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int i:hand){
            queue.offer(i);
        }
        for (int i=0;i<n;i++){
            int temp=queue.peek();
            for (int j=0;j<W;j++){
                if(queue.contains(temp+j)){
                    queue.remove(temp+j);
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public int sumSubarrayMins(int[] A) {
        int mod=1000000007,len=A.length;
        int[] dp=new int[len];
        Arrays.fill(dp,Integer.MAX_VALUE);
        int sum=0;
        for (int i=0;i<len;i++){
            dp[i]=A[i];
            sum=(A[i]+sum)%mod;
            for (int j=i-1;j>=0;j--){
                dp[j]= Math.min(dp[j],A[i]);
                sum=(dp[j]+sum)%mod;
            }
        }
        return sum;
    }


    public int sumSubarrayMins2(int[] A) {
        int MOD = 1_000_000_007;

        Stack<RepInteger> stack = new Stack();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            stack.push(new RepInteger(A[j], count));
            dot += A[j] * count;
            ans += dot;
            ans %= MOD;
        }
        return ans;
    }


    class RepInteger {
        int val, count;
        RepInteger(int v, int c) {
            val = v;
            count = c;
        }
    }


    public int minJump(int[] jump) {
        int[] dp=new int[jump.length];
        Arrays.fill(dp,jump.length);
        if(jump[0]>=jump.length) return 1;
        dp[jump[0]]=1;
        int max=jump[0],pre=0,sum=jump.length;
        while(max<jump.length){
            int temp=max;
            for(int i=temp;i>=pre;i--){
                dp[i]=Math.min(dp[i],dp[temp]+1);
                if(i+jump[i]>=jump.length){
                    sum=Math.min(sum,dp[i]+1);
                    max=jump.length;
                    continue;
                }
                max=Math.max(max,i+jump[i]);
                dp[i+jump[i]]=Math.min(dp[i+jump[i]],dp[i]+1);

            }
            pre=temp;
        }
        return sum;
    }


    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] res=new int[requirements.length];
        Arrays.fill(res,-1);
        int c=0,r=0,h=0;
        Map<int[],Integer> map=new HashMap<>();
        for(int i=0;i<requirements.length;i++){
            if(c>=requirements[i][0]&&r>=requirements[i][1]&&h>=requirements[i][2]&&res[i]==-1){
                res[i]=0;
            }else{
                map.put(requirements[i],i);
            }
        }
        for(int i=0;i<increase.length;i++){
            c+=increase[i][0];
            r+=increase[i][1];
            h+=increase[i][2];
            for (int[] temp:map.keySet()){
                if(c>=temp[0]&&r>=temp[1]&&h>=temp[2]){
                    res[map.get(temp)]=i+1;
                    map.remove(temp);
                }
            }
        }
        return res;
    }

    public int findMinFibonacciNumbers(int k) {
        if(k==1) return 1;
        int a=1,b=1,c=2,res=0;
        List<Integer> list=new ArrayList<>();
        while(c<=k){
            list.add(c);
            a=b;
            b=c;
            c=a+b;
        }
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i)<=k){
                res+=(k/list.get(i));
                k%=list.get(i);
                if(k==0) break;
            }
        }
        return res;

    }


    private List<String> list;
    private int n;
    public String getHappyString(int n, int k) {
        this.n=n;
        this.list=new ArrayList<>();
        get("a",'a',1);
        get("b",'b',1);
        get("c",'c',1);
        if(list.size()<k) return "";
        return list.get(k-1);
    }

    public void get(String s,char a,int step){
        if(step==n){
            list.add(s);
            return;
        }
        step++;
        if(a=='a'){
            get(s +'b','b',step);
            get(s +'c','c',step);
        }else if(a=='b'){
            get(s +'a','a',step);
            get(s +'c','c',step);
        }else {
            get(s +'a','a',step);
            get(s +'b','b',step);
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new interview01().getTriggerTime(new int[][]{{2, 8, 4},{2,5,0},{10,9,8}}, new int[][]{{2,11,3},{15,10,7},{9,17,12},{8,1,14}})));
        System.out.println(new interview01().getHappyString(10,100));
    }

//    int[] res=new int[requirements.length];
//        Arrays.fill(res,-1);
//        int c=0,r=0,h=0;
//        for(int i=0;i<requirements.length;i++){
//            if(c>=requirements[i][0]&&r>=requirements[i][1]&&h>=requirements[i][2]&&res[i]==-1){
//                res[i]=0;
//            }
//        }
//        for(int i=0;i<increase.length;i++){
//            c+=increase[i][0];
//            r+=increase[i][1];
//            h+=increase[i][2];
//            for(int j=0;j<requirements.length;j++){
//                if(res[j]==-1){
//                    if(c>=requirements[j][0]&&r>=requirements[j][1]&&h>=requirements[j][2]){
//                        res[j]=i+1;
//                    }
//                }
//            }
//        }
//        return res;
}
