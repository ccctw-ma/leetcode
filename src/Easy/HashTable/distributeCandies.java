package Easy.HashTable;


/**
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 *
 * 示例 1:
 *
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * 示例 2 :
 *
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * 注意:
 *
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 **/

import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/2 575. 分糖果 */

public class distributeCandies {


    public int distributeCandies(int[] candies) {
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int i:candies){
            if(i<min) min=i;
            if(i>max) max=i;
        }
        int[] buckets=new int[max-min+1];
        for (int i:candies){
            buckets[i-min]++;
        }
        int count=0,need=candies.length/2;
        for (int i:buckets){
            if(i>0) count++;
        }
        return count>need?need:count;

    }

    //return (int)Math.min(Arrays.stream(candies).distinct().count(),candies.length/2);
    public static void main(String[] args) {
        int[] candies=new int[]{1,1,2,2,3,3,5,6,7,8,9};
        System.out.println(Arrays.stream(candies).distinct());
    }
}
