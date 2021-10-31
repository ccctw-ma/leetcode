package BUPT.test01;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T)>=0){
            int n = in.nextInt();
            int k = in.nextInt();
            String s= in.next();
            int len = s.length();
            char[] chars = s.toCharArray();
            int left = 0, right = len-1;
            while (left<len&&chars[left]!='*') left++;
            while (right>=0&&chars[right]!='*') right--;
            if(left==right){
                System.out.println(1);
            }else{
                chars[left] = 'x';
                chars[right] = 'x';
                int res = 2;
                while (left + k < right) {
                    int index = left;
                    for (int i = left + 1; i <= left + k; i++) {
                        if (chars[i] == '*') {
                            index = i;
                        }
                    }
                    chars[index] = 'x';
                    res++;
                    left = index;
                }
                System.out.println(res);
            }

        }

    }
}
