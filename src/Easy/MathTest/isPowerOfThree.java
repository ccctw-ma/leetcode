package Easy.MathTest;


/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 27
 * 输出: true
 * 示例 2:
 *
 * 输入: 0
 * 输出: false
 * 示例 3:
 *
 * 输入: 9
 * 输出: true
 * 示例 4:
 *
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/6 326. 3的幂 */

public class isPowerOfThree {

    public boolean isPowerOfThree(int n) {
        if(n<1) return false;
        while (n>1){
            if(n%3!=0) return false;
            n/=3;
        }
        return true;
    }

    //if(n==1||n==3||n==9) return true;
    //        int index=n%10;
    //        if(index!=1&&index!=3&&index!=7&&index!=9) return false;
    //        int x=n%9;
    //        return x >= 2 && x <= 5;
    
    public static void main(String[] args) {

    }
}
