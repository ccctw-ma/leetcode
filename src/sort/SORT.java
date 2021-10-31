package sort;


import java.io.IOException;
import java.util.*;

/**
 *
 * @implNote  this class is just a practise for better
 * understand the Sort Algorithm
 * @author 马世臣
 * @// TODO: 2020/9/11  */


public class SORT {


    /**
     * @apiNote InsertSort*/
    public static void InsertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int rt = arr[i];
            for(int j = i - 1; j >= 0; j--){
                if(rt < arr[j]){
                    arr[j + 1] = arr[j];
                    arr[j] = rt;
                }else{
                    break;
                }
            }
        }
    }

    private static void BinaryInsertSort(int[] arr){
        for (int i=1;i<arr.length;i++){
            int temp=arr[i];
            if(temp>arr[i-1]) continue;
            int k=Arrays.binarySearch(arr,0,i,temp);
            k = k<0? -k-1: k;
            for (int j=i;j>=k+1;j--){
                arr[j]=arr[j-1];
            }
            arr[k]=temp;
        }
    }

    /**
     * @apiNote ShellSort*/
    public static void ShellSort(int[] arr){
        int di=arr.length>>>1;
        while (di>=1){
            for (int i=0;i<arr.length-di;i++){
                for (int j=i+di;j<arr.length;j+=di){
                    if(arr[j]<arr[j-di]){
                        int temp=arr[j];
                        for (int k=j-di;k>=0;k-=di){
                            if(arr[k]>temp){
                                arr[k+di]=arr[k];
                                arr[k]=temp;
                            }else break;
                        }
                    }
                }
            }
            di>>>=1;
        }
    }


    /**
     * @apiNote BubbleSor
     * This algorithm is easy to understand,but not efficient o(n2)*/
    public static void BubbleSort(int[] arr){
        int n=arr.length;
        boolean flag;
        for (int i=0;i<n-1;i++){
            flag=false;
            for (int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=true;
                }
            }
            if (!flag) return;
        }
    }

    /**
     * @apiNote QuickSort
     * Quite lovely,this is the algorithm's charm*/
    public static void QuickSort(int[] arr,int left,int right){
        if (left<right){
            int pivot=quickSort(arr,left,right);
            QuickSort(arr,left,pivot-1);
            QuickSort(arr,pivot+1,right);
        }
    }

    public static int quickSort(int[] arr,int left,int right){
        int temp=arr[left];
        while (left<right){
            while (left<right&&arr[right]>=temp) right--;
            arr[left]=arr[right];
            while (left<right&&arr[left]<=temp) left++;
            arr[right]=arr[left];
        }
        arr[left]=temp;
        return left;
    }


    public static  void SelectSort(int[] arr,int n){
        for (int i=0;i<n-1;i++){
            int min=i;
            for (int j=i+1;j<n;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            swap(arr,i,min);
        }
    }

    /**
     * @apiNote HeapSort
     * This algorithm is base on The Complete Binary Tree*/
    public static void HeapSort(int[] arr){
        int n=arr.length;
        for (int i=n/2-1;i>=0;i--){
            HeapChange(arr,i,n);
        }
        for (int i=n-1;i>=0;i--){
            swap(arr,0,i);
            HeapChange(arr,0,i);
        }
    }

    /**This method is used to keep the heap array orderly, a sequence that we need*/
    public static void HeapChange(int[] arr, int index, int n){
        int temp=index;
        while ( index*2 + 1 < n ){
            int maxIndex=index*2+1;
            if(index*2+2<n)
                maxIndex=arr[index*2+1]>arr[index*2+2] ? index*2 + 1 : index*2 + 2;
            if(arr[index]<arr[maxIndex]){
                swap(arr,index,maxIndex);
                index = maxIndex;
            }
            if(index==temp) break;
            temp=index;
        }
    }


    /**Basic swap operation*/
    public static void swap(int[] arr,int x,int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }


    /**
     * @apiNote MergeSort*/

    public static void MergeSort(int[] arr,int[] temp,int left,int right){
        if(left<right){
            int mid=left+(right-left)/2;
            MergeSort(arr, temp,left, mid);
            MergeSort(arr, temp,mid+1,right);
            if(arr[mid]>arr[mid+1])
                merge(arr,temp,left,mid,right);
        }
    }

    private static void merge(int[] arr,int[] temp,int left,int mid,int right){
        int i=left,j=mid+1,t=0;
        while (i<=mid&&j<=right) temp[t++] = ( arr[i] <= arr[j] ? arr[i++] : arr[j++] );
        while (i<=mid) temp[t++]=arr[i++];
        while (j<=right) temp[t++]=arr[j++];
        for (int k=left,c=0;k<=right;k++) arr[k]=temp[c++];
    }



    /**
     * @apiNote BucketSort
     * If the data set's range has limitation,this method can reach o(n),quite amazing*/
    public static void BucketSort(int[] arr){
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for (int i:arr){
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        int[] bucket=new int[max-min+1];
        for (int i:arr){
            bucket[i-min]++;
        }
        int index=0;
        for (int i=0;i<bucket.length;){
            if(bucket[i]>0){
                arr[index++]=i+min;
                bucket[i]--;
            }else {
                i++;
            }
        }
    }

    /**
     * @apiNote BaseSort*/
    public static void BaseSort(int[] arr){
        List<Deque<Integer>> dequeList=new ArrayList<>();
        for (int i=0;i<10;i++) dequeList.add(new ArrayDeque<>());
        int mod=10,div=1;
        for (int i=0;i<4;i++){
            if(i!=0){
                mod*=10;
                div*=10;
            }
            for (int num:arr){
                int temp=num;
                num=(num%mod)/div;
                dequeList.get(num).add(temp);
            }
            int index=0;
            for (Deque<Integer> deque:dequeList){
                while (!deque.isEmpty()){
                    arr[index++]=deque.poll();
                }
            }
        }
    }


    public static void SanSeSort(int[] arr){
        int i=0,j=0,k=arr.length-1;
        while (j<k){
            switch (arr[j]){
                case 0:{
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                    i++;
                    j++;
                    break;
                }
                case 1:{
                    j++;
                    break;
                }
                case 2:{
                    int temp=arr[j];
                    arr[j]=arr[k];
                    arr[k]=temp;
                    k--;
                }

            }
        }

    }


    public static boolean isOrder(int[] arr){
        for (int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]) return false;
        }
        return true;
    }

    public static double legendre(double x,int n){
        double a = 1;
        double b = x;
        double c = 0;
        for (int i = 2; i <= n; i++) {
            System.out.println((2 - 1.0 / n) * x * b + "  " + (1 - 1.0 / n) * a);
            c = (2 - (1.0 / n)) * x * b - (1 - 1.0 / n) * a;
            a = b;
            b = c;
        }
        return c;
    }


    public static void main(String[] args) throws IOException {
        //test data

        int MAX_NUMS = 2048;
        int[] arr = new int[MAX_NUMS];
        int[] temp = new int[MAX_NUMS];
        for (int i=0;i<MAX_NUMS;i++){
            arr[i] = (int) (Math.random() * MAX_NUMS);
        }
//        System.out.println(Arrays.toString(arr));
        long time = System.currentTimeMillis();
//        SORT.BubbleSort(arr);
//        SORT.InsertSort(arr);
//        SORT.HeapSort(arr);
//        SORT.QuickSort(arr,0,arr.length-1);
        SORT.MergeSort(arr,temp,0,arr.length-1);
//        SORT.BucketSort(arr);
//        SORT.BaseSort(arr);
//        SORT.BinaryInsertSort(arr);
//        SORT.SelectSort(arr,arr.length);

        System.out.println("Time cost:   " + (System.currentTimeMillis() - time) + " In order : " + isOrder(arr));
//        System.out.println(1.0/10);
//        System.out.println(SORT.legendre(2,10));

    }
}
