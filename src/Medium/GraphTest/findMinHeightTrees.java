package Medium.GraphTest;

import java.util.*;



/*
* 310. 最小高度树
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

示例 1:

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

输出: [1]
示例 2:

输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

输出: [3, 4]
说明:

 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
树的高度是指根节点和叶子节点之间最长向下路径上边的数量。*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/13  */


public class findMinHeightTrees {


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] degrees=new int[n];
        boolean[][] graph=new boolean[n][n];
        Map<Integer,List<Integer>> map=new HashMap<>();
        for (int[] arr:edges){
            degrees[arr[0]]++;
            degrees[arr[1]]++;
            graph[arr[0]][arr[1]]=true;
            graph[arr[1]][arr[0]]=true;
        }

        for (int i=0;i<n;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<n;j++){
                if(graph[i][j]){
                    list.add(j);
                }
            }
            map.put(i,list);
        }

        List<Integer> highLevels=new ArrayList<>();
        List<Integer> lowLevels=new ArrayList<>();

        while (true) {
            lowLevels.clear();
            highLevels.clear();
            for (int i:map.keySet()){
                if(degrees[i]<=1) lowLevels.add(i);
                if(degrees[i]>1) highLevels.add(i);
            }
            if(highLevels.size()==0) break;
            for (int i:lowLevels){
                degrees[i]--;
                List<Integer> list=map.get(i);
                map.remove(i);
                for (int j:list){
                    degrees[j]--;
                }
            }
        }
        return lowLevels;

    }


    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }

    public List<Integer> findMinHeightTrees3(int n, int[][] edges) {
        if(n==1) return Collections.singletonList(0);
        int[] connected = new int[n];
        int[] degree = new int[n];

        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            connected[v1] ^= v2;
            connected[v2] ^= v1;

            degree[v1]++;
            degree[v2]++;
        }

        //很巧妙，通过异或来查询与该节点相邻的节点，因为每次查询的都是度数为1节点，所以不用担心异或之后
        //得不到正确结果
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i<degree.length ; i++) {
            if(degree[i] == 1) {
                queue.offer(i);
            }
        }

        while(n > 2 && !queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i<size ; i++) {
                int v = queue.poll();
                n--;
                int v1 = connected[v];
                connected[v1] ^= v;
                degree[v1]--;
                if(degree[v1] == 1) {
                    queue.add(v1);
                }
            }
        }

        List<Integer> result = new ArrayList<>(queue);
        return result;
    }

    public static void main(String[] args) {
        int[][] edges=new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}};
        System.out.println(new findMinHeightTrees().findMinHeightTrees3(6,edges));
    }
}
