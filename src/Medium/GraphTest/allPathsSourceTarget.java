package Medium.GraphTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author msc
 * @version 1.0
 * @date 2021/8/25 10:50
 */

/*
* 797. 所有可能的路径
给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）

二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。

译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。



示例 1：



输入：graph = [[1,2],[3],[3],[]]
输出：[[0,1,3],[0,2,3]]
解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
示例 2：



输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
示例 3：

输入：graph = [[1],[]]
输出：[[0,1]]
示例 4：

输入：graph = [[1,2,3],[2],[3],[]]
输出：[[0,1,2,3],[0,2,3],[0,3]]
示例 5：

输入：graph = [[1,3],[2],[3],[]]
输出：[[0,1,2,3],[0,3]]


提示：

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i（即，不存在自环）
graph[i] 中的所有元素 互不相同
保证输入为 有向无环图（DAG）*/



public class allPathsSourceTarget {


    class Node {
        int index;
        List<Integer> next;

        Node(int index, List<Integer> next) {
            this.index = index;
            this.next = next;
        }
    }

    private List<List<Integer>> res;
    private Node[] nodes;
    private int target;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        target = n-1;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                nodes[i].next.add(j);
            }
        }
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        trace(0, list);
        return res;
    }

    public void trace(int index, List<Integer> list) {
        if (index==target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int next : nodes[index].next) {
            list.add(next);
            trace(next, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}
