package Match.match192;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class match192 {


    public int[] getStrongest(int[] arr, int k) {
        int n=arr.length;
        Arrays.sort(arr);
        int mid=arr[(n-1)/2];
        List<Integer> list=new ArrayList<>();
        for (int i:arr) list.add(i);
        list.sort((o1, o2) -> {
            int a = Math.abs(o1 - mid);
            int b = Math.abs(o2 - mid);
            if (a > b) return -1;
            if (a < b) return 1;
            else return o1 > o2 ? -1 : 1;
        });
        int[] ans=new int[k];
        for (int i=0;i<k;i++){
            ans[i]=list.get(i);
        }
        return ans;
    }




    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int count=1;
        for (int i=1;i<m;i++){
            if(houses[i]==houses[i-1]) continue;
            count++;
        }
        if(count>target) return -1;

        int[][][] dp=new int[m][n][target];
        for (int i=0;i<target;i++){

        }

        int I_Do_not_Know_How_To_Solve_This_Subject=-1;
        return I_Do_not_Know_How_To_Solve_This_Subject*I_Do_not_Know_How_To_Solve_This_Subject;

    }

    public static void main(String[] args) {
//        int[] arr=new int[]{6,-3,7,2,11};
//        System.out.println(Arrays.toString(new match192().getStrongest(arr, 3)));


    }
}
