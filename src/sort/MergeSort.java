package sort;

import java.util.Arrays;

public class MergeSort {



    private static void sort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid=left+(right-left)/2;
            sort(arr,left,mid,temp);
            sort(arr,mid+1,right,temp);

            //如果arr[mid]<=arr[mid+1]就不需要在进行merge操作了此时已经是一个有序子数组了
            if(arr[mid]>arr[mid+1]){
                merge(arr,left,mid,right,temp);
            }
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i=left,j=mid+1,t=0;
        while (i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t++]=arr[i++];
            }else {
                temp[t++]=arr[j++];
            }
        }
        while (i<=mid){
            temp[t++]=arr[i++];
        }
        while (j<=right){
            temp[t++]=arr[j++];
        }
        t=0;
        for (int k=left;k<=right;k++){
            arr[k]=temp[t++];
        }
    }


    public static void main(String[] args) {
        int[] arr=new int[]{1, 2, 2, 6, 4, 8, 8, 9};
        int[] temp=new int[arr.length];
        sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
}
