package Easy.GraphTest;



/**
 *有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 *
 * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 *
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 示例 2：
 *
 * 输入：N = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 *
 * 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *  
 *
 * 提示：
 *
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * 不存在花园有 4 条或者更多路径可以进入或离开。
 * 保证存在答案。
 **/

import java.util.*;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/25 1042. 不邻接植花 */
public class gardenNoAdj {

    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, List<Integer>> graph=new HashMap<>();
        for (int i=1;i<=N;i++)
            graph.put(i,new ArrayList<>());
        for (int[] path:paths){
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }

        int[] colors=new int[N];
        int[] type=new int[]{1,2,3,4};
        Set<Integer> set=new HashSet<>();
        for (int i:type) set.add(i);
        for (int i=0;i<N;i++){
            List<Integer> neighbours=graph.get(i+1);
            Set<Integer> temp=new HashSet<>(set);
            for (int n:neighbours){
                if(colors[n-1]!=0)
                    temp.remove(colors[n-1]);
            }
            colors[i]=temp.iterator().next();
        }
        return colors;
    }



    //不使用map,使用数组进行替代
    public int[] gardenNoAdj2(int N, int[][] paths) {
        int[] result = new int[N];
        if (null == paths || paths.length == 0 || paths[0].length == 0) {
            for (int i = 0; i < N; i++) {
                result[i] = 1;
            }
            return result;
        }
        int[][] grap = new int[N + 1][4];
        for (int[] path : paths) {
            int x = path[0];
            int y = path[1];
            grap[x][++grap[x][0]] = y;
            grap[y][++grap[y][0]] = x;
        }
        for (int i = 1; i < N + 1; i++) {
            boolean []color = new boolean[5];
            for (int j = 1; j <= grap[i][0]; j++) {
                color[result[grap[i][j] - 1]] = true;
            }
            for (int j = 1; j < 5; j++) {
                if (!color[j]) {
                    result[i - 1] = j;
                    break;
                }
            }
        }
        return result;
    }
    /*boolean[][] graph=new boolean[N][N];
        int[] colors=new int[N];
        for (int[] path:paths){
            graph[path[0]][path[1]]=true;
            graph[path[1]][path[0]]=true;
        }

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                for (int k=0;k<N;k++){
                    if(i==j||j==k||i==k) continue;
                    if(graph[j][k]) continue;
                    if(graph[j][i]&&graph[i][k]){
                        graph[j][k]=true;
                    }
                }
            }
        }

        for (int i=0;i<N;i++){

        }
        return colors;*/

    public static void main(String[] args) {
//        Set<Integer> set=new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        set.add(4);
//        for (Iterator<Integer> iterator=set.iterator();iterator.hasNext();){
//            System.out.println(iterator.next());
//        }
//        System.out.println(set.iterator().next());
        int[][] paths=new int[][]{{1,2},{3,4}};
        System.out.println(Arrays.toString(new gardenNoAdj().gardenNoAdj(4, paths)));
        Set<Integer> set=new HashSet<>(Arrays.asList(1,2,3,4));
        System.out.println(set);
    }
}
