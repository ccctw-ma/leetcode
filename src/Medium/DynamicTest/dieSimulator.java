package Medium.DynamicTest;


/**
 * 1223. 掷骰子模拟
 * 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
 *
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
 *
 * 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
 *
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, rollMax = [1,1,2,2,2,3]
 * 输出：34
 * 解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
 * 示例 2：
 *
 * 输入：n = 2, rollMax = [1,1,1,1,1,1]
 * 输出：30
 * 示例 3：
 *
 * 输入：n = 3, rollMax = [1,1,1,2,2,3]
 * 输出：181
 *
 *
 * 提示：
 *
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/7  */

public class dieSimulator {

    int sum=0;
    public int dieSimulator(int n, int[] rollMax) {
        traverse(n,rollMax);
        return sum;
    }

    private void traverse(int n,int[] rollMax){
        if(n==0) return;
        for (int i=0;i<6;i++){
            if(rollMax[i]>0){
                if(n==1) sum++;
                rollMax[i]--;
                int[] arr=new int[6];
                System.arraycopy(rollMax, 0, arr, 0, 6);
                traverse(n-1,arr);
                rollMax[i]++;
            }
        }
    }




    public static void main(String[] args) {
        System.out.println(new dieSimulator().dieSimulator(3,new int[]{0,0,0,1,0,3}));
    }
}
