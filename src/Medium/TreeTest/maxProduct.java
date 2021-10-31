package Medium.TreeTest;

public class maxProduct {

    private int sum=0;
    private long max=0;
    public int maxProduct(TreeNode root) {
        this.sum=getSum(root);
        SeqTraversal(root);
        return (int) (max%1000000007);
    }

    private void SeqTraversal(TreeNode root){
        if(root==null){
            return;
        }
        long a=root.val,b=sum-root.val;
        max=Math.max(max,a*b);
        SeqTraversal(root.left);
        SeqTraversal(root.right);
    }

    private int getSum(TreeNode root){
        if(root==null) return 0;
        root.val=getSum(root.left)+getSum(root.right)+root.val;
        return root.val;
    }

    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        node1.left=node2;
        node2.left=node4;
        node2.right=node5;
        node1.right=node3;
        node3.left=node6;
        System.out.println(new maxProduct().maxProduct(node1));
    }
}
