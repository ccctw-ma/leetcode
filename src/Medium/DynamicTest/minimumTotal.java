package Medium.DynamicTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/3/10  */

public class minimumTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==1) return triangle.get(0).get(0);
        int a=triangle.get(0).get(0);
        triangle.get(1).set(0,triangle.get(1).get(0)+a);
        triangle.get(1).set(1,triangle.get(1).get(1)+a);
        for (int i=2;i<triangle.size();i++){
            for (int j=0;j<triangle.get(i).size();j++){
                int temp=triangle.get(i).get(j);
                if(j==0){
                    triangle.get(i).set(0,temp+Math.min(triangle.get(i-1).get(0),triangle.get(i-1).get(1)));
                }else if(j<triangle.get(i).size()-1) {
                    triangle.get(i).set(j,temp+Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j)));
                }else {
                    triangle.get(i).set(j,temp+triangle.get(i-1).get(j-1));
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int i:triangle.get(triangle.size()-1)){
            if(i<min) min=i;
        }
        return min;
    }


    //从底向上进行查找，妙呀
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp中记录了求第i行时，第i+1的最小路径和
        int[] dp = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + rows.get(j);
            }
        }
        return dp[0];
    }
    
    public static void main(String[] args) {
        List<List<Integer>> triangle=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        list1.add(2);
        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3=new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4=new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        System.out.println(new minimumTotal().minimumTotal(triangle));
    }
}
