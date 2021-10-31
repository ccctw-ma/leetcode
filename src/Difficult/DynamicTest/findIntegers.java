package Difficult.DynamicTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/11 14:30
 */

/*
* 600. 不含连续1的非负整数
给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。

示例 1:

输入: 5
输出: 5
解释:
下面是带有相应二进制表示的非负整数<= 5：
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
说明: 1 <= n <= 109*/


public class findIntegers {

    public int findIntegers(int n) {
        if (n == 1) return 2;
        //保留 one的结果
        int count = 2;
        int len = Integer.toBinaryString(n).length();
        List<Integer> ones = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();
        ones.add(1);
        zeros.add(0);
        for (int i = 1; i < len - 1; i++) {
            int add = 1 << i;
            List<Integer> oneTemp = new ArrayList<>();
            List<Integer> zeroTemp = new ArrayList<>();
            for (int num : zeros) {
                oneTemp.add(add + num);
            }
            zeroTemp.addAll(ones);
            zeroTemp.addAll(zeros);
            ones = new ArrayList<>(oneTemp);
            zeros = new ArrayList<>(zeroTemp);
            count += ones.size();
        }
        int add = 1 << (len - 1);
        for (int zero : zeros) {
            if (zero + add <= n) count++;
        }
        return count;
    }

    public int findIntegers2(int n) {
        if (n == 1) return 2;
        //保留 one的结果
        int count = 2;
        int len = Integer.toBinaryString(n).length();
        int zero = 1, one = 1;
        for (int i = 1; i < len - 1; i++) {
            int oneTemp = zero;
            zero = zero + one;
            one = oneTemp;
            count += one;
        }
        List<Integer> ones = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();
        zeros.add(1 << (len - 1));
        count++;
        for (int i = len - 3; i >= 0; i--) {
            int add = 1 << i;
            List<Integer> oneTemp = new ArrayList<>();
            List<Integer> zeroTemp = new ArrayList<>();
            for (int num : zeros)
                if(add+num<=n) oneTemp.add(add+num);
            zeroTemp.addAll(ones);
            zeroTemp.addAll(zeros);
            ones = new ArrayList<>(oneTemp);
            zeros = new ArrayList<>(zeroTemp);
            count += ones.size();
        }
        return count;
    }

    //这两个都超时了，不行

    public int findIntegers3(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new findIntegers().findIntegers3(10));
    }
}
