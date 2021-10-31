package Medium.MathTest;


/**
 * 372. 超级次方
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 示例 1:
 *
 * 输入: a = 2, b = [3]
 * 输出: 8
 * 示例 2:
 *
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 *
 * */

import java.util.PriorityQueue;

/**
 * @author 马世臣
 * @// TODO: 2020/5/9  */


public class superPow {


    private int mod=1337;
    public int superPow(int a, int[] b) {
        return superpow(a,b,b.length-1);
    }

    private int superpow(int a,int[] b,int index){
        if(index==-1) return 1;
        int k=b[index];
        int part1=pow(a,k);
        int part2=pow(superpow(a,b,index-1),10);
        return (part1*part2)%mod;
    }

    private int pow(int a,int b){
        if (b == 0) return 1;
        a %= mod;

        if (b % 2 == 1) {
            // k 是奇数
            return (a * pow(a, b - 1)) % mod;
        } else {
            // k 是偶数
            int sub = pow(a, b / 2);
            return (sub * sub) % mod;
        }

    }

    public static void main(String[] args) {
        System.out.println();
        PriorityQueue<Integer> queue=new PriorityQueue<>(6, (o1, o2) -> o2-o1);

    }
}
