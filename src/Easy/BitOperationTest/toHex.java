package Easy.BitOperationTest;


/**
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 *
 * 注意:
 *
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * 示例 1：
 *
 * 输入:
 * 26
 *
 * 输出:
 * "1a"
 * 示例 2：
 *
 * 输入:
 * -1
 *
 * 输出:
 * "ffffffff"
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/17 405. 数字转换为十六进制数 */
public class toHex {
    
    public static String toHex(int num) {
        if(num==0){
            return "0";
        }
        char[] chars=new char[8];
        int temp;
        for (int i=0;i<8;i++){
            temp=num&15;
            if(temp>=0&&temp<=9){
                chars[i]=(char)(temp+'0');
            }else {
                chars[i]=(char)(temp-10+'a');
            }
            num>>=4;
        }
        int i=7;
        while (chars[i]=='0'){
            i--;
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int j=i;j>=0;j--){
            stringBuilder.append(chars[j]);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        System.out.println(Integer.toHexString(-1));
    }
}
