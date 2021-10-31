package Match.match197;

import java.util.*;

public class match197_D {

    public String reformatDate(String date) {
        String[] arrs=date.split(" ");
        Map<String,String> map=new HashMap<>();
        map.put("Jan","01");
        map.put("Feb","02");
        map.put("Mar","03");
        map.put("Apr","04");
        map.put("May","05");
        map.put("Jun","06");
        map.put("Jul","07");
        map.put("Aug","08");
        map.put("Sep","09");
        map.put("Oct","10");
        map.put("Nov","11");
        map.put("Dec","12");
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(arrs[2]).append('-');
        stringBuilder.append(map.get(arrs[1])).append('-');
        String s=arrs[0];
        char a=s.charAt(0);
        char b=s.charAt(1);
        if(Character.isDigit(b)){
            stringBuilder.append(a).append(b);
        }else {
            stringBuilder.append('0').append(a);
        }
        return stringBuilder.toString();
    }

    public int rangeSum(int[] nums, int n, int left, int right) {
        int mod=1000_000_007;
        int[] arr= new int[n+1];
        for (int i=1;i<=n;i++){
            arr[i]=arr[i-1]+nums[i-1];
        }
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<=n;i++){
            for (int j=i+1;j<=n;j++){
                list.add(arr[j]-arr[i]);
            }
        }
        list.sort(Comparator.comparingInt(o -> o));
        int sum=0;
        for (int i=left-1;i<right;i++){
            sum=((sum+list.get(i))%mod);
        }
        return sum;
    }

    public int minDifference(int[] nums) {
        int n=nums.length;
        if(n<=4) return 0;
        Arrays.sort(nums);
        int a=nums[n-1]-nums[3];
        int b=nums[n-2]-nums[2];
        int c=nums[n-3]-nums[1];
        int d=nums[n-4]-nums[0];
        return Math.min(Math.min(a,b),Math.min(c,d));
    }

    public boolean winnerSquareGame(int n) {
        if(n==1) return true;
        if(n==2) return false;
        boolean[] dp=new boolean[n+1];
        for (int i=1;i*i<=n;i++){
            dp[i*i]=true;
        }
        if(dp[n]) return true;
        dp[2]=false;
        for (int i=3;i<=n;i++){
            for (int k=1;(i-k*k)>=1;k++){
                if(!dp[i-k*k]){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new match197_D().winnerSquareGame(17));
    }
}
