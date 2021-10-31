package Easy.TreeTest;


/**
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 *
 * 示例 :
 *
 * 输入:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出:
 * 1
 *
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 注意: 树中至少有2个节点。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/20 530. 二叉搜索树的最小绝对差 */
public class getMinimumDifference {

    int min=Integer.MAX_VALUE;
    int prevalue=Integer.MIN_VALUE/2;
    public int getMinimumDifference(TreeNode root) {
        inOrderTraversal(root);
        return min;
    }

    public void inOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        if(Math.abs(root.val-prevalue)<min){
            min=Math.abs(root.val-prevalue);
        }
        prevalue=root.val;
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {

    }
}
