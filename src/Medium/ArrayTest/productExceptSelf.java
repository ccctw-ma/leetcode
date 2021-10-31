package Medium.ArrayTest;

public class productExceptSelf {


    public int[] productExceptSelf(int[] nums) {
        int sum=1,count=0,index=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]==0){
                count++;
                index=i;
                continue;
            }
            sum*=nums[i];
        }
        if(count>1) return new int[nums.length];
        if(count==1){
            int[] res=new int[nums.length];
            res[index]=sum;
            return res;
        }
        for (int i=0;i<nums.length;i++){
            int a=sum<0?-1:1;
            int b=nums[i]<0?-1:1;
            int ans=division(Math.abs(sum),Math.abs(nums[i]));
            nums[i]=a*b*ans;
        }
        return nums;
    }

    private int division(int a,int b){
        int res = 0;
        while (a>=b){
            a-=b;
            res++;
        }
        return res;
    }


    //该数据不就是它左边的乘积和乘以他右边的乘积，遍历两遍即可
    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }



    public static void main(String[] args) {
        double a=Math.log(10);
        double b=Math.log(2);
        a=a-b;
        System.out.println((int) Math.exp(Math.log(-10)-Math.log(2)));
    }
}
