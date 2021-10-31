package Medium.Stack;


/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 示例 1:
 *
 * 输入: 
 * asteroids = [5, 10, -5]
 * 输出: [5, 10]
 * 解释: 
 * 10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。
 * 示例 2:
 *
 * 输入: 
 * asteroids = [8, -8]
 * 输出: []
 * 解释: 
 * 8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3:
 *
 * 输入: 
 * asteroids = [10, 2, -5]
 * 输出: [10]
 * 解释: 
 * 2 和 -5 发生碰撞后剩下 -5。10 和 -5 发生碰撞后剩下 10。
 * 示例 4:
 *
 * 输入: 
 * asteroids = [-2, -1, 1, 2]
 * 输出: [-2, -1, 1, 2]
 * 解释: 
 * -2 和 -1 向左移动，而 1 和 2 向右移动。
 * 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 * 说明:
 *
 * 数组 asteroids 的长度不超过 10000。
 * 每一颗行星的大小都是非零整数，范围是 [-1000, 1000] 。
 **/

import java.util.Arrays;
import java.util.Stack;

/**
 * @author 马世臣 
 * @// TODO: 2020/3/5 735. 行星碰撞 */

public class asteroidCollision {


    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack=new Stack<>();
        for (int item:asteroids){
            if(stack.isEmpty()){
                stack.push(item);
            }else if(stack.peek()*item>0||(stack.peek()*item<0&&stack.peek()<0)){
                stack.push(item);
            }else {
                item=-item;
                while (true){
                    if(!stack.isEmpty()&&stack.peek()<item){
                        stack.pop();
                        if(stack.isEmpty()||stack.peek()<0) {
                            stack.push(-item);
                            break;
                        }
                    }else if(!stack.isEmpty()&&stack.peek()==item){
                        stack.pop();
                        break;
                    }else if(!stack.isEmpty()&&stack.peek()>item){
                        break;
                    }else {
                        stack.push(-item);
                    }
                }
            }
        }
        int[] res=new int[stack.size()];
        for (int i=stack.size()-1;i>=0;i--){
            res[i]=stack.pop();
        }
        return res;
    }


    //妙呀，逻辑比较清晰
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast: asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }

    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new asteroidCollision().asteroidCollision(new int[]{-2, -1, 1, 2})));
    }
}
