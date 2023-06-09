package Medium.Stack;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author 马世臣
 * @// TODO: 2020/3/5  */

public class dailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] res=new int[T.length];
        for (int i=T.length-1;i>=0;i--){
            int j=i+1;
            while (j<T.length&&T[i]>=T[j]){
                j++;
            }
            if(j==T.length){
                res[i]=0;
            }else {
                res[i]=j-i;
            }
        }
        return res;
    }


    //站里面存索引，从栈中拿索引在数组中进行比较
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }


    public int[] dailyTemperatures3(int[] T) {
        int[] res=new int[T.length];
        Deque<Integer> stack=new ArrayDeque<>();
        for (int i=0;i<T.length;i++){
            while (!stack.isEmpty()&&T[i]>T[stack.peek()]){
                int index=stack.pop();
                res[index]=i-index;
            }
            stack.push(i);
        }
        while (!stack.isEmpty())    res[stack.pop()]=0;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new dailyTemperatures().dailyTemperatures3(new int[]{73,74,75,71,69,72,76,73})));
    }
}
