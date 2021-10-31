package Easy.HeapTest;


/**
 *
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */

import java.util.PriorityQueue;

/**@author 马世臣
 * @// TODO: 2020/1/13  1046. 最后一块石头的重量*/
public class lastStoneWeight {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> p=new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int i:stones){
            p.offer(i);
        }
        while (p.size()>=2){
            int s1=p.poll();
            int s2=p.poll();
            if(s2<s1){
                p.offer(s1-s2);
            }
        }
        if(p.isEmpty()){
            return 0;
        }else {
            return p.peek();
        }
    }

    /**
     *
     * 用递归进行实现很巧妙
     * public int lastStoneWeight(int[] stones) {
     *         if (stones.length == 2) {
     *             return Math.abs(stones[0] - stones[1]);
     *         }
     *         if (stones.length == 1) {
     *             return stones[0];
     *         }
     *         Arrays.sort(stones);
     *         if (stones[stones.length - 3] == 0) {
     *             return stones[stones.length - 1] - stones[stones.length - 2];
     *         }
     *         stones[stones.length - 1] = stones[stones.length - 1] - stones[stones.length - 2];
     *         stones[stones.length - 2] = 0;
     *         return lastStoneWeight(stones);
     *     }*/

    public static void main(String[] args) {
        int[] stones=new int[]{1,2,3,4,5};
        System.out.println(lastStoneWeight(stones));
    }
}
