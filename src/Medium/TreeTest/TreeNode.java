package Medium.TreeTest;

public class TreeNode {

    public int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    TreeNode(int val,TreeNode left,TreeNode right){
        this.left = left;
        this.right = right;
        this.val = val;
    }


}
