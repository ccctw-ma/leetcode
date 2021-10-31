package Medium.TreeTest;

import java.util.*;


/*
* 863. All Nodes Distance K in Binary Tree
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.


Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.*/

/**
 * @author 马世臣
 * @// TODO: 2021/3/15  */



public class distanceK {

//    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//        //复杂了且不是对的
//        Set<Integer> ans = new HashSet<>();
//        List<List<Integer>> left = new ArrayList<>();
//        List<List<Integer>> right = new ArrayList<>();
//        Deque<TreeNode> deque = new ArrayDeque<>();
//        if(root.left!=null) deque.add(root.left);
//        int level = 0;
//        int part = 0;
//        int index = 1;
//        while (!deque.isEmpty()){
//            int n = deque.size();
//            List<Integer> list = new ArrayList<>();
//            for (int i=0;i<n;i++){
//                TreeNode temp = deque.pop();
//                list.add(temp.val);
//                if(temp.val==target.val){
//                    level = index;
//                    part = 1;
//                }
//                if(temp.left!=null) deque.add(temp.left);
//                if(temp.right!=null) deque.add(temp.right);
//            }
//            left.add(list);
//            index++;
//        }
//        if(root.right!=null) deque.add(root.right);
//        index = 1;
//        while (!deque.isEmpty()){
//            int n = deque.size();
//            List<Integer> list = new ArrayList<>();
//            for (int i=0;i<n;i++){
//                TreeNode temp = deque.pop();
//                list.add(temp.val);
//                if(temp.val==target.val){
//                    level = index;
//                    part = 2;
//                }
//                if(temp.left!=null) deque.add(temp.left);
//                if(temp.right!=null) deque.add(temp.right);
//            }
//            right.add(list);
//            index++;
//        }
//        if(level==0){
//            if(left.size()>(level+K-1)){
//                ans.addAll(left.get(level+K-1));
//            }
//            if(right.size()>(level+K-1)){
//                ans.addAll(right.get(level+K-1));
//            }
//        }else {
//            //在左边
//            if(part==1){
//                if(left.size()>(level+K-1)){
//                    ans.addAll(left.get(level+K-1));
//                }
//                if(level-K>0){
//                    ans.addAll(left.get(level-K-1));
//                }else if(level==K){
//                    ans.add(root.val);
//                }else if(level-K<0&&right.size()>(Math.abs(level-K)-1)){
//                    ans.addAll(right.get((Math.abs(level-K)-1)));
//                }
//
//            }else if(part==2){
//                if(right.size()>(level+K-1)){
//                    ans.addAll(right.get(level+K-1));
//                }
//                if(level-K>0){
//                    ans.addAll(right.get(level-K-1));
//                }else if(level==K){
//                    ans.add(root.val);
//                }else if(level-K<0&&left.size()>(Math.abs(level-K)-1)){
//                    ans.addAll(left.get((Math.abs(level-K)-1)));
//                }
//            }
//        }
//        return new ArrayList<>(ans);
//    }
// 用map记录每个节点的父节点
    private Map<TreeNode, TreeNode> parents = new HashMap<>();

    private Set<TreeNode> used = new HashSet<>();

    private TreeNode targetNode;

    // 找到目标节点后以目标节点为开始位置向三个方向蔓延
    //妙呀
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        find(root, null, target);
        List<Integer> res = new LinkedList<>();
        dfs(targetNode, res, K);
        return res;
    }

    private void find(TreeNode root, TreeNode parent, TreeNode target) {
        if (null == root) {
            return;
        }
        if (root.val == target.val) {
            targetNode = root;
        }

        parents.put(root, parent);
        find(root.left, root, target);
        find(root.right, root, target);
    }

    private void dfs(TreeNode root, List<Integer> collector, int distance) {
        if (root != null && !used.contains(root)) {
            // 标记为已访问
            used.add(root);
            if (distance <= 0) {
                collector.add(root.val);
                return;
            }
            dfs(root.left, collector, distance - 1);
            dfs(root.right, collector, distance - 1);
            dfs(parents.get(root), collector, distance - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new distanceK().distanceK(null,null,1));
    }
}
