package Medium.GraphTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
* 802. 找到最终的安全状态
在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。

对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。

返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。

该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。



示例 1：

Illustration of graph
输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
输出：[2,4,5,6]
解释：示意图如上。
示例 2：

输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
输出：[4]


提示：

n == graph.length
1 <= n <= 104
0 <= graph[i].length <= n
graph[i] 按严格递增顺序排列。
图中可能包含自环。
图中边的数目在范围 [1, 4 * 104] 内。*/

/**
 * @author 马世臣
 * 21.8.5
 */

public class eventualSafeNodes {


    class Node {
        int index;
        List<Integer> next;
        int state; // -1->isEnd 0->unknow 1->valid 2->unValid

        Node(int index, List<Integer> next, int state) {
            this.index = index;
            this.next = next;
            this.state = state;
        }
    }

    private Node[] nodes;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) {
                nodes[i] = new Node(i, null, -1);
            } else {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < graph[i].length; j++) {
                    list.add(graph[i][j]);
                }
                nodes[i] = new Node(i, list, 0);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].state == -1 || nodes[i].state == 1) {
                res.add(i);
            } else if (nodes[i].state == 0) {
                if (dfs(i, new HashSet<>())) {
                    res.add(i);
                }
            }
        }
        return res;
    }


    private boolean dfs(int index, Set<Integer> hasVisited) {
        if (nodes[index].state == -1) return true;
        if (hasVisited.contains(index) || nodes[index].state == 2) return false;
        hasVisited.add(index);
        for (int i : nodes[index].next) {
            if (!dfs(i, hasVisited)) {
                nodes[index].state = 2;
                return false;
            }
        }
        hasVisited.remove(index);
        nodes[index].state = 1;
        return true;
    }


    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new eventualSafeNodes().eventualSafeNodes(new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}}));
    }
}
