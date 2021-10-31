package Easy.MathTest;


/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 *
 * 示例1:
 *
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 *
 * 示例2:
 *
 * 输入: 3
 * 输出: False
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 633. 平方数之和 */


public class judgeSquareSum {

    public boolean judgeSquareSum(int c) {
        int a= (int) Math.sqrt(c);
        if(a*a==c) return true;
        for (long i=a;i>=a/2;i--){
            long j=1;
            while ((i*i+j*j)<c){
                j++;
            }
            if(i*i+j*j==c) return true;
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b)
                return true;
        }
        return false;
    }


    //费马定律
    public boolean judgeSquareSum3(int c) {
        for(int i = 2; i * i <= c; i++){
            int count = 0;
            if(c % i == 0){
                while(c % i == 0){
                    count++;
                    c /= i;
                }
                if(i % 4 == 3 && count % 2 != 0){
                    return false;
                }
            }
        }
        return c % 4 != 3;
    }
    
    public static void main(String[] args) {

    }
}
