package Medium.TwoPointersTest;


/*
* 611. 有效三角形的个数
给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

示例 1:

输入: [2,2,3,4]
输出: 3
解释:
有效的组合是:
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
注意:

数组长度不超过1000。
数组里整数的范围为 [0, 1000]。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * 21.8.4
 */

public class triangleNumber {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0, n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 0) continue;
            for (int j = i + 1; j < n - 1; j++) {
                int a = nums[i], b = nums[j], c = a + b;
                if (c <= nums[j + 1]) continue;
                int l = j + 1, r = n - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (nums[mid] < c) {
                        l = mid + 1;
                    } else if (nums[mid] > c) {
                        r = mid - 1;
                    } else {
                        if (l == r) break;
                        r = mid;
                    }
                }
                count += l - j - 1;
            }
        }
        return count;
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);

        int res = 0;

        // 从大到小枚举
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new triangleNumber().triangleNumber(new int[]{1,2,3,4,6,9,9}));
    }
}
