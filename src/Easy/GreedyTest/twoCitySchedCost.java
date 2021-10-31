package Easy.GreedyTest;



/**
 *
 * 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
 *
 * 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[10,20],[30,200],[400,50],[30,20]]
 * 输出：110
 * 解释：
 * 第一个人去 A 市，费用为 10。
 * 第二个人去 A 市，费用为 30。
 * 第三个人去 B 市，费用为 50。
 * 第四个人去 B 市，费用为 20。
 *
 * 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
 *  
 *
 * 提示：
 *
 * 1 <= costs.length <= 100
 * costs.length 为偶数
 * 1 <= costs[i][0], costs[i][1] <= 1000
 **/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/1/14  1029. 两地调度*/
public class twoCitySchedCost {

    public static int twoCitySchedCost(int[][] costs) {
        int[][] num=new int[costs.length][2];
        for (int i=0;i<costs.length;i++){
            num[i][0]=costs[i][1]-costs[i][0];
            num[i][1]=i;
        }
        Arrays.sort(num, (o1, o2) -> o2[0]-o1[0]);
        int sum=0;
        for (int i=0;i<num.length;i++){
            if(i<num.length/2){
                sum+=costs[num[i][1]][0];
            }else {
                sum+=costs[num[i][1]][1];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] costs=new int[][]{{10,20},{30,200},{400,50},{30,20}};
        System.out.println(twoCitySchedCost(costs));
    }
}
