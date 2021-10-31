package Easy.MathTest;



/**
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 *
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 *  
 *
 * 示例：
 *
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *  
 *
 * 提示：
 *
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 507. 完美数 */

public class checkPerfectNumber {


    public boolean checkPerfectNumber(int num) {
        List<Integer> list=new ArrayList<>();
        int n=(int) Math.sqrt(num);
        for (int i=2;i<=n;i++){
            if(num%i==0){
                if(i!=num/i){
                    list.add(i);
                }
                list.add(num/i);
            }
        }
        int sum=0;
        for (int i:list) sum+=i;
        return sum+1==num;
    }
    
    public static void main(String[] args) {

    }

}
