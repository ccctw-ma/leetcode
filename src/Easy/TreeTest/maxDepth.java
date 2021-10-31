package Easy.TreeTest;


/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/18 104. 二叉树的最大深度 */
public class maxDepth {
    public static int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }else if(root.left==null&&root.right==null){
            return 1;
        }else if(root.left==null||root.right==null){
            return root.left==null?maxDepth(root.right)+1:maxDepth(root.left)+1;
        }else {
            int left=maxDepth(root.left);
            int right=maxDepth(root.right);
            return left>right?left+1:right+1;
        }
    }

    /**
     * public int maxDepth(TreeNode root) {
     *     if (root == null) {
     *       return 0;
     *     } else {
     *       int left_height = maxDepth(root.left);
     *       int right_height = maxDepth(root.right);
     *       return java.lang.Math.max(left_height, right_height) + 1;
     *     }
     *   }
     **/

    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(1);
        TreeNode treeNode2=new TreeNode(2);
        TreeNode treeNode3=new TreeNode(2);
        TreeNode treeNode4=new TreeNode(3);
        TreeNode treeNode5=new TreeNode(4);
        TreeNode treeNode6=new TreeNode(4);
        TreeNode treeNode7=new TreeNode(3);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode2.left=treeNode4;
        treeNode2.right=treeNode5;
        treeNode3.left=treeNode6;
        treeNode3.right=treeNode7;
        System.out.println(maxDepth(treeNode1));
    }
}
