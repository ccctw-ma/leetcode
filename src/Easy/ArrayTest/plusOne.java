package Easy.ArrayTest;


/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 **/

import java.util.Arrays;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/27 66. 加一 */


public class plusOne {


    public int[] plusOne(int[] digits) {
        if(digits.length==0){
            return new int[]{1};
        }
        int temp=0;
        if(digits[digits.length-1]==9){
            digits[digits.length-1]=0;
            temp=1;
        }else {
            digits[digits.length-1]=digits[digits.length-1]+1;
        }
        for (int i=digits.length-2;i>=0;i--){
            if(digits[i]+temp==10){
                digits[i]=0;
                temp=1;
            }else {
                digits[i]+=temp;
                temp=0;
                break;
            }
        }
        if(temp==1){
            int[] res=new int[digits.length+1];
            res[0]=1;
            Arrays.fill(res,1,res.length,0);
            return res;
        }
        return digits;


    }

    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //跳出for循环，说明数字全部是9
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }

    public static void main(String[] args) {
        int[] digits=new int[]{9,9,9,9};
        int[] result=new plusOne().plusOne(digits);
        for (int i:result){
            System.out.print(i+" ");
        }
        System.out.println(Integer.MAX_VALUE);
    }
}
