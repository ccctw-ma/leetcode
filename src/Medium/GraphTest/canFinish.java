package Medium.GraphTest;


/*
* 207. 课程表
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？



示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。


提示：

输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
1 <= numCourses <= 10^5*/

import java.util.*;

/**
 * @author 马世臣
 * @// TODO: 2020/5/17  */


public class canFinish {



    //bfs进行查找
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph=new HashMap<>();
        int[] points=new int[numCourses];
        for (int[] arr:prerequisites){
            points[arr[0]]++;
            List<Integer> list=graph.containsKey(arr[1])?graph.get(arr[1]):new ArrayList<>();
            list.add(arr[0]);
            graph.put(arr[1],list);
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i=0;i<points.length;i++){
            if(points[i]==0){
                queue.add(i);
            }
        }
        int index=0;
        while (!queue.isEmpty()){
            int course=queue.poll();
            index++;
            List<Integer> des= graph.getOrDefault(course, null);
            if(des!=null){
                for (int j:des){
                    points[j]--;
                    if(points[j]==0)    queue.add(j);
                }
            }
        }
        return index==numCourses;
    }



    //dfs进行拓扑遍历
    private List<List<Integer>> edges;
    private int[] visited;
    private boolean valid = true;

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        //邻接表的建立
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }


    public static void main(String[] args) {
        System.out.println(new canFinish().canFinish(3,new int[][]{{2,0},{2,1}}));
    }
}
