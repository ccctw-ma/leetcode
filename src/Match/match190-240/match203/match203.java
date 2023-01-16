package Match.match203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class match203 {


    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] nums=new int[n+1];
        nums[rounds[0]]++;
        int max=0;
        for (int i=1;i<rounds.length;i++){
            int l=rounds[i-1];
            l++;
            if(l>n) l%=n;
            int r=rounds[i];
            if(r<l){
                r+=n;
            }
            for (int j=l;j<=r;j++){
                int index=j%n;
                if(index==0) index=n;
                nums[index]++;
                max=Math.max(max,nums[index]);
            }
        }
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if(nums[i]==max){
                list.add(i);
            }
        }
        return list;
    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int count=0;
        int n=piles.length;
        int index=n-2;
        for (int i=0;i<n/3;i++){
            count+=piles[index];
            index-=2;
        }
        return count;
    }


    //超时
    public int findLatestStep(int[] arr, int m) {

        if (arr.length == m) {
            return m;
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(arr.length + 1);
        m++;
        for (int i = arr.length - 1; i >= 0; i--) {
            int floor = set.floor(arr[i]);
            if (arr[i] - floor == m) {
                return i;
            }
            int cel = set.ceiling(arr[i]);
            if (cel - arr[i] == m) {
                return i;
            }
            set.add(arr[i]);
        }
        return -1;
//        int n=arr.length;
//        if(m==arr.length) return n;
//        int[] nums=new int[n];
//        Arrays.fill(nums,1);
//        for (int i=n-1;i>=0;i--){
//            nums[arr[i]-1]=0;
//            if(i<m) break;
//            if(find(nums,m)){
//                return i;
//            }
//        }
//        return -1;
    }

    private boolean find(int[] nums,int m){
        int count=0;
        int left=0;
        while (left<nums.length){
            if(nums[left]==1) {
                count++;
                left++;
                continue;
            } else if(count==m){
                return true;
            }
            count=0;
            while (left<nums.length&&nums[left]==0) left++;
        }
        return count==m;
    }


    public static void main(String[] args) {
        System.out.println(new match203().findLatestStep(new int[]{3,5,1,2,4},1));
    }
}
