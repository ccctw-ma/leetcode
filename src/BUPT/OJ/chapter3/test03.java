package BUPT.OJ.chapter3;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * @author msc
 * @version 1.0
 * @date 2021/10/5 16:35
 */
public class test03 {


    public static String operation(String s) {
        //存放字符
        Deque<Character> deque1 = new ArrayDeque<>();
        //存放操作符
        Deque<Character> deque2 = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                deque1.addLast(c);
            } else {
                while (!deque2.isEmpty() && map.get(c) <= map.get(deque2.peek())) {
                    deque1.addLast(deque2.poll());
                }
                deque2.push(c);
            }
        }
        while (!deque2.isEmpty())
            deque1.addLast(deque2.poll());
        StringBuilder builder = new StringBuilder();
        while (!deque1.isEmpty())
            builder.append(deque1.pollFirst());
        return builder.toString();
    }


    public static void createFile() {
        try {
            File inputFile = new File("src/BUPT/OJ/chapter3/test.in");
            File outputFile = new File("src/BUPT/OJ/chapter3/test.out");
            if (inputFile.exists()) inputFile.delete();
            if (outputFile.exists()) outputFile.delete();
            boolean inIsOK = inputFile.createNewFile();
            boolean outIsOK = outputFile.createNewFile();
            if (inIsOK && outIsOK) {
                FileWriter in = new FileWriter(inputFile);
                FileWriter out = new FileWriter(outputFile);
                Random random = new Random();
                char[] characters = new char[26];
                for (int i = 0; i < 26; i++) {
                    characters[i] = (char) ('A' + i);
                }
                char[] operation = new char[]{'+', '-', '*', '/'};
                in.write("50\n");
                for (int i = 0; i < 50; i++) {
                    int num = random.nextInt(50) + 2;
                    char[] chars = new char[num * 2 - 1];
                    for (int j = 0, index = 0; j < num; j++, index += 2) {
                        chars[index] = characters[random.nextInt(26)];
                    }
                    for (int j = 0, index = 1; j < num - 1; j++, index += 2) {
                        chars[index] = operation[random.nextInt(4)];
                    }
                    in.write(chars.length + "\n");
                    in.write(String.valueOf(chars) + "\n");
                    out.write(operation(String.valueOf(chars)) + "\n");
                }
                in.flush();
                out.flush();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        createFile();
//        System.out.println(operation("U*V+P*Z*P/E/C+C*V-A+M-L-C-V*N*B*J+T-J/Y*T-T-X+T/M/C+R/Y+N+X"));
    }
}
