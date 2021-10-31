package InterviewTest.Easy;


/*
* 面试题 17.12. BiNode
二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。

返回转换后的单向链表的头节点。

注意：本题相对原题稍作改动



示例：

输入： [4,2,5,1,3,null,6,0]
输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
提示：

节点数量不会超过 100000。*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马世臣
 * @// TODO: 2020/8/12  */



public class convertBiNode {

    public TreeNode convertBiNode(TreeNode root) {
        Deque<TreeNode> stack=new ArrayDeque<>();
        TreeNode pre=new TreeNode(0);
        TreeNode head=pre;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            TreeNode temp=stack.pop();
            temp.left=null;
            pre.right=temp;
            pre=temp;
            root=temp.right;
        }
        return head.right;
    }


    public static void main(String[] args) {

    }
}
