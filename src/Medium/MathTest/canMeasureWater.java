package Medium.MathTest;


/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False*/

/**
 * @author 马世臣
 * @// TODO: 2020/3/21  */

public class canMeasureWater {


    public boolean canMeasureWater(int x, int y, int z) {
        if(z>x+y) return false;
        if(z==x||z==y||z==x+y||z==0) return true;
        if(x == 0 || y == 0) return false;
        int a=Math.min(x,y),b=Math.max(x,y);
        if(z%a==0) return true;
        int diff=(b-a)%a==0?a:(b-a)%a;
        return z%diff==0;
    }


    public static void main(String[] args) {
        System.out.println(new canMeasureWater().canMeasureWater(2,6,5));
    }
}
