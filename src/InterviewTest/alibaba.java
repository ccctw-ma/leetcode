package InterviewTest;

import java.util.*;

public class alibaba {


    public static int test01(int[] arr) {
        Arrays.sort(arr);
        int res = 0;
        while (arr[1] != 0) {
            res += (arr[1] - arr[0] + 1);
            int temp = (arr[1] - arr[0] + 1);
            for (int i = 1; i < 5; i++) {
                arr[i] = arr[i] - temp;
            }
            Arrays.sort(arr);
        }
        return res;
    }


    public static int gcd1(int num1, int num2) {
        int temp = num1 % num2;
        while (temp != 0) {
            num1 = num2;
            num2 = temp;
            temp = num1 % num2;
        }
        return num2;
    }


    public static int test02(int[][] arr) {


        Map<String, Integer> map = new HashMap<>();
        for (int[] a : arr) {
            int A = a[0];
            int B = a[1];
            int C = a[2];
            if (B == 0) {
                map.put("100", map.getOrDefault("100", 0) + 1);
            } else {
                int g = gcd1(A, B);

                String key = A / g + "_" + B / g;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int[] nums = new int[map.values().size()];
        int index = 0;
        for (Integer value : map.values()) {
            nums[index++] = value;
        }
        Arrays.sort(nums);
        List<Integer> sum1 = new ArrayList<>();
        List<Integer> sum2 = new ArrayList<>();
        sum1.add(nums[nums.length - 1]);
        int sumA = nums[nums.length - 1];
        sum2.add(nums[nums.length - 2]);
        int sumB = nums[nums.length - 2];
        for (int i = nums.length - 3; i >= 0; i--) {
            int temp = nums[i];
            if (Math.abs(sumA + temp - sumB) < Math.abs(sumA - temp - sumB)) {
                sumA += temp;
                sum1.add(temp);
            } else {
                sumB += temp;
                sum2.add(temp);
            }
        }
        int ss1 = 0;
        int ss2 = 0;
        for (Integer integer : sum1) {
            ss1 += integer;
        }
        for (Integer integer : sum2) {
            ss2 += integer;
        }
        return ss1 * ss2;
    }

    public static int subStringArr(int[] A, int k) {
        int n = A.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + A[i - 1];
        }
        System.out.println(Arrays.toString(preSum));
        int[][] dp = new int[n][k];
        for (int t = 0; t < k; t++) {
            for (int i = 0; i < n; i++) {
                dp[i][t] = Integer.MAX_VALUE;
                if (t == 0) {
                    dp[i][t] = (preSum[i + 1]) / (i + 1);
                } else {
                    for (int j = i; j >= t; j--) {
                        dp[i][t] = Math.min(dp[i][t], dp[j - 1][t - 1] + (preSum[i + 1] - preSum[j]) / (i - j + 1));
                    }
                }
            }
        }

        return dp[n - 1][k - 1];
    }

    public static void main(String[] args) {
//        System.out.println(test01(new int[]{10, 20, 20, 30, 30}));
//        System.out.println(test01(new int[]{3, 4, 5, 6, 7}));
//        System.out.println(test02(new int[][]{{1, 1, 0}, {1, 2, 0},{1,2, 1}, {2, 1, 0}, {2, 1, 1}, {1, 3, 0}, {1, 3, 1}, {1, 3, 2}}));
        System.out.println(subStringArr(new int[]{9, 1, 2, 3, 9}, 3));
    }


}
