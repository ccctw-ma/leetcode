package Easy.ArrayTest;

import java.util.Arrays;

/**
 * @author msc
 * @version 1.0
 * @date 2021/8/29 9:50
 */
public class sumOddLengthSubarrays {


    public int sumOddLengthSubarrays(int[] arr) {
        int[] sums = new int[arr.length + 1];
        int res = 0;
        res += Arrays.stream(arr).sum();
        for (int i = 0; i < arr.length; i++) {
            sums[i + 1] = sums[i] + arr[i];
        }
        for (int i = 3; i <= arr.length; i += 2) {
            for (int j = 0; j <= arr.length - i; j++) {
                res += (sums[j + i] - sums[j]);
            }
        }
        return res;
    }

    public int sumOddLengthSubarrays2(int[] arr) {
        int result = 0, len = arr.length;
        for (int i = 0; i < len; i++) {
            int leftOdd = (i + 1) / 2;
            int leftEven = i / 2 + 1;
            int rightOdd = (len - i) / 2;
            int rightEven = (len - 1 - i) / 2 + 1;
            result += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new sumOddLengthSubarrays().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }
}
