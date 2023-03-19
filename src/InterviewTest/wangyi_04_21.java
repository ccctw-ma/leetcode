package InterviewTest;

/**
 * @author msc
 * @version 1.0
 * @date 2022/4/22 0:02
 */
public class wangyi_04_21 {

    public static int test01(int n, int p, int x, int[] arr) {
        int[] pre_sum = new int[n + 1];
        //构造前缀和数组
        for (int i = 0; i < n; i++) {
            pre_sum[i + 1] = (pre_sum[i] + arr[i]) % x;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int left = pre_sum[i];
            int right = (pre_sum[n] - pre_sum[i + 1] + x) % x;
            int other_sum = (left + right) % x;
            int remain = x - other_sum;
            int cur = arr[i];
            if (remain <= p) {
                int c = (p  - remain) / x + 1;
                if ((cur + remain) % x == 0 && cur <= p) {
                    c -= 1;
                }
                res += c;
            }
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println((long) Math.pow(10, 10));
        System.out.println(test01(4, 3, 2, new int[]{2, 5, 3, 4}));
    }
}
