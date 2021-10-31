package InterviewTest.Medium;



/*
* 面试题56 - I. 数组中数字出现的次数
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。



示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]


限制：

2 <= nums <= 10000*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/28
 * */



public class singleNumbers {


    public int[] singleNumbers(int[] nums) {
        int len=nums.length;
        if(len==2) return nums;
        int index=0,sum=0;
        for (int i:nums) sum=(sum^i);
        while (index<32){
            if((sum&1)==1){
                break;
            }else {
                sum>>=1;
                index++;
            }
        }
        int a=0,b=0,temp;
        for (int i:nums){
            temp=i>>index;
            if((temp&1)==1){
                a^=i;
            }else {
                b^=i;
            }
        }
        return new int[]{a,b};
    }



    //牢记，x&-x得到x第一1所在位置，例7&-7=1；
//    相同的数异或为0，不同的异或为1。0和任何数异或等于这个数本身。
//
//所以，数组里面所有数异或 = 目标两个数异或 。 由于这两个数不同，所以异或结果必然不为0。
//
//假设数组异或的二进制结果为10010，那么说明这两个数从右向左数第2位是不同的
//
//那么可以根据数组里面所有数的第二位为0或者1将数组划分为2个。
//
//这样做可以将目标数必然分散在不同的数组中，而且相同的数必然落在同一个数组中。
//
//这两个数组里面的数各自进行异或，得到的结果就是答案
    public int[] singleNumbers2(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        int diff = xor & (-xor);
        int x = 0;
        for (int num : nums) {
            if((diff&num)!=0)
                x ^= num;
        }
        return new int[]{x,xor^x};
    }


    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new singleNumbers().singleNumbers(new int[]{12, 2, 10, 4, 12, 4, 3, 3})));
        System.out.println(16&-16);
    }
}
