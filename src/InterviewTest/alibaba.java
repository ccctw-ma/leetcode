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

    public static int test03(int[] arr, int n) {
        int[] ones = new int[32];
        int[] zeros = new int[32];
        for (int c : arr) {
            for (int i = 0; i <= 31; i++) {
                int tmp = 1 << i;
                if ((c & tmp) == 0) {
                    zeros[i]++;
                } else {
                    ones[i]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += Math.min(ones[i], zeros[i]);
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(test01(new int[]{10, 20, 20, 30, 30}));
//        System.out.println(test01(new int[]{3, 4, 5, 6, 7}));
//        System.out.println(test02(new int[][]{{1, 1, 0}, {1, 2, 0},{1,2, 1}, {2, 1, 0}, {2, 1, 1}, {1, 3, 0}, {1, 3, 1}, {1, 3, 2}}));
        System.out.println(test03(new int[]{3, 4, 6}, 3));
        System.out.println(test03(new int[]{9, 9, 9, 9}, 4));
    }


}
