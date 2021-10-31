package Easy.ArrayTest;



/**
 * 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
 *
 * 至少有一个空座位，且至少有一人坐在座位上。
 *
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 *
 * 返回他到离他最近的人的最大距离。
 *
 * 示例 1：
 *
 * 输入：[1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。 
 * 示例 2：
 *
 * 输入：[1,0,0,0]
 * 输出：3
 * 解释： 
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
 * 这是可能的最大距离，所以答案是 3 。
 * 提示：
 *
 * 1 <= seats.length <= 20000
 * seats 中只含有 0 和 1，至少有一个 0，且至少有一个 1。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/2/2 849. 到最近的人的最大距离 */

public class maxDistToClosest {


    public int maxDistToClosest(int[] seats) {
        int i=0,max=0;
        while (i<seats.length){
            while (i<seats.length&&seats[i]==1){
                i++;
            }
            int j=i;
            while (j<seats.length&&seats[j]==0){
                j++;
            }
            int n;
            if(i==0||j==seats.length){
                n=j-i;
            }else {
                n=(j-i+1)>>1;
            }
            if(n>max) max=n;
            i=j;
        }
        return max;
    }

    //最开始就求出两种极端只做最后一次判断，避免了过多的进行极端判断，节省了时间
    public int maxDistToClosest2(int[] seats) {
        int countleft=0,countright=0,count=0,max=-1;
        int i=0,j=seats.length-1;
        while(seats[i]==0){
            countleft++;
            i++;
        }

        while(seats[j]==0){
            countright++;
            j--;
        }

        for(int k=i+1;k<j;k++){
            if(seats[k]==0){
                count++;
                if(count>max)max=count;
            }
            else count=0;


        }
        return Math.max(Math.max(countleft,countright),(max+1)/2);

    }

    public static void main(String[] args) {

    }
}
