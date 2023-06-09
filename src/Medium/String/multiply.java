package Medium.String;

/*
* 43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。*/

import java.math.BigInteger;

/**
 * @author 马世臣
 * @// TODO: 2020/8/13  */



public class multiply {

    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        int n1=num1.length();
        int n2=num2.length();
        int max=0;
        String[] res=new String[n2];
        for (int i=0;i<n2;i++){
            int a=num2.charAt(n2-i-1)-'0';
            int carry=0;
            StringBuilder builder=new StringBuilder();
            builder.append("0".repeat(i));
            for (int j=n1-1;j>=0;j--){
                int b=num1.charAt(j)-'0';
                int temp=a*b+carry;
                builder.append(temp%10);
                carry=temp/10;
            }
            if(carry>0) builder.append(carry);
            res[i]=builder.toString();
            max=Math.max(res[i].length(),max);
        }
        StringBuilder ans=new StringBuilder();
        int carry=0;
        for (int i=0;i<max;i++){
            int sum=0;
            for (String s:res){
                int num=i<s.length()?s.charAt(i)-'0':0;
                sum+=num;
            }
            sum+=carry;
            ans.append(sum%10);
            carry=sum/10;
        }
        if(carry!=0) ans.append(carry);
        return ans.reverse().toString();
    }


    //因为两个数的乘积在结果的位置是固定的，所以可以直接使用数组来表示
    //数组的最大长度为m+n是可以推导出来的
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        System.out.println(new multiply().multiply("123","456"));
        BigInteger a=new BigInteger("142365757");
        BigInteger res=a.multiply(new BigInteger("8877576467476"));
        System.out.println(res.toString());
    }
}
