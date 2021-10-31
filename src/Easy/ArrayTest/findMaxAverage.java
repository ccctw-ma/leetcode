package Easy.ArrayTest;



/**
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例 1:
 *
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *  
 *
 * 注意:
 *
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/31  643. 子数组最大平均数 I*/

public class findMaxAverage {


    public double findMaxAverage(int[] nums, int k) {
        int pre=nums[0],sum=0;
        for (int i=0;i<k;i++){
            sum+=nums[i];
        }
        double max=  sum*1.0 / k;
        for (int i=k;i<nums.length;i++){
            sum+=nums[i];
            sum-=pre;
            pre=nums[i-k+1];
            double temp= sum*1.0 / k;//可以先求出最大元素和，最后再求平均值节省运算时间
            if(temp>max){
                max=temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{7,4,5,8,8,3,9,8,7,6};
        System.out.println(new findMaxAverage().findMaxAverage(nums,7));
    }
}
