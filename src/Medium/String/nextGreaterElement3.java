package Medium.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个32位正整数 n，
 * 你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。
 * 如果不存在这样的32位整数，则返回-1。*/

/**@ TODO: 2020/1/10
 * @author 马世臣
 * 556. 下一个更大元素 III*/
public class nextGreaterElement3 {
    public static int nextGreaterElement(int n) {
        List<Integer> list=new ArrayList<>();
        while (n!=0){
            list.add(n%10);
            n=n/10;
        }
        int[] array=new int[list.size()];
        int i=0;
        for(Integer integer:list){
            array[list.size()-i-1]=integer;
            i++;
        }
        int index=0,flag=0;
        for (int j=array.length-1;j>0;j--){
            if(array[j]<=array[j-1]){
                continue;
            }else {
                index=j-1;
                flag=1;
                break;
            }
        }
        if(flag!=0){
            int[] arr=new int[array.length-index-1];
            for(int k=index+1,l=0;k<array.length;k++){
                arr[l++]=array[k];
            }
            Arrays.sort(arr);
            for(int k=0;k<arr.length;k++){
                if(arr[k]>array[index]){
                    int temp=array[index];
                    array[index]=arr[k];
                    arr[k]=temp;
                    break;
                }
            }
            Arrays.sort(arr);
            for(int k=index+1,l=0;k<array.length;k++){
                array[k]=arr[l++];
            }
            long result=0;
            for(Integer integer:array){
                result=result*10+integer;
            }
            if(result>Integer.MAX_VALUE){
                return -1;
            }else {
                return (int) result;
            }

        }

        return -1;
    }

    /**
     * public int nextGreaterElement(int n) {
     *         char[] a = ("" + n).toCharArray();
     *         int i = a.length - 2;
     *         while (i >= 0 && a[i + 1] <= a[i]) {
     *             i--;
     *         }
     *         if (i < 0)
     *             return -1;
     *         int j = a.length - 1;
     *         while (j >= 0 && a[j] <= a[i]) {
     *             j--;
     *         }
     *         swap(a, i, j);
     *         reverse(a, i + 1);
     *         try {
     *             return Integer.parseInt(new String(a));
     *         } catch (Exception e) {
     *             return -1;
     *         }
     *     }
     *     private void reverse(char[] a, int start) {
     *         int i = start, j = a.length - 1;
     *         while (i < j) {
     *             swap(a, i, j);
     *             i++;
     *             j--;
     *         }
     *     }
     *     private void swap(char[] a, int i, int j) {
     *         char temp = a[i];
     *         a[i] = a[j];
     *         a[j] = temp;
     *     }
     *
     */
    public static void main(String[] args) {
        System.out.println(nextGreaterElement(1999999999));
        System.out.println(Integer.MAX_VALUE);
    }
}
