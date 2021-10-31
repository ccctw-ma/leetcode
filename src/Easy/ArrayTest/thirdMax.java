package Easy.ArrayTest;



/**
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 * 示例 1:
 *
 * 输入: [3, 2, 1]
 *
 * 输出: 1
 *
 * 解释: 第三大的数是 1.
 * 示例 2:
 *
 * 输入: [1, 2]
 *
 * 输出: 2
 *
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 *
 * 输入: [2, 2, 3, 1]
 *
 * 输出: 1
 *
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 **/

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/29 414. 第三大的数 */


public class thirdMax {


    //时间复杂度为o(nlg3)==o(n)
    public int thirdMax(int[] nums) {
        Queue<Integer> queue=new PriorityQueue<>();
        for (int i:nums){
            if(queue.size()<3&&!queue.contains(i)){
                queue.offer(i);
            }else if(!queue.contains(i)){
                queue.offer(i);
                queue.remove();
            }
        }
        if(queue.size()==3){
            return queue.remove();
        }else {
            while (queue.size()!=1){
                queue.remove();
            }
            return queue.remove();
        }
    }



    //可以使用long的最小值来进行比较来排除极端情况
    public int thirdMa1(int[] nums) {
        long first=Long.MIN_VALUE,second=Long.MIN_VALUE,third=Long.MIN_VALUE;
        for(long num:nums){
            if(num>first){
                third=second;
                second=first;
                first=num;
            }else if(num>second&&num<first){
                third=second;
                second=num;
            }else if(num>third&&num<second){
                third=num;
            }
        }
        return (third==Long.MIN_VALUE||third==second)?(int)first:(int)third;

    }


    //使用treeSet类似优先队列
    public int thirdMax3(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> {
            if(o2.equals(o1)) {
                return 0;
            }
            return (o2 > o1)  ? 1 : -1 ;
        });
        for(int i = 0; i < nums.length; ++i) {
            treeSet.add(nums[i]);
            if(treeSet.size() > 3) {
                treeSet.pollLast();
            }
            // System.out.println( treeSet.first() + " " + treeSet.last() + " " + treeSet.size());

        }

        return treeSet.size() >= 3 ? treeSet.last() : treeSet.first();


    }

    public static void main(String[] args) {
        int[] array=new int[]{1,2,3,3,4,5,6,7};
        System.out.println(new thirdMax().thirdMax(array));
    }
}
