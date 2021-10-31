package Easy.TreeTest;


/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *  
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/21 653. 两数之和 IV - 输入 BST */
public class findTarget {

    public boolean findTarget(TreeNode root, int k) {
        if(root==null){
            return false;
        }else if(root.left==null&&root.right==null){
            return false;
        }else if(root.left!=null&&root.right==null){
            return findAnswer(root.left,k-root.val)||findTarget(root.left,k);
        }else if(root.left==null&&root.right!=null){
            return findAnswer(root.right,k-root.val)||findTarget(root.right,k);
        }else if(k>2*root.val){
            return findAnswer(root.right,k-root.val)||findTarget(root.right,k);
        }else if(root.left.val+root.right.val==k){
            return true;
        }
        return false;
    }

    public boolean findAnswer(TreeNode root,int k){
        if(root==null){
            return false;
        }else if(root.val==k){
            return true;
        }else {
            return findAnswer(root.left,k)||findAnswer(root.right,k);
        }
    }

    /**
     * public boolean findTarget(TreeNode root, int k) {
     *         Set < Integer > set = new HashSet();
     *         return find(root, k, set);
     *     }
     *     public boolean find(TreeNode root, int k, Set < Integer > set) {
     *         if (root == null)
     *             return false;
     *         if (set.contains(k - root.val))
     *             return true;
     *         set.add(root.val);
     *         return find(root.left, k, set) || find(root.right, k, set);
     *     }
     **/


    public static void main(String[] args) {

    }
}
