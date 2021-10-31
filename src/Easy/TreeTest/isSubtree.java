package Easy.TreeTest;


/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 **/
public class isSubtree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null&&t==null){
            return true;
        }else if(s==null||t==null){
            return false;
        }else if(s.val==t.val){
            return (isEquals(s.left,t.left)&&isEquals(s.right,t.right))||(isSubtree(s.left,t)||isSubtree(s.right,t));
        }else {
            return isSubtree(s.left,t)||isSubtree(s.right,t);
        }
    }

    public boolean isEquals(TreeNode s,TreeNode t){
        if(s==null&&t==null){
            return true;
        }else if(s==null||t==null){
            return false;
        }else if(s.val==t.val){
            return isEquals(s.left,t.left)&&isEquals(s.right,t.right);
        }
        return false;
    }

    private boolean isSub = false;

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        int hash = hash(t);
        int i = hashT(s, hash, t);
        if (i == hash) {//判断这两科树整体是否相等
            return checkNode(s, t);
        }
        return isSub;
    }

    /**检查两个节点是否相等*/
    private boolean checkNode(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return checkNode(node1.left, node2.left) && checkNode(node1.right, node2.right);
    }


    /**对一个节点所对应的树求和*/
    private int hash(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return hash(node.left) + hash(node.right) + node.val;
    }


    /**递归寻找数值总和相等的两棵树，在进行相等比较*/
    private int hashT(TreeNode node, int hash, TreeNode t) {
        if (node == null) {
            return 0;
        }
        int hashCur = hashT(node.left, hash, t) + hashT(node.right, hash, t) + node.val;
        if (hashCur == hash) {
            boolean check = checkNode(node, t);
            if (check) {
                isSub = true;
            }
        }
        return hashCur;
    }

    public static void main(String[] args) {

    }
}
