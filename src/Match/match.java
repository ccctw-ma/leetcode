package Match;

import java.util.Arrays;

/**
 * @author msc
 * @version 1.0
 * @date 2021/10/3 10:29
 */
public class match {

    public int minimumMoves(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (c == 'X') {
                i += 2;
                count++;
            }
        }
        return count;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = mean * (m + n);
        int[] res = new int[n];
        for (int roll : rolls) {
            sum -= roll;
        }
        if (sum < n || sum > n * 6) return new int[0];
        int ave = sum / n, mod = sum % n;
        Arrays.fill(res, ave);
        for (int i = 0; i < n && mod != 0; i++) {
            res[i]++;
            mod--;
        }
        return res;
    }


    public boolean stoneGameIX(int[] stones) {
        int len = stones.length;
        if (len == 1) return false;


        return false;
    }


    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == letter ? 1 : 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); count -= s.charAt(i++) == letter ? 1 : 0) {
            while (sb.length() + s.length() > i + k && sb.length() > 0 && s.charAt(i) < sb.charAt(sb.length() - 1)
                    && (sb.charAt(sb.length() - 1) != letter || count != repetition)) {
                repetition += sb.charAt(sb.length() - 1) == letter ? 1 : 0;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (k - sb.length() > Math.max(0, s.charAt(i) == letter ? 0 : repetition)) {
                sb.append(s.charAt(i));
                repetition -= s.charAt(i) == letter ? 1 : 0;
            }
        }
        return "" + sb;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.compare(new char[]{'b', 'b'}, new char[]{'a', 'b'}));
    }
}
