package Medium.UnionFindTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class minCostConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int n=points.length;
        if(n==1) return 0;
        int[][] graph = new int[n][n];
        //构造出图结构
        for (int i=0;i<n-1;i++){
            for (int j=i+1;j<n;j++){
                int distance = Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                graph[i][j]=distance;
                graph[j][i]=distance;
            }
        }
        DSU dsu=new DSU(n);
        PriorityQueue<edge> heap=new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        int sum=0;
        boolean[] isConnected=new boolean[n];
        Arrays.fill(isConnected,false);
        isConnected[0]=true;
        for (int i=1;i<n;i++){
            heap.add(new edge(0,i,graph[0][i]));
        }
        for (int i=0;i<n-1;i++){
            edge peek=heap.poll();
            while (dsu.isConnected(peek.a,peek.b)){
                peek=heap.poll();
            }
            int origin= isConnected[peek.a] ? peek.a:peek.b;
            int next= isConnected[peek.a] ? peek.b:peek.a;
            sum+=peek.weight;
            dsu.union(origin,next);
            isConnected[next]=true;
            for (int j=0;j<n;j++){
                if(!isConnected[j]){
                    heap.add(new edge(next,j,graph[next][j]));
                }
            }
        }
        return sum;
    }



    class edge{
        int a;
        int b;
        int weight;
        public edge(int a,int b,int weight){
            this.a=a;
            this.b=b;
            this.weight=weight;
        }
    }

    class DSU{

        int[] parent;

        public DSU(int n){
            parent=new int[n];
            for (int i=0;i<n;i++){
                parent[i]=i;
            }
        }

        public int find(int x){
            while (x!=parent[x]){
                parent[x]=parent[parent[x]];
                x=parent[x];
            }
            return parent[x];
        }

        public void union(int x,int y){
            parent[find(y)]=find(x);

        }

        public boolean isConnected(int x,int y){
            return find(x)==find(y);
        }
    }



    public static void main(String[] args) {

    }
}
