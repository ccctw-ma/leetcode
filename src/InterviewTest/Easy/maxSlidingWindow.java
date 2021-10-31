package InterviewTest.Easy;

import java.util.Arrays;

public class maxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        if(k==1) return nums;
        int[] res=new int[n-k+1];
        int maxIndex=0,max=Integer.MIN_VALUE;
        int index=0;
        for (int i=0;i<k;i++){
            if(nums[i]>max){
                max=nums[i];
                maxIndex=i;
            }
        }
        res[index++]=max;
        for (int i=k;i<n;i++){
            if(i-maxIndex<k){
                if(nums[i]>nums[maxIndex]){
                    maxIndex=i;
                }
                res[index++]=nums[maxIndex];
            }else {
                max=Integer.MIN_VALUE;
                for (int j=maxIndex+1;j<=i;j++){
                    if(nums[j]>max){
                        maxIndex=j;
                        max=nums[j];
                    }
                }
                res[index++]=max;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new maxSlidingWindow().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
