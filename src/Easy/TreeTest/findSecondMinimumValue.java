package Easy.TreeTest;


/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/21  671. 二叉树中第二小的节点*/
public class findSecondMinimumValue {

    long min=Long.MAX_VALUE;
    long secmin=Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if(root.left==null&&root.right==null){
            return -1;
        }
        orderTraversal(root);
        if(secmin!=Long.MAX_VALUE){
            return (int) secmin;
        }
        return -1;
    }

    public void orderTraversal(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        if(treeNode.val<min){
            min=treeNode.val;
        }else if(treeNode.val>min&&treeNode.val<=secmin){
            secmin=treeNode.val;
        }
        orderTraversal(treeNode.left);
        orderTraversal(treeNode.right);
    }

    public static void main(String[] args) {
        System.out.println((long) Integer.MAX_VALUE+1);
    }
}
