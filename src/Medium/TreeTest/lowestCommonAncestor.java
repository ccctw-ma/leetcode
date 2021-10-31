package Medium.TreeTest;


/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/5/10  */


public class lowestCommonAncestor {

    private boolean find;
    private Deque<TreeNode> lp;
    private Deque<TreeNode> lq;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lp=new ArrayDeque<>();
        lq=new ArrayDeque<>();
        find=false;
        traverse(root,p.val);
        find=false;
        traverse2(root,q.val);
        TreeNode res = null;
        while (!lp.isEmpty()&&!lq.isEmpty()&&lp.getLast().val==lq.getLast().val){
            res=lp.removeLast();
            lq.removeLast();
        }
        return res;
    }

    private void traverse(TreeNode root,int value){
        if(root==null||find) return;
        lp.addFirst(root);
        if(root.val==value){
            find=true;
            return;
        }
        traverse(root.left,value);
        traverse(root.right,value);
        if(!find) lp.removeFirst();
    }

    private void traverse2(TreeNode root,int value){
        if(root==null||find) return;
        lq.addFirst(root);
        if(root.val==value){
            find=true;
            return;
        }
        traverse2(root.left,value);
        traverse2(root.right,value);
        if(!find) lq.removeFirst();

    }


    //很妙
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }




    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        node1.left=node2;
        node2.left=node3;
        node3.right=node4;
        node1.right=node5;
        System.out.println(new lowestCommonAncestor().lowestCommonAncestor(node1,node4,node5).val);
    }


}
