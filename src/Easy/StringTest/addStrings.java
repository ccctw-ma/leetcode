package Easy.StringTest;


/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/28 415. 字符串相加 */

public class addStrings {


    public String addStrings(String num1, String num2) {
        int temp=0;
        char[] chars1=num1.toCharArray();
        char[] chars2=num2.toCharArray();
        if(num1.length()<num2.length()){
            int j=num1.length()-1;
            for (int i=num2.length()-1;i>=0;i--){
                int a= chars2[i]-'0';
                int b=0;
                if(j>=0){
                    b= chars1[j--]-'0';
                }
                chars2[i]=(char)((a+b+temp)%10+'0');
                temp=(a+b+temp)/10;
            }
            if(temp==1){
                return "1" + String.valueOf(chars2);
            }else {
                return String.valueOf(chars2);
            }
        }else {
            int j=num2.length()-1;
            for (int i=num1.length()-1;i>=0;i--){
                int a= chars1[i]-'0';
                int b=0;
                if(j>=0){
                    b= chars2[j--]-'0';
                }
                chars1[i]=(char)((a+b+temp)%10+'0');
                temp=(a+b+temp)/10;
            }
            if(temp==1){
                return "1" + String.valueOf(chars1);
            }else {
                return String.valueOf(chars1);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new addStrings().addStrings("9","99"));
    }
}
