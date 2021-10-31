package Match.match225;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class match225_D {


    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int[] arr = new int[n+1];
        int max;
        Set<Integer> set=new HashSet<>();
        for (int[] friendship : friendships) {
            int a = friendship[0]-1;
            int b = friendship[1]-1;
            if(!isConnected(languages[a],languages[b])){
                if(!set.contains(a)){
                    set.add(a);
                    for(int i:languages[a]){
                        arr[i]++;
                    }
                }
                if(!set.contains(b)){
                    set.add(b);
                    for(int i:languages[b]){
                        arr[i]++;
                    }
                }

            }
        }
        max = Arrays.stream(arr).max().getAsInt();
        return set.size()-max;
    }

    public boolean isConnected(int[] a,int[] b){
        for (int i:a){
            for (int j:b){
                if(i==j){
                    return true;
                }
            }
        }
        return false;
    }

    public int[] waysToFillArray(int[][] queries) {
        int n = queries.length;
        int mod = 1000_000_007;
        int[] arr = new int[10001];
        Arrays.fill(arr,1);
        int[] res = new int[n];
        int pre = 1;
        for (int i=0;i<n;i++){
            int a = queries[i][0];
            int b = queries[i][1];
            for (int j=pre;j<a;j++){
                int[] temp = new int[10001];
                for (int k=1;k<=10000;k++){
                    for (int h=1;h<=k/2;h++){
                        if(k%h==0){
                            temp[k]=(arr[k/h]+temp[k])%mod;
                        }
                    }
                }
                arr = temp;
            }
            pre = a;
            res[i] = arr[b];
        }
        return res;
    }




    public int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] res = new int[n+1];
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new match225_D().waysToFillArray(new int[][]{{1,1},{2,2},{3,3}})));
    }

}
