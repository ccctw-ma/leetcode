package Easy.TreeTest;


/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/1/20 235. 二叉搜索树的最近公共祖先 */
public class lowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }


    }


    //nonRecursive time o(n) space o(n) may be a little complicated
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList=new ArrayList<>();
        List<TreeNode> qList=new ArrayList<>();
        TreeNode temp=root;
        while(temp.val!=p.val){
            pList.add(temp);
            if(temp.val>p.val){
                temp=temp.left;
            }else{
                temp=temp.right;
            }
        }
        pList.add(temp);

        TreeNode temp2=root;
        while(temp2.val!=q.val){
            qList.add(temp2);
            if(temp2.val>q.val){
                temp2=temp2.left;
            }else{
                temp2=temp2.right;
            }
        }
        qList.add(temp2);
        int min=Math.min(pList.size(),qList.size());
        int index=0;
        while(index<min&&pList.get(index).val==qList.get(index).val) index++;
        index--;
        return pList.get(index);
    }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(6);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(8);
        TreeNode t4=new TreeNode(0);
        TreeNode t5=new TreeNode(4);
        TreeNode t6=new TreeNode(7);
        TreeNode t7=new TreeNode(9);
        TreeNode t8=new TreeNode(3);
        TreeNode t9=new TreeNode(5);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        t5.left=t8;
        t5.right=t9;
    }
}
