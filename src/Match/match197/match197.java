package Match.match197;

import java.util.*;

public class match197 {


    public int numSub(String s) {
        long count=0;
        int index=0;
        int mod=1000_000_007;
        while (index<s.length()){
            while (s.charAt(index)=='0') index++;
            if(index<s.length()){
                int j=index;
                while (j<s.length()&&s.charAt(j)=='1') j++;
                long n=j-index;
                long a=(n*n+n)/2;
                count+=a%mod;
                index=j;
            }
        }
        return (int) count;
    }


    class Node{
        int index;
        double cost;

        public Node(int index,double cost){
            this.index=index;
            this.cost=cost;
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Node>> map=new HashMap<>();
        for (int i=0;i<n;i++) map.put(i,new ArrayList<>());
        for (int i=0;i<edges.length;i++){
            int a=edges[i][0];
            int b=edges[i][1];
            double cost=succProb[i];
            map.get(a).add(new Node(b,cost));
            map.get(b).add(new Node(a,cost));
        }

        //记录节点是否已经访问过
        boolean[] visit=new boolean[n];
        //记录目前已知的到目的节点的概率
        double[] dis=new double[n];
         //创建优先队列（概率大的优先）
        PriorityQueue<Node> queue=new PriorityQueue<>((o1, o2) -> Double.compare(o2.cost,o1.cost));

        queue.offer(new Node(start,1.0));
        while (!queue.isEmpty()){
            Node root=queue.poll();
            visit[root.index]=true;
            if(root.index==end) return root.cost;
            for (Node node:map.get(root.index)){
                int to=node.index;
                double cost=node.cost;
                if(!visit[to]&&root.cost*cost>dis[to]){
                    dis[to]=root.cost*cost;
                    queue.offer(new Node(to,dis[to]));
                }
            }

        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{0,1},{1,2},{0,2}};
        double[] a=new double[]{0.5,0.5,0.2};
        System.out.println(new match197().maxProbability(3,arr,a,0,2));
    }
}
