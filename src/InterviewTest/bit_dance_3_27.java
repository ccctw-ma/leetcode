package InterviewTest;

public class bit_dance_3_27 {


    public static int test01(int N, int M, int T) {
        int a = (M + 1) * M / 2 + M * (T - 1);
        int min_a = M + T + 9;
        int b = (M + 1) * M / 2 + M * (T - 2);
        int min_b = M + T + 3;
        int m = M / 2;
        int c = (m + M) * (M - m + 1) / 2 + M * (T - 2);
        int min_c = M - m + 1 + T - 2 + 5;
        int d = c + M;
        if (N <= a) {
            return calculate(N, M, 1);
        } else {
            int res = min_b;
            N -= b;
            while (N > 0) {
                if (N > d) {
                    res += min_c;
                    N -= c;
                } else {
                    res += calculate(N, M, M / 2);
                    N = 0;
                }
            }
            return res;
        }
    }

    public static int calculate(int N, int M, int m) {
        int min = m, sum = 0, count = 0;
        while (sum < N) {
            sum += min;
            if (min != M) {
                min++;
            }
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(test01(40, 5, 2));

    }
}
