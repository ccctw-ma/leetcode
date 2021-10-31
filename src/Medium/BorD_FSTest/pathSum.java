package Medium.BorD_FSTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/16  */

public class pathSum {

    private List<List<Integer>> lists;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return lists;
        this.lists=new ArrayList<>();
        dfs(root,sum,new ArrayList<>());
        return lists;
    }

    private void dfs(TreeNode root,int sum,List<Integer> list){
        if(root==null) return;
        if(root.left==null&&root.right==null&&sum-root.val==0){
            list.add(root.val);
            lists.add(list);
        }else {
            List<Integer> temp=new ArrayList<>(list);
            temp.add(root.val);
            dfs(root.left,sum-root.val,temp);
            dfs(root.right,sum-root.val,temp);
        }
    }


    //回溯法
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, result);
        return result;
    }
    public void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result){
        if(root.left==null && root.right==null){
            if(sum == root.val){
                List<Integer> p = new ArrayList<>(path);
                p.add(sum);
                result.add(p);
                return;
            }
            else return;
        }
        path.add(root.val);
        if(root.left != null)dfs(root.left, sum-root.val, path, result);
        if(root.right!= null)dfs(root.right,sum-root.val, path, result);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {

    }
}
