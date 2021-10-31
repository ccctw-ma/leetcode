package Match.match189;


/*
* 5415. 圆形靶内的最大飞镖数量 显示英文描述
通过的用户数82
尝试过的用户数196
用户总通过次数88
用户总提交次数341
题目难度Hard
墙壁上挂着一个圆形的飞镖靶。现在请你蒙着眼睛向靶上投掷飞镖。

投掷到墙上的飞镖用二维平面上的点坐标数组表示。飞镖靶的半径为 r 。

请返回能够落在 任意 半径为 r 的圆形靶内或靶上的最大飞镖数。



示例 1：



输入：points = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
输出：4
解释：如果圆形的飞镖靶的圆心为 (0,0) ，半径为 2 ，所有的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 4 。
示例 2：



输入：points = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
输出：5
解释：如果圆形的飞镖靶的圆心为 (0,4) ，半径为 5 ，则除了 (7,8) 之外的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 5 。
示例 3：

输入：points = [[-2,0],[2,0],[0,2],[0,-2]], r = 1
输出：1
示例 4：

输入：points = [[1,2],[3,5],[1,-1],[2,3],[4,1],[1,3]], r = 2
输出：4


提示：

1 <= points.length <= 100
points[i].length == 2
-10^4 <= points[i][0], points[i][1] <= 10^4
1 <= r <= 5000*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/17  */

public class numPoints {

    private double prec = 1e-8;

    public int numPoints(int[][] points, int r) {
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int ans = count(points, points[0][0], points[0][1], r);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }
                double mx = (points[i][0] + points[j][0]) / 2.0;
                double my = (points[i][1] + points[j][1]) / 2.0;
                double dirX = points[i][0] - points[j][0];
                double dirY = points[i][1] - points[j][1];
                double sqr = Math.sqrt(dirX * dirX + dirY * dirY);
                dirX /= sqr;
                dirY /= sqr;
                double h = Math.sqrt(r * r - sqr / 2 * sqr / 2);
                double moveX = -dirY;
                double moveY = dirX;

                double centerX = mx + moveX * h;
                double centerY = my + moveY * h;
                int cnt = count(points, centerX, centerY, r);
                ans = Math.max(ans, cnt);
            }
        }

        return ans;
    }

    public int count(int[][] points, double x, double y, int r) {
        int ans = 0;
        for (int[] pt : points) {
            double dx = x - pt[0];
            double dy = y - pt[1];
            if (dx * dx + dy * dy <= r * r + prec) {
                ans++;
            }
        }
        return ans;
    }
}
