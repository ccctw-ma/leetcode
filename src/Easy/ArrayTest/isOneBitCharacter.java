package Easy.ArrayTest;




/**
 * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
 *
 * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
 *
 * 示例 1:
 *
 * 输入: 
 * bits = [1, 0, 0]
 * 输出: True
 * 解释: 
 * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
 * 示例 2:
 *
 * 输入: 
 * bits = [1, 1, 1, 0]
 * 输出: False
 * 解释: 
 * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
 * 注意:
 *
 * 1 <= len(bits) <= 1000.
 * bits[i] 总是0 或 1.
 **/


/**
 * @author 马世臣 
 * @// TODO: 2020/2/1 717. 1比特与2比特字符 */

public class isOneBitCharacter {


    public boolean isOneBitCharacter(int[] bits) {
        if(bits.length==1) return true;
        int flag=0;
        for (int i=0;i<bits.length;i++){
            if(i+1<bits.length&&bits[i]==1&&bits[i+1]==1){
                flag=3;
                i++;
            }else if(i+1<bits.length&&bits[i]==1&&bits[i+1]==0){
                flag=2;
                i++;
            }else if(bits[i]==0){
                flag=1;
            }
        }
        return flag==1;
    }


    //妙呀
    public boolean isOneBitCharacter2(int[] bits) {
        if( null == bits || 0 == bits.length ) return false;
        int i = 0;
        while(i < bits.length - 1)
        {
            if(0 == bits[i]) i++;
            else i+=2;
        }
        return i != bits.length;
    }
    public static void main(String[] args) {

    }
}
