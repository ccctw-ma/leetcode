package Easy.MathTest;


/**
 * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
 *
 * 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[[1,1],[2,3],[3,2]]
 * 输出：true
 * 示例 2：
 *
 * 输入：[[1,1],[2,2],[3,3]]
 * 输出：false
 *  
 *
 * 提示：
 *
 * points.length == 3
 * points[i].length == 2
 * 0 <= points[i][j] <= 100
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 1037. 有效的回旋镖 */

public class isBoomerang {


    public boolean isBoomerang(int[][] points) {
        /*Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        if(points[0][0]==points[1][0]&&points[1][0]==points[2][0]) return false;
        if(points[0][1]==points[1][1]&&points[1][1]==points[2][1]) return false;*/
        return (points[1][1]-points[0][1])*(points[2][0]-points[1][0])!=(points[2][1]-points[1][1])*(points[1][0]-points[0][0]);
    }
    
    public static void main(String[] args) {

    }
}
