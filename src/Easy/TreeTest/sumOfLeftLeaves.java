package Easy.TreeTest;


/**
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/20 404. 左叶子之和 */
public class sumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null){
            return 0;
        }else if(root.left==null&&root.right==null){
            return 0;
        }else if(root.left!=null&&(root.left.left==null&&root.left.right==null)){
            return root.left.val+sumOfLeftLeaves(root.right);
        }else {
            return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
        }
    }


    public static void main(String[] args) {
        TreeNode t1=new TreeNode(6);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(8);
        TreeNode t4=new TreeNode(0);
        TreeNode t5=new TreeNode(4);
        TreeNode t6=new TreeNode(7);
        TreeNode t7=new TreeNode(9);
        TreeNode t8=new TreeNode(3);
        TreeNode t9=new TreeNode(5);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        t5.left=t8;
        t5.right=t9;
        System.out.println(new sumOfLeftLeaves().sumOfLeftLeaves(t1));
    }
}
