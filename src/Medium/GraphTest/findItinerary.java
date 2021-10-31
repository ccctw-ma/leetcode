package Medium.GraphTest;

import java.util.*;


/*
* 332. 重新安排行程
给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。

说明:

如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
所有的机场都用三个大写字母表示（机场代码）。
假定所有机票至少存在一种合理的行程。
示例 1:

输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
示例 2:

输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/16  */


public class findItinerary {



    /*
    * 这是一道典型的欧拉通路问题
    * 欧拉通路：两个端点，入度与出度差1，其余节点的入度等于出度
    * 欧拉回路：所有节点的入度需要等于出度*/
    private Map<String, PriorityQueue<String>> map = new HashMap<>();

    private List<String> resList = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(src, pq);
            }
            map.get(src).add(dst);
        }
        dfs("JFK");
        return resList;
    }

    private void dfs(String src) {
        PriorityQueue<String> pq = map.get(src);
        while (pq != null && !pq.isEmpty())
            dfs(pq.poll());
        ((LinkedList<String>) resList).addFirst(src);
    }



    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("MUC");
        list.add("LHR");
        List<String> list2=new ArrayList<>();
        list2.add("JFK");
        list2.add("MUC");
        List<String> list3=new ArrayList<>();
        list3.add("SFO");
        list3.add("SJC");
        List<String> list4=new ArrayList<>();
        list4.add("LHR");
        list4.add("SFO");
        List<List<String>> lists=new ArrayList<>();
        lists.add(list);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        System.out.println(new findItinerary().findItinerary(lists));

    }
}
