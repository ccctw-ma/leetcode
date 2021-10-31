package Match.match195;

import java.util.*;

public class match195 {


    public int longestSubarray(int[] nums) {
        int max=0;

        int index=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]==1){
                index++;
                continue;
            }
            int left=i-1;
            int right=i+1;
            int count=0;
            while (left>=0&&nums[left--]==1) count++;
            while (right<nums.length&&nums[right++]==1) count++;
            max=Math.max(max,count);
        }
        if(index==nums.length) return index-1;
        return max;
    }


    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] degrees=new int[n];
        int[] output=new int[n];
        int[][] graph=new int[n][n];
        for (int[] arr:dependencies){
            int a=arr[0];
            int b=arr[1];
            graph[a-1][b-1]=1;
            degrees[b-1]++;
            output[a-1]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i=0;i<n;i++){
            if(degrees[i]==0){
                queue.add(i);
            }
        }
        int res=0;
        while (!queue.isEmpty()){
            int num=queue.size();
            res++;
            Set<Integer> set=new HashSet<>();
            for (int i=0;i<Math.min(num,k);i++){
                int next=queue.poll();
                for (int j=0;j<n;j++){
                    if(graph[next][j]==1){
                        degrees[j]--;
                        if(degrees[j]<=0){
                            set.add(j);
                        }
                    }
                }
            }

            List<Integer> list=new ArrayList<>(set);
            list.addAll(queue);
            queue.clear();
            list.sort((o1, o2) -> {
                int a = output[o1];
                int b = output[o2];
                return b-a;
            });
            queue.addAll(list);

        }
        return res;

    }


    public static void main(String[] args) {
        int[][] arr=new int[][]{
                {4,8},{3,6},{6,8},{7,6},{4,2},{4,1},{4,7},{3,7},{5,2},{5,9},{3,4},{6,9},{5,7}};
        System.out.println(new match195().minNumberOfSemesters(9,arr,2));
    }
}
