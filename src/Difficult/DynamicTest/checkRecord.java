package Difficult.DynamicTest;

/**
 * @author msc
 * @version 1.0
 * @date 2021/8/18 10:08
 */


/*
* 552. 学生出勤记录 II
可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
'A'：Absent，缺勤
'L'：Late，迟到
'P'：Present，到场
如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：

按 总出勤 计，学生缺勤（'A'）严格 少于两天。
学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。



示例 1：

输入：n = 2
输出：8
解释：
有 8 种长度为 2 的记录将被视为可奖励：
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
示例 2：

输入：n = 1
输出：3
示例 3：

输入：n = 10101
输出：183236316


提示：

1 <= n <= 105*/


public class checkRecord {

    public int checkRecord(int n) {
        int mod = 1000000007;
        // 0->..P 1->.A.P 2->..A 3->..LL 4->..L 5->.A.L 6->.A.LL
        long[] arr = new long[]{1, 0, 1, 0, 1, 0, 0};
        for (int i = 2; i <= n; i++) {
            long[] temp = new long[7];
            temp[0] = (arr[0] + arr[3] + arr[4]) % mod;
            temp[1] = (arr[1] + arr[2] + arr[5] + arr[6]) % mod;
            temp[2] = temp[0];
            temp[3] = arr[4];
            temp[4] = arr[0];
            temp[5] = (arr[1] + arr[2]) % mod;
            temp[6] = arr[5];
            arr = temp;
        }
        return (int) ((arr[0] + arr[1] + arr[2] + arr[3] + arr[4] + arr[5] + arr[6]) % mod);
    }

    public static void main(String[] args) {
        System.out.println(new checkRecord().checkRecord(10101));
    }
}


/**
 * @apiNote 矩阵快速幂*/
class Solution {
    public int checkRecord(int n) {
        long[][] a = new long[][]{{1}, {1}, {0}, {1}, {0}, {0}};
        long[][] aMatrix = new long[][]{{1, 1, 1, 0, 0, 0}, {1, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0}};
        while (n > 0) {
            int m = n & 1;
            if (m == 1) {
                a = this.multipleMatrix(aMatrix, a);
            }
            aMatrix = this.multipleMatrix(aMatrix, aMatrix);
            n = n >> 1;
        }
        /**
         * 0 A0L0
         * 1 A0L1
         * 2 A0L2
         * 3 A1L0
         * 4 A1L1
         * 5 A1L2
         */
        return (int) a[3][0];

    }

    public long[][] multipleMatrix(long[][] a, long[][] b) {
        long mod = (long) 1e9 + 7;
        long c[][] = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }
}

