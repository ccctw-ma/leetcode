package Difficult.TreeTest;


/*124. 二叉树中的最大路径和
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
* */

/**
 * @author 马世臣
 * @// TODO: 2020/6/21  */


//水花只能开在雨天
//
//烟花要绽放在黑夜
//
//雪花多舍不得冬天
//
//像我舍不得和说你再见
public class maxPathSum {


    private int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;
        max=root.val;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root){
        if(root==null) return 0;
        int val=root.val;
        int left=dfs(root.left);
        int right=dfs(root.right);
        int a=val+left;
        int b=val+right;
        int d=Math.max(Math.max(a,b),val);
        max=Math.max(Math.max(d,val+left+right),max);
        return d;
    }





    public static void main(String[] args) {

    }
}
