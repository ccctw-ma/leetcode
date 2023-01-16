package Match.match193;

public class TreeAncestor {



//    List<List<Integer>> list;
//    public TreeAncestor(int n, int[] parent) {
//        list=new ArrayList<>();
//        for (int i=0;i<n;i++){
//            List<Integer> integerList=new ArrayList<>();
//            int p=parent[i];
//            while (p!=-1){
//                integerList.add(p);
//                p=parent[p];
//            }
//            list.add(integerList);
//        }
//
//    }
//
//    public int getKthAncestor(int node, int k) {
//        List<Integer> listt= list.get(node);
//        if(k>listt.size()) return -1;
//        return listt.get(k-1);
//    }


    private int[] parent;
    public TreeAncestor(int n, int[] parent) {
        this.parent=parent;
    }

    public int getKthAncestor(int node, int k) {
        if(k==0||node==-1) return node;
        return getKthAncestor(parent[node],k-1);
    }
}
