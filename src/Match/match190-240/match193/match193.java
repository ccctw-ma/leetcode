package Match.match193;

import java.util.*;

public class match193 {


    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer,Integer> map=new TreeMap<>();
        for (int i:arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        List<Integer> list= new ArrayList<>();
        for (int i:map.keySet()){
            list.add(map.get(i));
        }
        Collections.sort(list);
        int count=list.size();
        for (int i:list){

            if(i<=k){
                k-=i;
                count--;
            }else{
                break;
            }
        }
        return count;
    }



    public int minDays(int[] b, int m, int k) {
        int inf = (int)1e9 + 1;
        int ans = inf;
        int n = b.length;

        int l = 0;
        int r = inf;
        while(l < r){
            int mid = (l + r) / 2;
            if(check(b, m, k, mid)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l == inf ? -1 : l;
    }

    public boolean check(int[] b, int m, int k, int t){
        int cnt = 0;
        int total = 0;
        for(int i = 0; i < b.length; i++){
            if(b[i] > t){
                cnt = 0;
                continue;
            }
            cnt++;
            if(cnt == k){
                total++;
                cnt = 0;
            }
        }
        return total >= m;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{7,7,7,7,12,7,7};
        System.out.println(new match193().minDays(arr,2,3));
    }
}
