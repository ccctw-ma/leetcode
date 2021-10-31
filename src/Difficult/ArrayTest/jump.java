package Difficult.ArrayTest;


/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/4
 * */


public class jump {

    public int jump(int[] nums) {
        int len=nums.length;
        if(len<=1) return 0;
        int count=1,max=nums[0],min=1;
        while (true){
            if(max>=len-1){
                return count;
            }
            int temp=max;
            for (int i=min;i<=temp;i++){
                max=Math.max(max,i+nums[i]);
            }
            min++;
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new jump().jump(new int[]{2,3,1,1,4}));
    }
}
