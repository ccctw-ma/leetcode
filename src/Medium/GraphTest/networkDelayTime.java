package Medium.GraphTest;


/*
* 743. 网络延迟时间
有 N 个网络节点，标记为 1 到 N。

给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。

现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。



示例：



输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
输出：2


注意:

N 的范围在 [1, 100] 之间。
K 的范围在 [1, N] 之间。
times 的长度在 [1, 6000] 之间。
所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2020/6/16  */


public class networkDelayTime {



    //Floyd算法
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] graph=new int[N][N];
        for (int[] arr:graph) Arrays.fill(arr,-1);
        for (int[] arr:times){
            int u=arr[0]-1;
            int v=arr[1]-1;
            int w=arr[2];
            graph[u][v]=w;
        }

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                for (int k=0;k<N;k++){
                    if(j==k||i==j||i==k) continue;
                    if(graph[j][i]!=-1&&graph[i][k]!=-1){
                        int sum=graph[j][i]+graph[i][k];
                        graph[j][k]=(graph[j][k]==-1?sum: Math.min(sum,graph[j][k]));
                    }
                }
            }
        }

        int time=0;
        for (int i=0;i<N;i++){
            if(i==K-1) continue;
            if(graph[K-1][i]==-1) return -1;
            time=Math.max(time,graph[K-1][i]);
        }
        return time;
    }



    //迪杰斯特拉算法
    public static int networkDelayTime2(int[][] times, int N, int K) {
        // 构建邻接表，用于存放各个点到各个点的距离
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = -1;
            }
        }
        // 遍历times填充邻接表
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }

        // 存放 K 到各个点的最短路径，最大的那个最短路径即为结果
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);

        // 初始化 distance 为 K 到各个节点的距离
        for (int i = 1; i <= N; i++) {
            distance[i] = graph[K][i];
        }
        // K到达K本身的节点初始化为 0
        distance[K] = 0;

        // 判断是否找到K到达该点最短路径
        boolean[] visited = new boolean[N + 1];
        visited[K] = true;

        // 遍历除K本身节点之外的所有N-1个节点
        for (int i = 1; i <= N - 1; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = 1;
            // 遍历所有节点，找到离K最近的节点
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && distance[j] != -1 && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minIndex = j;
                }
            }

            // 标记最近距离节点找到
            visited[minIndex] = true;

            // 根据刚刚找到的最短距离节点，通过该节点更新K节点与其他的节点的距离
            for (int j = 1; j <= N; j++) {
                // 如果已更新的最短节点可以到达当前节点
                if (graph[minIndex][j] != -1) {
                    if (distance[j] != -1) {
                        // 取之前路径与当前更新路径的最小值
                        distance[j] = Math.min(distance[j], distance[minIndex] + graph[minIndex][j]);
                    } else {
                        // 该节点是第一次访问，直接更新
                        distance[j] = distance[minIndex] + graph[minIndex][j];
                    }
                }
            }
        }

        int maxDistance = 0;
        // 遍历最大值，如果有节点未被访问，返回 -1，否则返回最大最短路径
        for (int i = 1; i <= N; i++) {
            if (distance[i] == -1) {
                return -1;
            }
            maxDistance = Math.max(distance[i], maxDistance);
        }

        return maxDistance;
    }


    public static void main(String[] args) {
        int[][] arr=new int[][]{
                {2,1,1},{2,3,1},{3,4,1}
        };
        System.out.println(new networkDelayTime().networkDelayTime(arr,4,2));
    }
}
