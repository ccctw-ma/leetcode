package Medium.BorD_FSTest;


/*
* 513. 找树左下角的值
给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:

输入:

    2
   / \
  1   3

输出:
1


示例 2:

输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7


注意: 您可以假设树（即给定的根节点）不为 NULL。*/


/**
 * @author 马世臣
 * @// TODO: 2020/8/14
 * */


public class findBottomLeftValue {

    public int findBottomLeftValue(TreeNode root) {
        max=root.val;
        height=0;
        dfs(root,1);
        return max;
    }

    private int max;
    private int height;

    private void dfs(TreeNode root,int h){
        if(root==null) return;
        dfs(root.left,h+1);
        if(h>height){
            max=root.val;
            height=h;
        }
        dfs(root.right,h+1);
    }
    public static void main(String[] args) {

    }
}
