package Match.match193;

import java.util.*;

public class match193D {



    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, List<int[]>> map=new TreeMap<>();

        int left=0;
        int right=0;
        int sum=0;
        while (left<arr.length){

            if(right==arr.length&&sum<target) break;
            while (sum<target&&right<arr.length){
                sum+=arr[right++];
            }
            if(sum==target){
                int length=right-left;
                if(map.containsKey(length)){
                    map.get(length).add(new int[]{left,right-1});
                }else {
                    List<int[]> list=new ArrayList<>();
                    list.add(new int[]{left,right-1});
                    map.put(length,list);
                }
            }
            while (sum>=target&&left<right){
                sum-=arr[left++];
                if(sum==target) break;
            }

        }

        int min=0;
        Queue<int[]> queue=new LinkedList<>();
        for(Integer integer:map.keySet()){
            List<int[]> list=map.get(integer);
            for (int[] ints : list) {
                queue.offer(ints);
            }
        }

        if(queue.size()<=1) return -1;

        int n=1;
        int[] fir=queue.poll();
        int l=fir[0];
        int r=fir[1];
        min+=r-l+1;
        while (!queue.isEmpty()&&n==1){
            int[] array=queue.poll();
            int index1=array[0];
            int index2=array[1];
            if(index1>r){
                n--;
                min+=array[1]-index1+1;
            }else if(index2<l){
                n--;
                min+=index2-index1+1;
            }
        }
        return n==1?-1:min;



    }




    public int minDistance(int[] houses, int k) {
        if(k==houses.length) return 0;
        int n=houses.length;
        int[][] dp=new int[n][k];
        return 0;
    }


    public static void main(String[] args) {
//        int[] arr=new int[]{31,1,1,18,15,3,15,14};
//        System.out.println(new match193D().minSumOfLengths(arr,33));


    }
}
