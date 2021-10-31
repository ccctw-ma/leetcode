package Easy.TreeTest;


/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/19  111. 二叉树的最小深度*/
public class minDepth {

    public static int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }else if(root.left==null&&root.right==null){
            return 1;
        }else if(root.left==null){
            return minDepth(root.right)+1;
        }else if(root.right==null){
            return minDepth(root.left)+1;
        }
        return Math.min(minDepth(root.left),minDepth(root.right))+1;

    }

    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(3);
        TreeNode treeNode2=new TreeNode(9);
        TreeNode treeNode3=new TreeNode(20);
        TreeNode treeNode4=new TreeNode(15);
        TreeNode treeNode5=new TreeNode(7);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode3.left=treeNode4;
        treeNode3.right=treeNode5;
    }
}
