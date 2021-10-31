package Easy.StringTest;


/**
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 *
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 *
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 *
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 * 注意:
 *
 * N 的取值范围是 [1, 10000]。
 **/


/**
 * @author 马世臣
 * @// TODO: 2020/1/30  788. 旋转数字*/


public class rotatedDigits {


    public int rotatedDigits(int N) {
        int n=0;
        for (int i=1;i<=N;i++){
            String s=String.valueOf(i);
            if(s.contains("2")||s.contains("5")||s.contains("6")||s.contains("9")){
                if(!s.contains("3")&&!s.contains("4")&&!s.contains("7")){
                    if(s.length()==1||s.contains("0")||s.contains("1")||s.contains("8")){
                        n++;
                    }
                }
            }
        }
        return n;
    }

    public int rotatedDigits2(int N) {
        if (N == 1) return 0;
        boolean isGood = true;
        boolean isAll018 = true;
        int tmp = N;
        do {
            int remainder = tmp % 10;
            if ((remainder == 3) || (remainder == 4) || (remainder == 7)) {
                isGood = false;
                break;
            } else if ((remainder == 2) || (remainder == 5) || (remainder == 6) || (remainder == 9)) {
                isAll018 = false;
            };
            tmp = tmp / 10;
        } while (tmp > 0);
        return ((isGood && !isAll018) ? 1 : 0) + rotatedDigits2(N - 1);
    }


    public static void main(String[] args) {

    }
}
