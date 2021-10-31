package Easy.BinarySearchTest;


/*
* 剑指 Offer 11. 旋转数组的最小数字
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0*/


/**
 * @author 马世臣
 * @// TODO: 2020/7/22  */


public class minArray {

    public int minArray(int[] numbers) {
        int n=numbers.length;
        int a=numbers[0];
        int b=numbers[n-1];
        if(a<b||n==1) return a;
        int left=0;
        int right=n-1;
        while (left<right&&numbers[left]==numbers[left+1]) left++;
        while (left<right&&numbers[right]==numbers[right-1]) right--;
        while (left<right){
            int mid=left+(right-left)/2;
            int num=numbers[mid];
            int next=numbers[mid+1];
            if(num>next) return next;
            if(mid>0&&num<numbers[mid-1]) return num;
            if(num>=a&&num>b){
                left=mid+1;
            }else if(num<=a&&num<=b){
                right=mid-1;
            }
        }
        return numbers[left];
    }

    //关键是去重
    public int minArray2(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else r--;
        }
        return numbers[l];
    }


    public static void main(String[] args) {
        System.out.println(new minArray().minArray(new int[]{3,3,3,3,3,1,3,3,3}));
    }
}
