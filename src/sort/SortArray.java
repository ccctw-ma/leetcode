package sort;


/*  *
 * 912. 排序数组
 * 给你一个整数数组 nums，将该数组升序排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2020/3/31  */

public class SortArray {

    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high){
        if (low<high){
            int par=sort(nums,low,high);
            quickSort(nums,low,par-1);
            quickSort(nums,par+1,high);
        }
    }

    public int sort(int[] nums,int low,int high){
        int par=nums[low];
        while (low<high){
            while (low<high&&nums[high]>par) high--;
            nums[low]=nums[high];
            while (low<high&&nums[low]<=par) low++;
            nums[high]=nums[low];
        }
        nums[high]=par;
        return high;
    }

    /**
     选择排序 每一趟在n-i+1（i=1,2,3…,n-1）个记录中选取关键字最小的记录与第i个记录交换，
     并作为有序序列中的第i个记录。
     **/
    void selectSort(int[] arr){
        int min;
        for(int i = 0;i<arr.length;i++){
            min = i;
            for(int j = i;j<arr.length;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     *
     * 插入排序：数列前面部分看为有序，依次将后面的无序数列元素插入到前面的有序数列中，
     * 初始状态有序数列仅有一个元素，即首元素。在将无序数列元素插入有序数列的过程中，
     * 采用了逆序遍历有序数列，相较于顺序遍历会稍显繁琐，但当数列本身已近排序状态效率会更高。
     *
     * 时间复杂度：O(N2) 　　稳定性：稳定
     * @param arr
     */
    public void insertSort(int[] arr){
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
    /**
     * 希尔排序 - 插入排序的改进版。为了减少数据的移动次数，
     * 在初始序列较大时取较大的步长，通常取序列长度的一半，
     * 此时只有两个元素比较，交换一次；之后步长依次减半直至步长为1，
     * 即为插入排序，由于此时序列已接近有序，故插入元素时数据移动的次数会相对较少，效率得到了提高。
     *
     * 时间复杂度：通常认为是O(N3/2) ，未验证　　稳定性：不稳定
     * @param arr
     */
    void shellSort(int[] arr){
        int d = arr.length >> 1;
        while(d >= 1){
            for(int i = d; i < arr.length; i++){
                int rt = arr[i];
                for(int j = i - d; j >= 0; j -= d){
                    if(rt < arr[j]){
                        arr[j + d] = arr[j];
                        arr[j] = rt;
                    }else break;
                }
            }
            d >>= 1;
        }
    }
    /**
     * 桶排序 - 实现线性排序，但当元素间值得大小有较大差距时会带来内存空间的较大浪费。
     * 首先，找出待排序列中得最大元素max，申请内存大小为max + 1的桶（数组）并初始化为0；
     * 然后，遍历排序数列，并依次将每个元素作为下标的桶元素值自增1；
     * 最后，遍历桶元素，并依次将值非0的元素下标值载入排序数列（桶元素>1表明有值大小相等的元素，
     * 此时依次将他们载入排序数列），遍历完成，排序数列便为有序数列。
     *
     * 时间复杂度：O(x*N) 　　稳定性：稳定
     * @param arr
     */
    void bucketSort(int[] arr){
        int[] bk = new int[50000 * 2 + 1];
        for(int i = 0; i < arr.length; i++){
            bk[arr[i] + 50000] += 1;
        }
        int ar = 0;
        for(int i = 0; i < bk.length; i++){
            for(int j = bk[i]; j > 0; j--){
                arr[ar++] = i - 50000;
            }
        }
    }

    /**
     * 基数排序 - 桶排序的改进版，桶的大小固定为10，减少了内存空间的开销。
     * 首先，找出待排序列中得最大元素max，并依次按max的低位到高位对所有元素排序；
     * 桶元素10个元素的大小即为待排序数列元素对应数值为相等元素的个数，即每次遍历待排序数列，
     * 桶将其按对应数值位大小分为了10个层级，桶内元素值得和为待排序数列元素个数。
     * @param arr
     */
    void countSort(int[] arr){
        int[] bk = new int[19];
        int max = Integer.MIN_VALUE;
        for (int item : arr) {
            if (max < Math.abs(item)) max = item;
        }
        if(max < 0) max = -max;
        max = Integer.toString(max).length();
        int [][] bd = new int[19][arr.length];
        for(int k = 0; k < max; k++) {
            for (int item : arr) {
                int value = (int) (item / (Math.pow(10, k)) % 10);
                bd[value + 9][bk[value + 9]++] = item;
            }
            int fl = 0;
            for(int l = 0; l < 19; l++){
                if(bk[l] != 0){
                    for(int s = 0; s < bk[l]; s++){
                        arr[fl++] = bd[l][s];
                    }
                }
            }
            bk = new int[19];
            fl = 0;
        }
    }

    /**
     * 归并排序 - 采用了分治和递归的思想，递归&分治-排序整个数列如同排序两个有序数列，
     * 依次执行这个过程直至排序末端的两个元素，
     * 再依次向上层输送排序好的两个子列进行排序直至整个数列有序（类比二叉树的思想，from down to up）。
     *
     * 时间复杂度：O(NlogN) 　　稳定性：稳定
     * @param arr
     */
    private void mergeSortInOrder(int[] arr, int bgn, int mid, int end){
        int l = bgn, m = mid +1, e = end;
        int[] arrs = new int[end - bgn + 1];
        int k = 0;
        while(l <= mid && m <= e){
            if(arr[l] < arr[m]){
                arrs[k++] = arr[l++];
            }else{
                arrs[k++] = arr[m++];
            }
        }
        while(l <= mid){
            arrs[k++] = arr[l++];
        }
        while(m <= e){
            arrs[k++] = arr[m++];
        }
        for(int i = 0; i < arrs.length; i++){
            arr[i + bgn] = arrs[i];
        }
    }


    void mergeSort(int[] arr, int bgn, int end) {
        if(bgn >= end){
            return;
        }
        int mid = (bgn + end) >> 1;
        mergeSort(arr,bgn,mid);
        mergeSort(arr,mid + 1, end);
        mergeSortInOrder(arr,bgn,mid,end);
    }

    /**
     * 堆排序 - 堆排序的思想借助于二叉堆中的最大堆得以实现。首先，将待排序数列抽象为二叉树，并构造出最大堆；然后，依次将最大元素（即根节点元素）与待排序数列的最后一个元素交换（即二叉树最深层最右边的叶子结点元素）；
     * 每次遍历，刷新最后一个元素的位置（自减1），直至其与首元素相交，即完成排序。
     *
     * 时间复杂度：O(NlogN) 　　稳定性：不稳定
     *
     * @param nums
     */
    void heapSort(int[] nums) {
        int size = nums.length;
        for (int i = size/2-1; i >=0; i--) {
            adjust(nums, size, i);
        }
        for (int i = size - 1; i >= 1; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjust(nums, i, 0);
        }
    }
    private void adjust(int[] nums, int len, int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int maxIndex = index;
        if (l<len&&nums[l]>nums[maxIndex])maxIndex = l;
        if (r<len&&nums[r]>nums[maxIndex])maxIndex = r;
        if (maxIndex != index) {
            int temp = nums[maxIndex];
            nums[maxIndex] = nums[index];
            nums[index] = temp;
            adjust(nums, len, maxIndex);
        }
    }

    //计数排序，用空间换时间
    public int[] sortArray2(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i]) min = nums[i];
            else if (max < nums[i]) max = nums[i];
        }
        int range = max - min + 1;

        int[] counts = new int[range];
        for (int num : nums) {
            counts[num - min]++;
        }
        for (int i = 0, j = 0; i < range; i++) {
            while (counts[i]-- > 0) {
                nums[j++] = i + min;
            }
        }
        return nums;
    }


    private int dfsBinarySearch(int[] a,int target,int left,int right){
        if(left<=right){
            int mid=left+(right-left)/2;
            if(a[mid]==target){
                return mid;
            }else if(a[mid]>target){
                return dfsBinarySearch(a,target,left,mid-1);
            }else {
                return dfsBinarySearch(a,target,mid+1,right);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new SortArray().SortArray(new int[]{3, 2, 5, 1, 5, 6, 7, 8, 9, 3, 4})));
        int[] a = {1,3,5,7,9,11,12};
//        System.out.println(new SortArray().dfsBinarySearch(a,11,0,a.length-1));


        Arrays.sort(a);
        Map<Integer,Integer> map=new HashMap<>();
        System.out.println(Arrays.stream(a).max().getAsInt());
    }

}
