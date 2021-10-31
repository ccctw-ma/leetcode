package Easy.MathTest;


/**
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释: 
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 *
 *
 * 注意:
 *
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 *  -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 **/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 马世臣 
 * @// TODO: 2020/2/7 812. 最大三角形面积 */

public class largestTriangleArea {

    public double largestTriangleArea(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int a=(points[0][0]+points[points.length-1][0])/2;
        int b=(points[0][1]+points[points.length-1][1])/2;
        int max=Integer.MIN_VALUE,x=0,y=0;
        for (int i=1;i<points.length-1;i++){
            int temp=(points[i][0]-a)*(points[i][0]-a)+(points[i][1]-b)*(points[i][1]-b);
            if(temp>max){
                max=temp;
                x=points[i][0];
                y=points[i][1];
            }
        }
        double l1=Math.sqrt((points[0][0]-x)*(points[0][0]-x)+(points[0][1]-y)*(points[0][1]-y));
        double l2=Math.sqrt((points[points.length-1][0]-x)*(points[points.length-1][0]-x)+(points[points.length-1][1]-y)*(points[points.length-1][1]-y));
        double l3=Math.sqrt((points[0][0]-points[points.length-1][0])*(points[0][0]-points[points.length-1][0])+(points[0][1]-points[points.length-1][1])*(points[0][1]-points[points.length-1][1]));
        double p=(l1+l2+l3)/2;
        return Math.sqrt(p*(p-l1)*(p-l2)*(p-l3));
    }


    //三重循环，利用鞋带公式求得三角形面积
    public double largestTriangleArea2(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }

    
    public static void main(String[] args) {

    }
}
