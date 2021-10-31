package Easy.TreeTest;


/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/19  112. 路径总和*/
public class hasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }else if(sum-root.val==0&&root.left==null&&root.right==null){
            return true;
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

    /**
     * public boolean hasPathSum(TreeNode root, int sum) {
     *     if (root == null)
     *       return false;
     *
     *     LinkedList<TreeNode> node_stack = new LinkedList();
     *     LinkedList<Integer> sum_stack = new LinkedList();
     *     node_stack.add(root);
     *     sum_stack.add(sum - root.val);
     *
     *     TreeNode node;
     *     int curr_sum;
     *     while ( !node_stack.isEmpty() ) {
     *       node = node_stack.pollLast();
     *       curr_sum = sum_stack.pollLast();
     *       if ((node.right == null) && (node.left == null) && (curr_sum == 0))
     *         return true;
     *
     *       if (node.right != null) {
     *         node_stack.add(node.right);
     *         sum_stack.add(curr_sum - node.right.val);
     *       }
     *       if (node.left != null) {
     *         node_stack.add(node.left);
     *         sum_stack.add(curr_sum - node.left.val);
     *       }
     *     }
     *     return false;
     *   }
     **/

    public static void main(String[] args) {
        /*TreeNode treeNode1=new TreeNode(3);
        TreeNode treeNode2=new TreeNode(9);
        TreeNode treeNode3=new TreeNode(20);
        TreeNode treeNode4=new TreeNode(15);
        TreeNode treeNode5=new TreeNode(7);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode3.left=treeNode4;
        treeNode3.right=treeNode5;*/
        TreeNode treeNode1=new TreeNode(1);
        TreeNode treeNode2=new TreeNode(2);
        treeNode1.left=treeNode2;
        System.out.println(new hasPathSum().hasPathSum(treeNode1,3));
    }
}
