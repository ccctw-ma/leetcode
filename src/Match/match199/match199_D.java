package Match.match199;

import java.util.HashSet;
import java.util.Set;

public class match199_D {


    public int countOdds(int low, int high) {
        if(high==low) return low%2==1?1:0;
        int n=(high-low+1);
        if(n%2==0) return n%2;
        if(low%2==0){
            n--;
        }else {
            n++;
        }
        return n%2;
    }


    public int numOfSubarrays(int[] arr) {
        int n=arr.length;
        int mod=1_000_000_007;
        int even=0;
        int odd=0;
        int count=0;
        for (int num : arr) {
            if (num % 2 == 0) {
                even++;
            } else {
                int t = even;
                even = odd;
                odd = t+1;
            }
            count = (count + odd) % mod;
        }
        return count;
    }


    public int numSplits(String s) {
        int n=s.length();
        Set<Character> left=new HashSet<>();
        Set<Character> right=new HashSet<>();
        int[] ll=new int[n];
        int[] rr=new int[n];
        for (int i=0;i<n;i++){
            char c=s.charAt(i);
            left.add(c);
            ll[i]=left.size();
        }
        for (int i=n-1;i>=0;i--){
            char c=s.charAt(i);
            right.add(c);
            rr[i]=right.size();
        }
        int count=0;
        for (int i=0;i<n-1;i++){
            if(ll[i]==rr[i+1]){
                count++;
            }
        }
        return count;
    }


    public int minNumberOperations(int[] target) {
        int count=0;
        int n=target.length;
        int index=0;
        while (true){
            int temp=index;
            while (index<n&&target[index]==0) index++;
            if(temp==0&&index==n) return count;
            count++;
            while (index<n&&target[index]!=0){
                target[index]--;
                index++;
            }
            while (index<n&&target[index]==0) index++;
            if(index==n){
                index=0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new match199_D().minNumberOperations(new int[]{1,0,1,0,1}));
    }
}
