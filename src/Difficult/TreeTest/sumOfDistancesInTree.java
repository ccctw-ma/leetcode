package Difficult.TreeTest;


/*
* 834. Sum of Distances in Tree
An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation:
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/10/6
 * */



public class sumOfDistancesInTree {



    class Node{
        int id;
        List<Integer> nexts;
        public Node(int id){
            this.id=id;
            nexts=new ArrayList<>();
        }
    }

    //dfs method will overtime , we need to use tree dp to solve this problem
    public int[] sumOfDistancesInTree(int N, int[][] edges) {

        List<Node> list=new ArrayList<>();
        for (int i=0;i<N;i++){
            list.add(new Node(i));
        }
        for (int[] arr:edges){
            int a=arr[0];
            int b=arr[1];
            list.get(a).nexts.add(b);
            list.get(b).nexts.add(a);
        }


        int[] sums=new int[N];
        boolean[] visited=new boolean[N];
        for (int i=0;i<N;i++){
            Arrays.fill(visited,false);
            sum=0;
            dfs(1,list,i,visited);
            sums[i]=sum;
        }
        return sums;
    }

    private int sum;
    private int[][] distance;
    private void dfs(int depth,List<Node> list,int index,boolean[] visited){
        visited[index]=true;
        for (int id:list.get(index).nexts){
            if(visited[id]) continue;
            sum+=depth;
            dfs(depth+1,list,id,visited);
        }
    }


//        for (int i=0;i<N;i++){
//            for (int j=0;j<N;j++){
//                if(i==j) continue;
//                //if(distance[i][j]!=0) continue;
//                for (int k=0;k<N;k++){
//                    if(k==i||k==j) continue;
//                    if(distance[i][k]!=0&&distance[k][j]!=0){
//                        int a=distance[i][k];
//                        int b=distance[k][j];
//                        distance[i][j]= distance[i][j]==0 ? a+b:Math.min(distance[i][j],a+b);
//                        distance[j][i]= distance[i][j];
//                    }
//                }
//            }
//        }




    class Solution {
        int[] ans;
        int[] sz;
        int[] dp;
        List<List<Integer>> graph;

        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            ans = new int[N];
            sz = new int[N];
            dp = new int[N];
            graph = new ArrayList<List<Integer>>();
            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<Integer>());
            }
            for (int[] edge: edges) {
                int u = edge[0], v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        public void dfs(int u, int f) {
            sz[u] = 1;
            dp[u] = 0;
            for (int v: graph.get(u)) {
                if (v == f) {
                    continue;
                }
                dfs(v, u);
                dp[u] += dp[v] + sz[v];
                sz[u] += sz[v];
            }
        }

        public void dfs2(int u, int f) {
            ans[u] = dp[u];
            for (int v: graph.get(u)) {
                if (v == f) {
                    continue;
                }
                int pu = dp[u], pv = dp[v];
                int su = sz[u], sv = sz[v];

                dp[u] -= dp[v] + sz[v];
                sz[u] -= sz[v];
                dp[v] += dp[u] + sz[u];
                sz[v] += sz[u];

                dfs2(v, u);

                dp[u] = pu;
                dp[v] = pv;
                sz[u] = su;
                sz[v] = sv;
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{1,3},{5,0},{2,5},{6,2},{4,2},{6,3}};
        System.out.println(Arrays.toString(new sumOfDistancesInTree().sumOfDistancesInTree(7, arr)));
    }
}
