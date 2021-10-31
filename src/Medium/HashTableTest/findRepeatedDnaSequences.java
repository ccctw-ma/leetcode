package Medium.HashTableTest;

import java.util.*;

/**
 * @author msc
 * @version 1.0
 * @date 2021/10/8 9:08
 */
public class findRepeatedDnaSequences {


    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len < 10) return res;
        char[] chars = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(compute(chars, 0), 1);
        for (int i = 1; i <= len - 10; i++) {
            int temp = compute(chars, i);
            int count = map.getOrDefault(temp, 0);
            if (count == 1) res.add(String.valueOf(chars, i, 10));
            map.put(temp, count + 1);
        }
        return res;
    }

    public int compute(char[] chars, int index) {
        int res = 0;
        for (int i = 0; i < 10; i++) {
            char c = chars[index + i];
            int temp = 0;
            if (c == 'A') temp = 1;
            if (c == 'C') temp = 2;
            if (c == 'G') temp = 3;
            if (c == 'T') temp = 4;
            res = res * 10 + temp;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new findRepeatedDnaSequences().findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
}
