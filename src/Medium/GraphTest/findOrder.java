package Medium.GraphTest;


/*
* 210. 课程表 II
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]]
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 马世臣
 * @// TODO: 2020/5/17  */


public class findOrder {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> lists=new ArrayList<>();
        int[] points=new int[numCourses];
        for (int i=0;i<numCourses;i++){
            lists.add(new ArrayList<>());
        }
        for (int[] arr:prerequisites){
            points[arr[0]]++;
            lists.get(arr[1]).add(arr[0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i=0;i<numCourses;i++){
            if(points[i]==0) queue.offer(i);
        }
        int index=0;
        int[] res=new int[numCourses];
        while (!queue.isEmpty()){
            int des=queue.poll();
            res[index++]=des;
            for (int i:lists.get(des)){
                points[i]--;
                if(points[i]==0){
                    queue.offer(i);
                }
            }
        }
        return index==numCourses?res:new int[0];
    }


    //dfs的解法进行拓扑排序
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        res=new int[numCourses];
        index=numCourses-1;
        flag=true;
        List<List<Integer>> graph=new ArrayList<>();
        for (int i=0;i<numCourses;i++) graph.add(new ArrayList<>());
        for (int[] arr:prerequisites){
            int a=arr[0];
            int b=arr[1];
            graph.get(a).add(b);
        }
        int[] visited=new int[numCourses];
        for (int i=0;i<numCourses&&flag;i++){
            if(visited[i]==0){
                dfs(graph,visited,i);
            }
        }
        return flag?res:new int[0];
    }

    private boolean flag;
    private int index;
    private int[] res;
    private void dfs(List<List<Integer>> graph,int[] visited,int node){
        visited[node]=1;
        for(int i:graph.get(node)){
            if(visited[i]==0){
                dfs(graph,visited,i);
                if(!flag){
                    return;
                }
            }else if(visited[i]==1){
                flag=false;
                return;
            }
        }
        visited[node]=2;
        res[index--]=node;
    }



    public static void main(String[] args) {

    }
}
