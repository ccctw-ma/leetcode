package Easy.BitOperationTest;


/**
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 *
 * 注意:
 *
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 示例 1:
 *
 * 输入: 5
 * 输出: 2
 * 解释: 5的二进制表示为101（没有前导零位），其补数为010。所以你需要输出2。
 * 示例 2:
 *
 * 输入: 1
 * 输出: 0
 * 解释: 1的二进制表示为1（没有前导零位），其补数为0。所以你需要输出0。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/17 476. 数字的补数 */
public class findComplement {

    public static int findComplement(int num) {
        int result=0,n=0;
        while ((num&0x80000000)==0){
            num=num<<1;
            n++;
        }
        for (int i=n;i<32;i++){
            if((num&0x80000000)!=0){
                num<<=1;
                result<<=1;
                result+=0;

            }else {
                num<<=1;
                result<<=1;
                result+=1;

            }
        }
        return result;
    }

    public static int func2(int num){
        int temp = num, c = 0;
        while(temp > 0){
            temp >>= 1;
            c =  (c << 1) + 1;
        }
        return num ^ c;
    }

    public static void main(String[] args) {
        System.out.println(func2(6));
    }

}
