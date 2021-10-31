package BUPT.OJ.chapter1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/16 19:12
 */
public class test01 {


    public static int test(int[] arr, int length) {
        int start = 0, end = length - 1;
        while ((start <= end) && (start <= length - 1) && (end >= 0)) {
            int middle = (start + end) / 2;
            if (arr[middle] == middle)
                return middle;
            else if (arr[middle] < middle)
                start = middle + 1;
            else
                end = middle - 1;
        }
        return -1;
    }


    public static void createFile() {
        try {
            File inputFile = new File("src/BUPT/OJ/chapter1/test.in");
            File outputFile = new File("src/BUPT/OJ/chapter1/test.out");
            if (inputFile.exists()) inputFile.delete();
            if (outputFile.exists()) outputFile.delete();
            boolean inIsOK = inputFile.createNewFile();
            boolean outIsOK = outputFile.createNewFile();
            if (inIsOK && outIsOK) {
                FileWriter in = new FileWriter(inputFile);
                FileWriter out = new FileWriter(outputFile);
                Random random = new Random();
                //有一个正确答案
                List<int[]> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    int len = random.nextInt(100) + 10;
                    int[] arr = new int[len];
                    for (int j = 0; j < len; j++) {
                        if (j < i) {
                            arr[j] = j - 1;
                        } else if (j == i) {
                            arr[j] = j;
                        } else {
                            arr[j] = j + 1;
                        }
                    }
                    list.add(arr);
                }
                //随机生成数据
                for (int i = 0; i < 10; i++) {
                    int len = random.nextInt(100);
                    Set<Integer> set = new HashSet<>();
                    int[] arr = new int[len];
                    for (int j = 0; j < len; j++) {
                        int temp = random.nextInt(150);
                        while (set.contains(temp)) {
                            temp = random.nextInt(150);
                        }
                        set.add(temp);
                        arr[j] = temp;
                    }
                    Arrays.sort(arr);
                    int count = 1;
                    List<Integer> temp = new ArrayList<>();
                    for (int k = 0; k < arr.length; k++) {
                        if (arr[k] != k) temp.add(arr[k]);
                        if (arr[k] == k && count == 1) {
                            temp.add(arr[k]);
                            count--;
                        }
                    }
                    arr = new int[temp.size()];
                    for (int k = 0; k < temp.size(); k++) {
                        arr[k] = temp.get(k);
                    }
                    list.add(arr);
                }
                in.write(list.size() + "\n");
                for (int[] arr : list) {
                    int len = arr.length;
                    in.write(len + "\n");
                    StringBuilder builder = new StringBuilder();
                    for (int item : arr) {
                        builder.append(item);
                        builder.append(' ');
                    }
                    builder.append("\n");
                    in.write(builder.toString());
                    out.write(test(arr, len) + "\n");
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
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((--T) >= 0) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(test(arr, n));
        }
    }
}
