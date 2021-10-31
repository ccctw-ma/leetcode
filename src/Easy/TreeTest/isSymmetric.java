package Easy.TreeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class isSymmetric {

    public static boolean isSymmetric(TreeNode root) {
        if(root.left==null&&root.right==null){
            return true;
        }else if((root.right==null)||(root.left==null)){
            return false;
        }
        Stack<Integer> numStack=new Stack<>();
        Stack<TreeNode> treeStack=new Stack<>();
        treeStack.push(root.left);
        treeStack.push(root.right);
        while(!treeStack.isEmpty()){
            List<TreeNode> list=new ArrayList<>();
            for(TreeNode node:treeStack){
                add(numStack,node.val);
                list.add(node);
            }
            if(!numStack.isEmpty()){
                return false;
            }
            treeStack.clear();
            for(TreeNode node:list){
                if(node.left!=null){
                    treeStack.push(node.left);
                }
                if(node.right!=null){
                    treeStack.push(node.right);
                }
            }
        }
        return true;
    }

    public static void add(Stack<Integer> s,int i){
        if(s.isEmpty()){
            s.push(i);
        }else if(s.peek()==i){
            s.pop();
        }
    }

    /**
     * public boolean isSymmetric(TreeNode root) {
     *     return isMirror(root, root);
     * }
     *
     * public boolean isMirror(TreeNode t1, TreeNode t2) {
     *     if (t1 == null && t2 == null) return true;
     *     if (t1 == null || t2 == null) return false;
     *     return (t1.val == t2.val)
     *         && isMirror(t1.right, t2.left)
     *         && isMirror(t1.left, t2.right);
     * }
     **/
    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(1);
        TreeNode treeNode2=new TreeNode(2);
        TreeNode treeNode3=new TreeNode(2);
        TreeNode treeNode4=new TreeNode(3);
        TreeNode treeNode5=new TreeNode(4);
        TreeNode treeNode6=new TreeNode(4);
        TreeNode treeNode7=new TreeNode(3);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode2.left=treeNode4;
        treeNode2.right=treeNode5;
        treeNode3.left=treeNode6;
        treeNode3.right=treeNode7;
        System.out.println(isSymmetric(treeNode1));
    }
}
