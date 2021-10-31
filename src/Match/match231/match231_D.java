package Match.match231;

import java.util.HashSet;
import java.util.Set;

public class match231_D {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int res = -1;
        int min = Integer.MAX_VALUE;
        for (int i=0;i<points.length;i++){
            int a = points[i][0];
            int b = points[i][1];
            if(a==x||b==y){
                int dis = Math.abs(x-a)+Math.abs(y-b);
                if(dis<min){
                    min = dis;
                    res = i;
                }
            }
        }
        return res;
    }

    public boolean checkPowersOfThree(int n) {
        Set<Integer> set = new HashSet<>();
        int base = 1;
        int index = 0;
        while (base<=n){
            base*=3;
            index*=2;
            index+=1;
        }
        while (index!=0){
            int t = index;
            int b = 1;
            int sum = 0;
            while (t!=0){
                if((t&1)==1){
                    sum+=b;
                }
                b*=3;
                t>>=1;
            }
            if(sum==n){
                return true;
            }
            index--;
        }
        return false;

    }


    public int beautySum(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int l=3;l<=s.length();l++){
            int[] arr = new int[26];
            for (int i=0;i<l;i++){
                arr[chars[i]-'a']++;
            }
            sum+=findMin(arr);

            for (int i=l;i<s.length();i++){
                int a = chars[i]-'a';
                arr[a]++;
                int b = chars[i-l]-'a';
                arr[b]--;
                sum+=findMin(arr);
            }
        }
        return sum;
    }

    public int findMin(int[] arr){
        int min = 500;
        int max = 1;
        for (int i:arr){
            if(i!=0){
                min = Math.min(i,min);
                max = Math.max(i,max);
            }
        }
        return max-min;
    }


    public static void main(String[] args) {
        System.out.println(new match231_D().beautySum("aabcbaa"));
    }
}
