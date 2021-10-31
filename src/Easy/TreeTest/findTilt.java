package Easy.TreeTest;


/**
 * 给定一个二叉树，计算整个树的坡度。
 *
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 *
 * 整个树的坡度就是其所有节点的坡度之和。
 *
 * 示例:
 *
 * 输入: 
 *          1
 *        /   \
 *       2     3
 * 输出: 1
 * 解释: 
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 * 注意:
 *
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/21 563. 二叉树的坡度 */

public class findTilt {
    
    public int findTilt(TreeNode root) {
        if(root==null){
            return 0;
        }else if(root.left==null&&root.right==null){
            return 0;
        }else {
            return Math.abs(getSum(root.left)-getSum(root.right))+findTilt(root.left)+findTilt(root.right);
        }
    }

    public int getSum(TreeNode root){
        if(root==null){
            return 0;
        }else {
            return root.val+getSum(root.left)+getSum(root.right);
        }
    }


    /**
     * public class Solution {
     *     int tilt=0;
     *     public int findTilt(TreeNode root) {
     *         traverse(root);
     *         return tilt;
     *     }
     *     public int traverse(TreeNode root)
     *     {
     *         if(root==null )
     *             return 0;
     *         int left=traverse(root.left);
     *         int right=traverse(root.right);
     *         tilt+=Math.abs(left-right);
     *         return left+right+root.val;
     *     }
     * }
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
    }
}
