package sort;

public class HeapSort {

    private void heapSort(int[] arr){
        int n=arr.length;
        for (int i=n/2-1;i>=0;i--){
            heapChange(arr,i,n);
        }
        for (int i=n-1;i>=0;i--){
            swap(arr,0,i);
            heapChange(arr,0,i);
        }
    }

    private void heapChange(int[] arr,int index,int size){
        int maxIndex,temp=index;
        while ((index*2+1)<size){
            int left=arr[index*2+1];
            if(index*2+2<size) {
                int right=arr[index*2+2];
                maxIndex=(left<right?(index*2+2):(index*2+1));
            } else {
                maxIndex=index*2+1;
            }
            if(arr[index]<arr[maxIndex]){
                swap(arr,index,maxIndex);
                index=maxIndex;
            }
            if(temp==index){
                break;
            }else temp=index;
        }
    }

    private void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
