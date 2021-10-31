package Medium.TreeTest;


/*
* 623. 在二叉树中增加一行
给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。

添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。

将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。

如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。

示例 1:

输入:
二叉树如下所示:
       4
     /   \
    2     6
   / \   /
  3   1 5

v = 1

d = 2

输出:
       4
      / \
     1   1
    /     \
   2       6
  / \     /
 3   1   5

示例 2:

输入:
二叉树如下所示:
      4
     /
    2
   / \
  3   1

v = 1

d = 3

输出:
      4
     /
    2
   / \
  1   1
 /     \
3       1
注意:

输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
输入的二叉树至少有一个节点。*/

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 马世臣
 * @// TODO: 2020/8/14
 * */

public class addOneRow {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d==1){
            TreeNode res=new TreeNode(v);
            res.left=root;
            return res;
        }
        TreeNode head=root;
        Queue<TreeNode> queue=new LinkedList<>();
        int height=1;
        queue.add(head);
        while (!queue.isEmpty()){
            int n= queue.size();
            height++;
            if(height==d){
                for (int i=0;i<n;i++){
                    TreeNode temp=queue.poll();
                    if(temp.left!=null){
                        TreeNode t=new TreeNode(v);
                        t.left=temp.left;
                        temp.left=t;
                        temp.right=null;
                    }
                    if(temp.right!=null){
                        TreeNode t=new TreeNode(v);
                        t.right=temp.right;
                        temp.right=t;
                        temp.left=null;
                    }
                }
                break;
            }else {
                for (int i=0;i<n;i++){
                    TreeNode temp=queue.poll();
                    if(temp.left!=null) queue.add(temp.left);
                    if(temp.right!=null) queue.add(temp.right);
                }
            }
        }
        return root;

    }



    public static void main(String[] args) {

    }
}
