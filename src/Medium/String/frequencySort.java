package Medium.String;

import java.util.Arrays;

public class frequencySort {


    public String frequencySort(String s) {
        return " ";
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5,2,3,4};
        Arrays.sort(args);
        String[] months = {"January","February","March","April","May","June","July","August","September","October","December"};
        //按字符串长度排序
        //1.
        Arrays.sort(months, (a, b) -> Integer.signum(a.length() - b.length()));
        Arrays.sort(arr, (o1, o2) -> o2-o1);
        System.out.println(Arrays.toString(arr));
    }
}
