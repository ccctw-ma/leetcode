package Medium.TreeTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class deleteNode {


    //不太对
    public TreeNode deleteNode(TreeNode root, int key) {

        TreeNode tar=root;
        TreeNode pre=null;
        while(tar!=null&&tar.val!=key){
            pre=tar;
            tar=(tar.val<key?tar.right:tar.left);
        }
        if(tar==null){
            return root;
        } else if(tar.left==null&&tar.right==null){
            return null;
        } else if(pre.left!=null&&pre.left.val==key){
            pre.left=delete(tar);
        }else if(pre.right!=null&&pre.right.val==key){
            pre.right=delete(tar);
        }
        return root;
    }

    private TreeNode delete(TreeNode root){
        //为叶子结点直接删除
        if(root.left==null&&root.right==null){
            return null;
        }
        //节点左子树为空直接用右子树代替
        else if(root.left==null){
            return root.right;
        }
        //右子树为空直接用左子树代替
        else if(root.right==null){
            return root.left;
        }
        //都不为空时找右子树中序遍历的第一个结点代替
        else{
            Deque<TreeNode> stack=new ArrayDeque<>();
            TreeNode temp=root.right;
            while(temp!=null){
                stack.push(temp);
                temp=temp.left;
            }
            TreeNode top=stack.pop();
            int t=top.val;
            root.right=deleteNode(root.right,t);
            root.val=t;
        }

        return root;
    }


    //这个方法比较讨巧，把右子树放在待删除节点左子树最大节点的右子树处
    //并将待删除节点的左子树来代替被删除节点
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val>key) {
            root.left = deleteNode2(root.left, key);
        }else if (root.val<key){
            root.right = deleteNode2(root.right, key);
        }else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode leftMax = root.left;
            while (leftMax.right != null){
                leftMax = leftMax.right;
            }
            leftMax.right = root.right;
            return root.left;
        }
        return root;
    }


    public TreeNode deleteNode3(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            // 待删除节点在左子树中
            root.left = deleteNode3(root.left, key);
            return root;
        } else if (key > root.val) {
            // 待删除节点在右子树中
            root.right = deleteNode3(root.right, key);
            return root;
        } else {
            // key == root.val，root 为待删除节点
            if (root.left == null) {
                // 返回右子树作为新的根
                return root.right;
            } else if (root.right == null) {
                // 返回左子树作为新的根
                return root.left;
            } else {
                // 左右子树都存在，返回后继节点（右子树最左叶子）作为新的根
                TreeNode successor = min(root.right);
                successor.right = deleteMin(root.right);
                successor.left = root.left;
                return successor;
            }
        }
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
    public static void main(String[] args) {
        TreeNode node1=new TreeNode(5);
        TreeNode node2=new TreeNode(3);
        TreeNode node3=new TreeNode(6);
        TreeNode node4=new TreeNode(2);
        TreeNode node5=new TreeNode(4);
        TreeNode node6=new TreeNode(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node6;
        System.out.println(new deleteNode().deleteNode(node1,3));
    }
}
