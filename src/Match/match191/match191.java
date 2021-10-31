package Match.match191;

import java.util.*;

public class match191 {


    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long max=Long.MIN_VALUE;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int x,y,a=horizontalCuts.length,b=verticalCuts.length;
        x=Math.max(horizontalCuts[0],h-horizontalCuts[a-1]);
        y=Math.max(verticalCuts[0],verticalCuts[b-1]);
        for (int i=1;i<a;i++)   x=Math.max(x,horizontalCuts[i]-horizontalCuts[i-1]);
        for (int i=1;i<b;i++)   y=Math.max(y,verticalCuts[i]-verticalCuts[i-1]);
        max=Math.max(max,(long) y*(long) x);
        return (int)(max%1_000_000_007);
    }


    /*
    * int a=horizontalCuts.length,b=verticalCuts.length;
        for (int i=0;i<=a;i++){
            for (int j=0;j<=b;j++){
                int x1=(i==a?h:horizontalCuts[i]);
                int y1=(j==b?w:verticalCuts[j]);
                int x2=(i-1<0?0:horizontalCuts[i-1]);
                int y2=(j-1<0?0:verticalCuts[j-1]);
                max=Math.max(max,(x1-x2)*(y1-y2));
            }
        }*/
    /*
    * Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int hl=horizontalCuts.length+2, vl=verticalCuts.length+2;
        int[][][] dp=new int[hl][vl][2];
        dp[hl-1][0][0]=h;
        dp[0][vl-1][1]=w;
        dp[hl-1][vl-1][0]=h;
        dp[hl-1][vl-1][1]=w;
        for (int i=1;i<hl-1;i++){
            dp[i][0][0]=horizontalCuts[i-1];
            dp[i][vl-1][0]=horizontalCuts[i-1];
            dp[i][vl-1][1]=w;
        }
        for (int i=1;i<vl-1;i++){
            dp[0][i][1]=verticalCuts[i-1];
            dp[hl-1][i][1]=verticalCuts[i-1];
            dp[hl-1][i][0]=h;
        }
        for (int i=1;i<hl-1;i++){
            for (int j=1;j<vl-1;j++){
                dp[i][j][0]=horizontalCuts[i-1];
                dp[i][j][1]=verticalCuts[j-1];
            }
        }

        for (int i=1;i<hl;i++){
            for (int j=1;j<vl;j++){
                int a=dp[i][j][0];
                int b=dp[i][j][1];
                int c=dp[i-1][j-1][0];
                int d=dp[i-1][j-1][1];
                int x=a-c,y=b-d;
                max=Math.max(max,x*y);
            }
        }
*/


    public int minReorder(int n, int[][] connections) {
        int ans = 0;
        ArrayList <Integer> graph[] = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList();
        for(int[] edge : connections)
        {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Queue <Integer> q = new LinkedList();
        q.add(0);
        boolean visited[] = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        visited[0] = true;
        while(!q.isEmpty())
        {
            int cur = q.poll();
            for(int nei : graph[cur])
            {
                if(visited[nei]) continue;
                q.add(nei);
                visited[nei] = true;
                parent[nei] = cur;
            }
        }
        for(int[] edge : connections)
        {
            if(parent[edge[0]] != edge[1])
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(new match191().minReorder(6,new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}}));

        List<Integer> list=new ArrayList<>();
        list.add(0);
        int[] nums=new int[]{1,2,3,4,5};
    }
}
