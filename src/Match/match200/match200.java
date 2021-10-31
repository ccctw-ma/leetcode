package Match.match200;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class match200 {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count=0;
        int n=arr.length;
        for(int i=0;i<n-2;i++){
            for (int j=i+1;j<n-1;j++){
                for (int k=j+1;k<n;k++){
                    if(Math.abs(arr[i]-arr[j])<=a&&Math.abs(arr[j]-arr[k])<=b&&Math.abs(arr[i]-arr[k])<=c){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int getWinner(int[] arr, int k) {
        int n=arr.length;
        int max=Integer.MIN_VALUE;
        Queue<Integer> queue=new LinkedList<>();
        for (int item : arr) {
            queue.add(item);
            max = Math.max(max, item);
        }
        if(k>=n) return max;
        int temp=queue.poll();
        int count=0;
        while (true){
            int p=queue.poll();
            if(temp>p){
                queue.add(p);
                count++;
                if(count==k) return temp;
            }else {
                queue.add(temp);
                temp=p;
                count=1;
                if(count==k) return temp;
            }
        }
    }

    public int minSwaps(int[][] grid) {
        int n=grid.length;
        if(n<=2&&grid[0][n-1]==0) return 0;
        int[] arr=new int[n];
        int max=0;
        for (int i=0;i<n;i++){
            int count=0;
            int index=n-1;
            while (index>=0&&grid[i][index]==0){
                count++;
                index--;
            }
            arr[i]=count;
            max=Math.max(count,max);
        }
        int count=0;
        for (int i=0;i<n-1;i++){
            int tar=n-i-1;
            if(arr[i]>=tar) continue;
            if(arr[i]<tar){
                int j=i;
                while (j<n){
                    if(arr[j]>=tar){
                        count+=j-i;
                        int temp=arr[j];
                        for (int k=j;k>i;k--){
                            arr[k]=arr[k-1];
                        }
                        arr[i]=temp;
                        break;
                    }else {
                        j++;
                    }
                }
                if(j==n) return -1;

            }
        }

        return count;
    }

    public int maxSum(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        long res1=0;
        long res2=0;
        int l=0;
        int r=0;
        while (l<m||r<n){
            if(l<m&&r<n){
                if(nums1[l]<nums2[r]){
                    res1+=nums1[l++];
                }else if(nums1[l]>nums2[r]){
                    res2+=nums2[r++];
                }else {
                    res1=Math.max(res1+nums1[l++],res2+nums2[r++]);
                    res2=res1;
                }
            }else {
                if(l<m){
                    res1+=nums1[l++];
                }else {
                    res2+=nums2[r++];
                }
            }
        }
        return (int) (Math.max(res1,res2)%1000000007);
    }

    class Node{
        int value;
        List<Node> next;
        public Node(int value,List<Node> next){
            this.value=value;
            this.next=next;
        }
    }

    public static void main(String[] args) {
        System.out.println(new match200().minSwaps(new int[][]{{0,0,1},{1,1,0},{1,0,0}}));
    }
}
