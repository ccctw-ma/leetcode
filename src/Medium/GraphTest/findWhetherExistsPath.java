package Medium.GraphTest;

import java.util.*;


/*
* 面试题 04.01. 节点间通路
节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。

示例1:

 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 输出：true
示例2:

 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 输出 true
提示：

节点数量n在[0, 1e5]范围内。
节点编号大于等于 0 小于 n。
图中可能存在自环和平行边。*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/4  */


public class findWhetherExistsPath {


    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int[] arr:graph){
            List<Integer> list=map.getOrDefault(arr[0],new ArrayList<>());
            list.add(arr[1]);
            map.put(arr[0],list);
        }
        boolean[] visited= new boolean[n];
        visited[start]=true;
        Deque<Integer> deque=new ArrayDeque<>();
        deque.add(start);
        while (!deque.isEmpty()){
            int t=deque.poll();
            if(!map.containsKey(t)) continue;
            for (int i:map.get(t)){
                if(i==target) return true;
                if(!visited[i]){
                    visited[i]=true;
                    deque.add(i);
                }
            }
        }
        return false;

    }
    public static void main(String[] args) {

    }
}
