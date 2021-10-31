package Easy.BitOperationTest;


/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/17  461. 汉明距离*/
public class hammingDistance {

    public static int hammingDistance(int x, int y) {
        int index=x^y,sum=0;
        while (index!=0){
            index&=(index-1);
            sum++;
        }
        return sum;
    }

    public static int hammingDistance2(int x, int y) {
        return Integer.bitCount(x^y);
    }


    public static void main(String[] args) {
        System.out.println(hammingDistance2(2,5));

    }
}
