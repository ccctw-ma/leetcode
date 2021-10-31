package Medium.ArrayTest;


/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 马世臣
 * @// TODO: 2020/4/16  */

public class merge {

    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Queue<int[]> queue=new LinkedList<>();
        int[] temp=intervals[0];
        for (int i=1;i<intervals.length;i++){
            if(intervals[i][0]<=temp[1]){
                temp[1]=Math.max(intervals[i][1],temp[1]);
            }else {
                queue.offer(temp);
                temp=intervals[i];
            }
        }
        queue.offer(temp);
        int[][] res=new int[queue.size()][2];
        int i=0;
        while (!queue.isEmpty()){
            res[i++]=queue.poll();
        }
        return res;
    }


    //这题用快排会更快些
    public void sort(int[][] intervals,int l,int r){
        if(l>=r) return;
        int p=partition(intervals,l,r);
        sort(intervals,l,p-1);
        sort(intervals,p+1,r);
    }
    public int partition(int[][] intervals,int l,int r){
        int[] temp = intervals[l];
        while(l<r){
            while(l<r){
                if(intervals[r][0]<temp[0]){
                    intervals[l]=intervals[r];
                    break;
                }
                r--;
            }
            while(l<r){
                if(intervals[l][0]>temp[0]){
                    intervals[r]=intervals[l];
                    break;
                }
                l++;
            }
        }
        intervals[l]=temp;
        return l;
    }

    public static void main(String[] args) {

    }
}
