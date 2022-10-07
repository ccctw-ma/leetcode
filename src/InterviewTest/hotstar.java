package InterviewTest;

public class hotstar {


    public static int test02(int[] arr) {
        int res = Integer.MAX_VALUE;
        // 纯暴力
        for (int t = 0; t <= 4; t++) {

            int count = 0;
            for (int x : arr) {
                int c = (t - x + 5) % 5;
                count += c;
            }
            count = count / 2 + count % 2;
            res = Math.min(count, res);
        }
        return res;

    }

    public static int test03(int n, String s) {
        if (s.length() == 1) return (int) Math.pow(2, n);
        if (s.length() == n) return (int) Math.pow(3, n) - 1;

        int[] dp = new int[n + 1];
        int k = s.length();
        dp[k] = 1;


        return 0;
    }

    public static boolean check(int[] arr) {
        int a = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (a != arr[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(test02(new int[]{1, 1, 1, 1, 1}));
        System.out.println(test02(new int[]{2, 2, 1, 2, 2}));
        System.out.println(test02(new int[]{2, 1, 1, 2, 1}));
        System.out.println(test02(new int[]{1, 2, 3, 4, 5}));
    }
}
