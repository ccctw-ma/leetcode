import java.util.Scanner;

public class Main {
    private static int findSubsequenceLen(int[] nums) {
        int len = 1;
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[len] = nums[0];
        for (int num : nums) {
            if (num > dp[len] && num % dp[len] == 0) {
                len++;
                dp[len] = num;
            } else {
                int l = 1;
                int r = len;
                int pos = 0;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if (dp[mid] > num) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                if (l == 1) {
                    dp[1] = num;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[]{};
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            System.out.println(findSubsequenceLen(nums));
        }
    }
}
