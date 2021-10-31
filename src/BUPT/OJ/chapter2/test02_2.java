package BUPT.OJ.chapter2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/23 20:47
 */
public class test02_2 {


    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void operation() {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while (T-- > 0) {
            int lenA = in.nextInt();
            int lenB = in.nextInt();
            //创建链表A
            Node preA = new Node(0);
            Node nodeA = new Node(in.nextInt());
            preA.next = nodeA;
            for (int i = 1; i < lenA; i++) {
                Node node = new Node(in.nextInt());
                nodeA.next = node;
                nodeA = node;
            }
            //创建链表B
            Node preB = new Node(0);
            Node nodeB = new Node(in.nextInt());
            preB.next = nodeB;
            for (int i = 1; i < lenB; i++) {
                Node node = new Node(in.nextInt());
                nodeB.next = node;
                nodeB = node;
            }
            //处理链表A和B生成C
            Node p1 = preA.next, p2 = preB.next;
            Node next = new Node(0), head = next, temp;
            while (p1 != null || p2 != null) {
                if (p1 != null && p2 != null) {
                    next.next = p1;
                    p1 = p1.next;
                    next = next.next;
                    next.next = p2;
                    p2 = p2.next;
                    next = next.next;
                } else if (p1 == null) {
                    next.next = p2;
                    p2 = p2.next;
                    next = next.next;
                } else if (p2 == null) {
                    next.next = p1;
                    p1 = p1.next;
                    next = next.next;
                }
            }
            Node node = head.next;
            while (node != null) {
                System.out.print(node.val + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    public static void createFile() {
        try {
            File inputFile = new File("src/BUPT/OJ/chapter2/in");
            File outputFile = new File("src/BUPT/OJ/chapter2/out");
            if (inputFile.exists()) inputFile.delete();
            if (outputFile.exists()) outputFile.delete();
            boolean inIsOK = inputFile.createNewFile();
            boolean outIsOK = outputFile.createNewFile();
            if (inIsOK && outIsOK) {
                FileWriter in = new FileWriter(inputFile);
                FileWriter out = new FileWriter(outputFile);
                Random random = new Random();
                //有一个正确答案
                List<List<int[]>> res = new ArrayList<>();
                for (int t = 0; t < 20; t++) {
                    List<int[]> list = new ArrayList<>();
                    int lenA = random.nextInt(1000) + 10;
                    int lenB = random.nextInt(1000) + 10;
                    int lenC = lenA + lenB;
                    int[] arrA = new int[lenA];
                    int[] arrB = new int[lenB];
                    int[] arrC = new int[lenC];
                    for (int i = 0; i < lenA; i++) {
                        int temp = random.nextInt(2000);
                        arrA[i] = temp;
                    }
                    for (int i = 0; i < lenB; i++) {
                        int temp = random.nextInt(2000);
                        arrB[i] = temp;
                    }
                    int index = 0, a = 0, b = 0;
                    while (a != lenA || b != lenB) {
                        if (a != lenA && b != lenB) {
                            arrC[index++] = arrA[a++];
                            arrC[index++] = arrB[b++];
                        } else if (a != lenA) {
                            arrC[index++] = arrA[a++];
                        } else {
                            arrC[index++] = arrB[b++];
                        }
                    }
                    list.add(arrA);
                    list.add(arrB);
                    list.add(arrC);
                    res.add(list);
                }
                in.write(20 + "\n");
                for (List<int[]> list : res) {
                    int[] a = list.get(0);
                    int[] b = list.get(1);
                    int[] c = list.get(2);
                    in.write(a.length + " " + b.length + "\n");
                    StringBuilder builder = new StringBuilder();
                    for (int item : a) {
                        builder.append(item);
                        builder.append(" ");
                    }
                    builder.append("\n");
                    in.write(builder.toString());
                    builder = new StringBuilder();
                    for (int item : b) {
                        builder.append(item);
                        builder.append(" ");
                    }
                    builder.append("\n");
                    in.write(builder.toString());
                    builder = new StringBuilder();
                    for (int item : c) {
                        builder.append(item);
                        builder.append(" ");
                    }
                    builder.append("\n");
                    out.write(builder.toString());
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
//        createFile();
        operation();
    }
}
