package Easy.MathTest;

/**
 * 给定一个正整数 N，找到并返回 N 的二进制表示中两个连续的 1 之间的最长距离。 
 *
 * 如果没有两个连续的 1，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：22
 * 输出：2
 * 解释：
 * 22 的二进制是 0b10110 。
 * 在 22 的二进制表示中，有三个 1，组成两对连续的 1 。
 * 第一对连续的 1 中，两个 1 之间的距离为 2 。
 * 第二对连续的 1 中，两个 1 之间的距离为 1 。
 * 答案取两个距离之中最大的，也就是 2 。
 * 示例 2：
 *
 * 输入：5
 * 输出：2
 * 解释：
 * 5 的二进制是 0b101 。
 * 示例 3：
 *
 * 输入：6
 * 输出：1
 * 解释：
 * 6 的二进制是 0b110 。
 * 示例 4：
 *
 * 输入：8
 * 输出：0
 * 解释：
 * 8 的二进制是 0b1000 。
 * 在 8 的二进制表示中没有连续的 1，所以返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= N <= 10^9
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 868. 二进制间距 */

public class binaryGap {


    public int binaryGap(int N) {
        int[] bucket=new int[32];
        for (int i=0;i<32;i++){
            if((N&1)==1){
                bucket[i]=1;
            }else {
                bucket[i]=0;
            }
            N>>=1;
        }
        int max=0,i=0;
        while (i<32&&bucket[i]==0) i++;
        while (i<32){
            int j=i+1;
            while (j<32&&bucket[j]==0) j++;
            if(j<32) max=Math.max(max,j-i);
            i=j;
        }
        return max;
    }


    //一次遍历，节省空间
    public int binaryGap2(int N) {
        int last = -1, ans = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) > 0) {
                if (last >= 0)
                    ans = Math.max(ans, i - last);
                last = i;
            }

        return ans;
    }

    
    public static void main(String[] args) {
        System.out.println(new binaryGap().binaryGap(8));
    }
}
