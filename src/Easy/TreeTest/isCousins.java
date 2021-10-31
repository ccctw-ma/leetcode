package Easy.TreeTest;


/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 *
 *
 *
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *  
 *
 * 提示：
 *
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/22 993. 二叉树的堂兄弟节点 */

public class isCousins {

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }else if(root.left==null&&root.right==null){
            return false;
        }else if(root.left==null||root.right==null){
            return isCousins(root.left,x,y)||isCousins(root.right,x,y);
        }else {
            int left=find(root.left,x,0);
            int left2=find(root.left,y,0);
            int right=find(root.right,y,0);
            int right2=find(root.right,x,0);
            if((left*right!=0&&left==right)||(left2*right2!=0&&right2==left2)){
                return true;
            }
            return false;
        }
    }

    public int find(TreeNode root,int x,int depth){
        if(root==null){
            return 0;
        }else if(root.val==x){
            return depth;
        }else {
            int left= find(root.left,x,depth+1);
            int right= find(root.right,x,depth+1);
            if(left!=0){
                return left;
            }else if(right!=0){
                return right;
            }else {
                return 0;
            }
        }
    }


    public boolean isCousins2(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        depth = new int[2];
        parentVal = new int[2];
        dfs(root, 0, -1);
        return depth[0] == depth[1] && parentVal[0] != parentVal[1];
    }

    private int x;

    private int y;

    private int[] depth;

    private int[] parentVal;

    private void dfs(TreeNode node, int deepth, int parentVal) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            depth[0] = deepth;
            this.parentVal[0] = parentVal;
        } else if (node.val == y) {
            depth[1] = deepth;
            this.parentVal[1] = parentVal;
        }
        dfs(node.left, deepth + 1, node.val);
        dfs(node.right, deepth + 1, node.val);
    }

    public static void main(String[] args) {
        
    }
}
