package Medium.ArrayTest;



/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/17
 * */

public class canJump {


    public boolean canJump(int[] nums) {
        int len=nums.length-1;
        int max=nums[0],pmax=0;
        if(max>=len) return true;
        while (true){
            int a=max;
            for (int i=pmax+1;i<=max;i++){
                a=Math.max(a,i+nums[i]);
            }
            if(a>=len) return true;
            if(a==max) return false;
            pmax=max;
            max=a;
        }
    }

    public static void main(String[] args) {
        System.out.println(new canJump().canJump(new int[]{3,2,1,1,4}));
    }
}
