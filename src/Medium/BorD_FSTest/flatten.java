package Medium.BorD_FSTest;





/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/17
 * */

public class flatten {



    //后序遍历，只需要一个辅助空间即可
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right != null) root = root.right;
        root.right = tmp;
    }


    //记录右子树的头结点，放着接，高效，牛逼
    private TreeNode pre=null;
    public void flatten2(TreeNode root) {
        if(root == null){
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    public static void main(String[] args) {

    }
}
