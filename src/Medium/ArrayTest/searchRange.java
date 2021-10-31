package Medium.ArrayTest;

public class searchRange {

    public int[] searchRange(int[] nums, int target) {
        int l=searchLeft(nums,target);
        int r=searchRight(nums,target);
        if(l>=0&&r<nums.length&&nums[l]==target&&nums[r]==target){
            return new int[]{l,r};
        }
        return new int[]{-1, -1};
    }

    private int searchLeft(int[] nums,int target){
        int len=nums.length;
        int l=0,r=len-1,index=-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            //逼近
            if(nums[mid]>=target){
                r=mid-1;
                index=mid;
            }else {
                l=mid+1;
            }
        }
        return index;
    }

    private int searchRight(int[] nums,int target){
        int len=nums.length;
        int l=0,r=len-1,index=-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            //逼近
            if(nums[mid]<=target){
                l=mid+1;
                index=mid;
            }else {
                r=mid-1;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        System.out.println(new searchRange().searchRight(new int[]{1,2,5},5));
    }
}
