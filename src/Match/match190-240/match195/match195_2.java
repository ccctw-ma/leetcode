package Match.match195;

import java.util.*;

public class match195_2 {

    public boolean isPathCrossing(String path) {
        Map<Integer,Set<Integer>>  set=new HashMap<>();
        int x=0,y=0;
        set.put(x,new HashSet<>());
        set.get(x).add(y);
        for (char c:path.toCharArray()){
            if(c=='N'){
                x--;
                if(set.containsKey(x)){
                    if(set.get(x).contains(y)){
                        return true;
                    }else {
                        set.get(x).add(y);
                    }
                }else {
                    Set<Integer> set1=new HashSet<>();
                    set1.add(y);
                    set.put(x,set1);
                }
            }else if(c=='S'){
                x++;
                if(set.containsKey(x)){
                    if(set.get(x).contains(y)){
                        return true;
                    }else {
                        set.get(x).add(y);
                    }
                }else {
                    Set<Integer> set1=new HashSet<>();
                    set1.add(y);
                    set.put(x,set1);
                }
            }else if(c=='E'){
                y++;
                if(set.containsKey(x)){
                    if(set.get(x).contains(y)){
                        return true;
                    }else {
                        set.get(x).add(y);
                    }
                }else {
                    Set<Integer> set1=new HashSet<>();
                    set1.add(y);
                    set.put(x,set1);
                }
            }else {
                y--;
                if(set.containsKey(x)){
                    if(set.get(x).contains(y)){
                        return true;
                    }else {
                        set.get(x).add(y);
                    }
                }else {
                    Set<Integer> set1=new HashSet<>();
                    set1.add(y);
                    set.put(x,set1);
                }
            }
        }
        return false;
    }

    public boolean canArrange(int[] arr, int k) {
        int n=arr.length;
        for (int i=0;i<n;i++){
            arr[i]%=k;
            if(arr[i]<0) arr[i]+=k;
        }
        int[] buc=new int[k];
        for (int i:arr) buc[i]++;
        for (int i=0;i<buc.length;i++){
            int c=(k-i)%k;
            if(c==i&&buc[c]%2!=0){
                return false;
            }else if(buc[i]!=buc[c]){
                return false;
            }
        }
        return true;
    }

    public int numSubseq(int[] nums, int target) {
        int mod=1000000007;
        Arrays.sort(nums);
        int n=nums.length;
        int[] dp=new int[n];
        int min=nums[0];
        int max=nums[0];
        int sum=0;
        if(nums[n-1]*2<=target) return pow(nums.length)-1;
        for (int i=n-1;i>=0;i--){
            int j=i;
            while (j>=0&&(nums[i]+nums[j])>target) j--;
            int x=i-j-1;

        }
        return 0;
    }

    private int pow(int x){
        if(x<0) return 0;
        int res=1;
        for (int i=0;i<x;i++){
            res*=2;
            res%=1000000007;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new match195_2().isPathCrossing("NES"));
    }
}
