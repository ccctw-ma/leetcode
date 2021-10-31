package Difficult.TreeTest;

import java.util.Arrays;

public class findRedundantDirectedConnection {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n=edges.length;
        DSU dsu=new DSU(n);
        int x=0,y=0;
        int[] in=new int[n+1];
        int[] out=new int[n+1];
        for (int[] arr:edges){
            int a=arr[0];
            int b=arr[1];
            out[a]++;
            in[b]++;
            dsu.Union(a,b);
        }
        for (int i=n-1;i>=0;i--){
            int a=edges[i][0];
            int b=edges[i][1];
            if(in[b]==1){
                
            }
        }

        return new int[]{x,y};
    }

    class DSU {

        private int[] parent;
        public DSU(int n){
            parent=new int[n+1];
            for (int i=1;i<=n;i++){
                parent[i]=i;
            }
        }

        public int find(int x){
            while (x!=parent[x]){
                parent[x]=parent[parent[x]];
                x=parent[x];
            }
            return x;
        }

        public void Union(int x,int y){
            int px=find(x);
            int py=find(y);
            parent[px]=py;
        }

        public boolean isConnected(int x,int y) {
            return find(x)==find(y);
        }
    }


    public static void main(String[] args) {
        int[][] edges=new int[][]{{2,1},{3,1},{4,2},{1,4}};
        System.out.println(Arrays.toString(new findRedundantDirectedConnection().findRedundantDirectedConnection(edges)));
    }
}
