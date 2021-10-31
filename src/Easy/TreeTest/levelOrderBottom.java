package Easy.TreeTest;

import java.util.*;


/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/18  107. 二叉树的层次遍历 II*/
public class levelOrderBottom {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();
        Stack<List<Integer>> stack=new Stack<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root!=null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            List<Integer> list=new ArrayList<>();
            List<TreeNode> treeNodeList=new ArrayList<>();
            while (queue.size()!=0){
                TreeNode treeNode=queue.poll();
                list.add(treeNode.val);
                if(treeNode.left!=null){
                    treeNodeList.add(treeNode.left);
                }
                if(treeNode.right!=null){
                    treeNodeList.add(treeNode.right);
                }
            }
            for (TreeNode treeNode:treeNodeList){
                queue.offer(treeNode);
            }
            stack.push(list);
        }
        while (stack.size()!=0){
            lists.add(stack.pop());
        }
        return lists;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        Deque<List<Integer>> deque=new ArrayDeque<>();
        Deque<TreeNode> queue=new ArrayDeque<>();
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return res;
        queue.add(root);
        while (!queue.isEmpty()){
            int n=deque.size();
            List<Integer> list=new ArrayList<>();
            for (int i=0;i<n;i++){
                TreeNode temp=queue.poll();
                list.add(temp.val);
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            deque.push(list);
        }
        while (!deque.isEmpty()){
            res.add(deque.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1=new TreeNode(3);
        TreeNode treeNode2=new TreeNode(9);
        TreeNode treeNode3=new TreeNode(20);
        TreeNode treeNode4=new TreeNode(15);
        TreeNode treeNode5=new TreeNode(1);

        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;
        treeNode3.left=treeNode4;
        treeNode3.right=treeNode5;
//        System.out.println(new levelOrderBottom().levelOrderBottom2(treeNode1));



        Deque<Integer> deque=new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        deque.add(5);
        deque.add(6);
        deque.push(7);
        deque.push(8);
        deque.push(9);
        deque.push(10);
        System.out.println(deque.peekFirst()+" "+deque.peekLast());

        PriorityQueue<Integer> queue=new PriorityQueue<>();
    }

}
