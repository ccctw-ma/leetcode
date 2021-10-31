package Medium.TreeTest;


/*
* 865. 具有所有最深节点的最小子树
给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。

如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。

一个节点的 子树 是该节点加上它的所有后代的集合。

返回能满足 以该节点为根的子树中包含所有最深的节点 这一条件的具有最大深度的节点。



注意：本题与力扣 1123 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/



示例 1：



输入：root = [3,5,1,6,2,0,8,null,null,7,4]
输出：[2,7,4]
解释：
我们返回值为 2 的节点，在图中用黄色标记。
在图中用蓝色标记的是树的最深的节点。
注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
示例 2：

输入：root = [1]
输出：[1]
解释：根节点是树中最深的节点。
示例 3：

输入：root = [0,1,3,null,2]
输出：[2]
解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。


提示：

树中节点的数量介于 1 和 500 之间。
0 <= Node.val <= 500
每个节点的值都是独一无二的。*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**@author 马世臣
 * @// TODO: 2021/3/16  */


public class subtreeWithAllDeepest {

    private Map<TreeNode,TreeNode> map;
    private int max;
    private List<TreeNode> list;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        map = new HashMap<>();
        max = 0;
        list = new ArrayList<>();
        dfs(root,null,0);
        while (list.size()!=1){
            int[] arr = new int[1001];
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode treeNode:list){
                TreeNode p = map.get(treeNode);
                if(arr[p.val]==0){
                    temp.add(p);
                    arr[p.val]=1;
                }
            }
            list.clear();
            list.addAll(temp);
        }
        return list.get(0);
    }

    private void dfs(TreeNode root,TreeNode parent, int height){
        if(root==null) return;
        map.put(root,parent);
        if(height>max){
            list.clear();
            list.add(root);
            max = height;
        }else if(height==max){
            list.add(root);
        }
        dfs(root.left,root,height+1);
        dfs(root.right,root,height+1);
    }

    //第二种解法
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    public Result dfs(TreeNode root){
        if(root==null) return new Result(null,0);
        Result l = dfs(root.left), r = dfs(root.left);
        if(l.dis>r.dis) return new Result(l.node,l.dis+1);
        if(l.dis<r.dis) return new Result(r.node,r.dis+1);
        return new Result(root,l.dis+1);
    }

    class Result{
        TreeNode node;
        int dis;
        public Result(TreeNode node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }











    //第三种解法
    private TreeNode res;
    private int maxDepth;
    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        res = root;
        dfs(root, 0);
        return res;
    }

    private int dfs(TreeNode node, int height) {
        if(node == null) {
            return height;
        }

        int left = dfs(node.left, height+1);
        int right = dfs(node.right, height+1);
        if(left == right && left >= maxDepth) {
            maxDepth = left;
            res = node;
        }
        return Math.max(left, right);
    }




    public static void main(String[] args) {

    }
}
