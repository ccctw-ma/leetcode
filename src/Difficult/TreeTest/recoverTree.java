package Difficult.TreeTest;


/*
*
* 99. 恢复二叉搜索树
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？*/

import java.util.*;

/**
 * @author 马世臣
 * @// TODO: 2020/8/8
 * */


public class recoverTree {

    public void recoverTree(TreeNode root) {
        pre=null;
        list=new ArrayList<>();
        dfs(root);
        list.sort(Comparator.comparingInt(o -> o));
        index=0;
        fs(root);
    }


    private int index=0;
    private List<Integer> list;
    private TreeNode pre;
    private void dfs(TreeNode root){
        if(root!=null){
            dfs(root.left);
            list.add(root.val);
            dfs(root.right);
        }
    }

    private void fs(TreeNode root){
        if(root!=null){
            fs(root.left);
            root.val=list.get(index++);
            fs(root.right);
        }
    }


    //明确只需要交换两个节点，有可能两个节点是连在一起的也有可能相隔很远
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }



    public static void main(String[] args) {

    }
}
