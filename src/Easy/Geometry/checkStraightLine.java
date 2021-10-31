package Easy.Geometry;


/**
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 *
 *
 *
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 **/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 马世臣 
 * @ TODO: 2020/1/26 1232. 缀点成线 */

public class checkStraightLine {

    public boolean checkStraightLine(int[][] coordinates) {
        Arrays.sort(coordinates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        double slope;
        if((coordinates[1][0]-coordinates[0][0])==0){
            slope=Double.MAX_VALUE;
        }else {
            slope=(coordinates[1][1]-coordinates[0][1])/(coordinates[1][0]-coordinates[0][0]);
        }
        for (int i=1;i<coordinates.length-1;i++){
            double temp;
            if((coordinates[i+1][0]-coordinates[i][0])==0){
                temp=Double.MAX_VALUE;
            }else {
                temp=(coordinates[i+1][1]-coordinates[i][1])/(coordinates[i+1][0]-coordinates[i][0]);
            }
            if(temp!=slope){
                return false;
            }
        }
        return true;
    }

    public boolean checkStraightLine2(int[][] coordinates) {
        if (coordinates == null || coordinates.length < 3) {
            return true;
        }
        float rat = (float) 1.0 * (coordinates[coordinates.length - 1][1] - coordinates[0][1])
                / (coordinates[coordinates.length - 1][0] - coordinates[0][0]);
        for (int i = 1; i < coordinates.length; i++) {
            if ((float) 1.0 * (coordinates[i][1] - coordinates[0][1])
                    / (coordinates[i][0] - coordinates[0][0]) != rat) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] array=new int[][]{{1,2},{2,3}};
        System.out.println(new checkStraightLine().checkStraightLine(array));
    }
    
}
