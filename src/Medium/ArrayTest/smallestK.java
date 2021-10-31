package Medium.ArrayTest;

import java.util.Arrays;
import java.util.Random;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/3 19:51
 */


/*
* 面试题 17.14. 最小K个数
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：

输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
提示：

0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))*/


public class smallestK {

    public int[] smallestK(int[] arr, int k) {
        if (k >= arr.length) return arr;
        int[] res = new int[k];
        if (k == 0) return res;
        quickSort(arr, 0, arr.length - 1, k);
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    private void quickSort(int[] arr, int l, int r, int k) {
        if (l >= r) return;
        Random random = new Random(System.currentTimeMillis());
        int index = l + random.nextInt(r - l + 1);
        int key = arr[index];
        arr[index] = arr[l];
        arr[l] = key;
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] > key) j--;
            arr[i] = arr[j];
            while (i < j && arr[i] < key) i++;
            arr[j] = arr[i];
        }
        arr[i] = key;
        if (i < k) {
            quickSort(arr, i + 1, r, k);
        } else if (i > k) {
            quickSort(arr, l, i - 1, k);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new smallestK().smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
//        Random random = new Random(System.currentTimeMillis());
//        for (int i = 0; i < 10; i++) {
//            System.out.println(1 + random.nextInt(4));
//        }

    }
}
