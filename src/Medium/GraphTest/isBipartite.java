package Medium.GraphTest;

import java.util.HashSet;
import java.util.Set;

public class isBipartite {


    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        boolean[] visit=new boolean[n];
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<n;i++){
            if(visit[i]) continue;
            if(graph[i].length!=0){
                visit[i]=true;
                for (int j=0;j<graph[i].length;j++){
                    set.add(graph[i][j]);
                }
                while (!set.isEmpty()){
                    Set<Integer> temp=new HashSet<>();
                    for (int ii:set){
                        visit[ii]=true;
                        for (int j=0;j<graph[ii].length;j++){
                            int next=graph[ii][j];
                            if(set.contains(next)) return false;
                            if(!visit[next]){
                                temp.add(next);
                            }
                        }
                    }
                    set=new HashSet<>(temp);
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        int[][] arr=new int[][]{{1,3},{0,2},{1,3},{0,2}};
        System.out.println(new isBipartite().isBipartite(arr));
    }
}
