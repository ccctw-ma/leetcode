package InterviewTest;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class xiaohongshu23_3_26 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        int M = in.nextInt();
        int[] ls = new int[M];
        int[] rs = new int[M];
        int[] xs = new int[M];
        for (int i = 0; i < N; i++) {
            ls[i] = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            rs[i] = in.nextInt();
        }
        String os = in.next();
        for (int i = 0; i < N; i++) {
            xs[i] = in.nextInt();
        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(ls));
//        System.out.println(Arrays.toString(rs));
//        System.out.println(Arrays.toString(xs));
//        System.out.println(os);
        for (int i = 0; i < M; i++) {
            int l = ls[i], r = rs[i], x = xs[i];
            char c = os.charAt(i);
            for (int j = l - 1; j < r; j++) {
                if (c == '|') {
                    arr[j] |= x;
                } else if (c == '&') {
                    arr[j] &= x;
                } else {
                    arr[j] = x;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        res.append(arr[0]);
        for (int i = 1; i < N; i++) {
            res.append(" ");
            res.append(arr[i]);
        }
        System.out.println(res);
    }
}
