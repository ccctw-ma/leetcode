package Medium.ArrayTest;


/*
* 287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:

输入: [1,3,4,2,2]
输出: 2
示例 2:

输入: [3,1,3,4,2]
输出: 3
说明：

不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/14  */


public class findDuplicate {


    public int findDuplicate(int[] nums) {
        // 基本方法，别的方法想不出来时可以使用
        // Set<Integer> seen = new HashSet<Integer>();
        // for (int num : nums) {
        //     if (seen.contains(num)) {
        //         return num;
        //     }
        //     seen.add(num);
        // }

        // return -1;


        //很巧妙的使用了二分法的思想，利用数组的特性对数据进行处理
        // int left=1,right=nums.length;
        // while(left<right){
        //     int mid=left+(right-left)/2;
        //     int count=0;
        //     for(int i:nums){
        //         if(i<=mid){
        //             count++;
        //         }
        //     }
        //     if(count<=mid){
        //         left=mid+1;
        //     }else{
        //         right=mid;
        //     }
        // }
        // return left;

        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
//            System.out.println(tortoise+"---"+hare);
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }


    /**
     * @apiNote  method4
     * 使用字节记录答案，很巧妙
     * int res = 0;
     *     int n = nums.length;
     *     //统计每一列 1 的个数
     *     for (int i = 0; i < 32; i++) {
     *         int a = 0;
     *         int b = 0;
     *         int mask = (1 << i);
     *         for (int j = 0; j < n; j++) {
     *             //统计原数组当前列 1 的个数
     *             if ((nums[j] & mask) > 0) {
     *                 a++;
     *             }
     *             //统计 1 到 n 序列中当前列 1 的个数
     *             if ((j & mask) > 0) {
     *                 b++;
     *             }
     *         }
     *         if (a > b) {
     *             res = res | mask;
     *         }
     *     }
     *     return res;
     **/

    public static void main(String[] args) {
        System.out.println(new findDuplicate().findDuplicate(new int[]{2,5,9,6,9,3,8,9,7,1}));
    }
}
