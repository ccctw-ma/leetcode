package Medium.TreeTest;

/*
* 652. 寻找重复的子树
给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

两棵树重复是指它们具有相同的结构以及相同的结点值。

示例 1：

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
下面是两个重复的子树：

      2
     /
    4
和

    4
因此，你需要以列表的形式返回上述重复子树的根结点。*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 马世臣
 * @// TODO: 2021/3/13
 * */


public class findDuplicateSubtrees {


//    //超时了，暴力解法
//    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        List<TreeNode> ans = new ArrayList<>();
//        Deque<TreeNode> deque = new ArrayDeque<>();
//        if(root==null) return ans;
//        List<TreeNode> list = new ArrayList<>();
//        while (root!=null||!deque.isEmpty()){
//            while (root!=null){
//                deque.add(root);
//                list.add(root);
//                root = root.left;
//            }
//            root = deque.pop().right;
//        }
//        Set<Integer> set = new HashSet<>();
//        for (int i=1;i<list.size();i++){
//            for (int j=i-1;j>=0;j--){
//                if(equals(list.get(i),list.get(j))){
//                    if(!set.contains(i)&&!set.contains(j)){
//                        ans.add(list.get(i));
//                    }
//                    set.add(i);
//                    set.add(j);
//                    break;
//                }
//            }
//        }
//        return ans;
//    }
//
//    private boolean equals(TreeNode left, TreeNode right){
//        if(left==null&&right==null) return true;
//        if(left==null&&right!=null) return false;
//        if(left!=null&&right==null) return false;
//        if(left.val!=right.val) return false;
//        return equals(left.left,right.left)&&equals(left.right,right.right);
//    }


    private Map<String,Integer> map;
    private List<TreeNode> ans;
    /*序列化方式*/
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        ans = new ArrayList<>();
        serialization(root);
        return ans;
    }


    private String serialization(TreeNode root){
        if(root==null) return "#";
        //一定要加上“，”因为像11 1 和 1 11 拼一块是一样的
        String s = root.val+","+serialization(root.left)+","+serialization(root.right);
        map.put(s,map.getOrDefault(s,0)+1);
        if(map.get(s)==2)
            ans.add(root);
        return s;
    }




    public static void main(String[] args) {

    }
}
