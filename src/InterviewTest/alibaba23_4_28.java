package InterviewTest;

public class alibaba23_4_28 {

    public static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmp(String text, String pattern) {
        int[] next = getNext(pattern);
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }
    public static String delete_str(String s, String t) {
        int index = kmp(s, t);
        if (index == -1) {
            return s;
        } else {
            String left = s.substring(0, index);
            String right = s.substring(index + t.length());
            return left + right;
        }
    }

    public static void main(String[] args) {
        System.out.println(delete_str("abcefg", "ced"));
    }
}
