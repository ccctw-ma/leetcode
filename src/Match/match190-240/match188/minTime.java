package Match.match188;

import java.util.ArrayList;
import java.util.List;

public class minTime {

//    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
//        int count=0,sum=0;
//        for(Boolean b:hasApple){
//            if(b)   count++;
//        }
//        List<List<Integer>> list=new ArrayList<>();
//        for (int i=0;i<n;i++){
//            list.add(new ArrayList<>());
//        }
//        for (int[] arr:edges){
//            int a=arr[0],b=arr[1];
//            list.get(a).add(b);
//            list.get(b).add(a);
//        }
//        if(count==0) return sum;
//        int from=0;
//        if(hasApple.get(from)){
//            hasApple.set(from,false);
//            count--;
//        }
//        while (count>0){
//            int[] ans=search(from,list,hasApple);
//            sum+=ans[0];
//            from=ans[1];
//            hasApple.set(from,false);
//            count--;
//        }
//        hasApple.set(0,true);
//        int[] ans=search(from,list,hasApple);
//        sum+=ans[0];
//        return sum;
//    }
//
//
//
//    private int[] search(int from,List<List<Integer>> list,List<Boolean> hasApple){
//        Queue<Integer> queue=new LinkedList<>();
//        queue.addAll(list.get(from));
//        int length=0,tar = 0;
//        boolean flag=true;
//        while (flag){
//            int n=queue.size();
//            length++;
//            for (int i=0;i<n;i++){
//                int temp=queue.poll();
//                if(hasApple.get(temp)){
//                    tar=temp;
//                    flag=false;
//                    break;
//                }
//                queue.addAll(list.get(temp));
//            }
//        }
//        return new int[]{length,tar};
//    }



    //只要知道要经过多少个点，出去顶点后点的个数乘以2就是答案
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].hasApple = hasApple.get(i);
        }
        for (int[] e : edges) {
            Node a = nodes[e[0]];
            Node b = nodes[e[1]];
            a.next.add(b);
            b.next.add(a);
        }

        dfs(nodes[0], null);
        int ans = count(nodes[0], null);
        return ans;
    }

    public void dfs(Node root, Node p) {
        for (Node node : root.next) {
            if (node == p) {
                continue;
            }
            dfs(node, root);
            root.hasApple = root.hasApple || node.hasApple;
        }
    }

    public int count(Node root, Node p) {
        int ans = 0;
        if (root.hasApple && p != null) {
            ans += 2;
        }
        for (Node node : root.next) {
            if (node == p) {
                continue;
            }
            ans += count(node, root);
        }

        return ans;
    }

    class Node {
        List<Node> next = new ArrayList<>();
        boolean hasApple;
    }





    public static void main(String[] args) {
        List<Boolean> booleans=new ArrayList<>();
        booleans.add(true);
        booleans.add(true);
        booleans.add(true);
        booleans.add(true);
        System.out.println(new minTime().minTime(4,new int[][]{{0,1},{1,2},{0,3}},booleans));
    }
}
