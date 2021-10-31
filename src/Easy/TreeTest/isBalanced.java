package Easy.TreeTest;



/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/19  110. 平衡二叉树*/
public class isBalanced {
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        if(Math.abs(left-right)>1){
            return false;
        }else {
            return isBalanced(root.left)&&isBalanced(root.right);
        }


    }

    /**
     * return root == null || ((Math.abs(depth(root.left) - depth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right));*/

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(3);
        TreeNode treeNode2=new TreeNode(9);
        TreeNode treeNode3=new TreeNode(20);
        TreeNode treeNode4=new TreeNode(15);
        TreeNode treeNode5=new TreeNode(8);

        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode3.left=treeNode4;
        treeNode3.right=treeNode5;
        isBalanced isBalanced=new isBalanced();
        System.out.println(isBalanced.isBalanced(treeNode1));
    }
}
