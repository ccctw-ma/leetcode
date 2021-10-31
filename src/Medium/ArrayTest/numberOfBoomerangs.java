package Medium.ArrayTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/13 12:01
 */

/*
* 447. 回旋镖的数量
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。


示例 1：

输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
示例 2：

输入：points = [[1,1],[2,2],[3,3]]
输出：2
示例 3：

输入：points = [[1,1]]
输出：0


提示：

n == points.length
1 <= n <= 500
points[i].length == 2
-104 <= xi, yi <= 104
所有点都 互不相同*/


public class numberOfBoomerangs {


    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        if (len < 3) return 0;
        int res = 0;
        int[][] distance = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int d = abs(points, i, j);
                distance[i][j] = d;
                distance[j][i] = d;
            }
        }
        for (int i = 0; i < len; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                map.put(distance[i][j], map.getOrDefault(distance[i][j], 0) + 1);
            }
            for (int size : map.values()) {
                if (size == 1) continue;
                res += (size * (size - 1));
            }
        }
        return res;
    }

    private int abs(int[][] points, int a, int b) {
        int xa = points[a][0];
        int ya = points[a][1];
        int xb = points[b][0];
        int yb = points[b][1];
        return (xb - xa) * (xb - xa) + (yb - ya) * (yb - ya);
    }


    public static void main(String[] args) {
        System.out.println(new numberOfBoomerangs().numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
    }
}
