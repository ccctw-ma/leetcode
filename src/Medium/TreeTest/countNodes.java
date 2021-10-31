package Medium.TreeTest;


/*
* 给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6
*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/31
 * */


public class countNodes {


    //巧用完全二叉树的特性
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int left = getDepth(root.left);
        int right = getDepth(root.right);

        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }

    private int getDepth(TreeNode r) {
        int depth = 0;
        while (r != null) {
            r = r.left;
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {

    }
}
