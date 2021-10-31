package Easy.MathTest;

public class trailingZeroes {


    //最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
    //这就是一道数学题
    public int trailingZeroes(int n) {
        int count = 0;
        while(n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(new trailingZeroes().trailingZeroes(75));
    }
}
