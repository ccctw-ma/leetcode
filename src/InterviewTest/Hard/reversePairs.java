package InterviewTest.Hard;


/**
 * 面试题51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000*/

import java.util.TreeMap;

/**
 * @author 马世臣
 * @// TODO: 2020/4/24
 * */


public class reversePairs {




    public int reversePairs(int[] nums) {
        int[] temp=new int[nums.length];
        return sort(nums,0,nums.length-1,temp);
    }


    /**
     *
     * @param arr array Of Operations
     * @param left the left index that we need to operate of this array
     * @param right the right index that we need to operate of this array
     * @param temp an array that ues temporarily
     * @return the answer we need
     */
    private int sort(int[] arr,int left,int right,int[] temp){
        int count=0;
        if(left<right){
            int mid=left+(right-left)/2;
            int leftCount=sort(arr,left,mid,temp);
            int rightCount=sort(arr,mid+1,right,temp);
            int mergeCount=0;
            if(arr[mid]>arr[mid+1])
                mergeCount=merge(arr,left,mid,right,temp);
            count=leftCount+rightCount+mergeCount;
        }
        return count;
    }

    private int merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i=left,j=mid+1,t=0,count=0;
        while (i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t++]=arr[i++];
            }else {
                count+=mid-i+1;
                temp[t++]=arr[j++];
            }
        }
        while (i<=mid)  temp[t++]=arr[i++];
        while (j<=right)    temp[t++]=arr[j++];
        System.arraycopy(temp,0,arr,left,right-left+1);
        return count;
    }





    //超时了
    public int reversePairs2(int[] nums) {
        if(nums.length<2) return 0;
        int sum=0,len=nums.length;
        TreeMap<Integer,int[]> map=new TreeMap<>((o1, o2) -> o2-o1);
        //int[] 0为该数字的数量 1为比该数字小的数量
        map.put(nums[len-1],new int[]{1,0});
        for (int i=len-2;i>=0;i--){
            int num=nums[i];
            for (Integer integer:map.keySet()){
                if(num>integer){
                    int a=map.get(integer)[0]+map.get(integer)[1];
                    if(!map.containsKey(num)){
                        map.put(num,new int[]{1,a});
                    }
                    sum+=a;
                    break;
                }else if(num==integer){
                    int[] a=map.get(integer);
                    a[0]++;
                    map.put(integer,a);
                }else {
                    int[] a=map.get(integer);
                    a[1]++;
                    map.put(integer,a);
                }
            }
            if(!map.containsKey(num)){
                map.put(num,new int[]{1,0});
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new reversePairs().reversePairs(new int[]{9,7,5,6,4,4,6,7,8}));
//        int[] arr=new int[]{9,7,5,6,4,4,6,7,8};
//        int[] temp=new int[arr.length];
//        System.arraycopy(arr,0,temp,0,arr.length);
//        System.out.println(Arrays.toString(temp));
    }
}
