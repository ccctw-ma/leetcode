package Easy.MathTest;



/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842..., 
 *      由于返回类型是整数，小数部分将被舍去。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 69. x 的平方根 */


public class mySqrt {

    public int mySqrt(int x) {
        Math.sqrt(x);
        String s=String.valueOf(x);
        int index= (int) Math.pow(10, (s.length()-1) / 2);
        while (index*index<x) index++;
        if(index*index==x) return index;
        return index-1;

    }
    
    public static void main(String[] args) {
        System.out.println(Math.log10(3287109));
    }
}
