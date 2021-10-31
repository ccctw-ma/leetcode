package Easy.TreeTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 **/

/**
 * @author 马世臣
 * @// TODO: 2020/1/20  257. 二叉树的所有路径
 * @implNote 尽量用递归，迭代开销较大*/
public class binaryTreePaths {

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list=new ArrayList<>();
        if(root==null){
            return list;
        }
        LinkedList<TreeNode> linkedList=new LinkedList<>();
        LinkedList<StringBuilder> stringBuilders=new LinkedList<>();
        linkedList.offer(root);
        stringBuilders.offer(new StringBuilder("S"));
        while (!linkedList.isEmpty()){
            int count=linkedList.size();
            for (int i=0;i<count;i++){
                StringBuilder temp=stringBuilders.poll();
                TreeNode treeNode=linkedList.poll();
                temp.append("->"+treeNode.val);
                if(treeNode.left==null&&treeNode.right==null){
                    list.add(temp.substring(3,temp.length()));
                }else if(treeNode.left==null){
                    linkedList.offer(treeNode.right);
                    stringBuilders.offer(temp);
                }else if(treeNode.right==null){
                    linkedList.offer(treeNode.left);
                    stringBuilders.offer(temp);
                }else {
                    stringBuilders.offer(temp);
                    stringBuilders.offer(new StringBuilder(temp));
                    linkedList.offer(treeNode.left);
                    linkedList.offer(treeNode.right);
                }
            }
        }
        return list;
    }

/**
 * class Solution {
 *     public void construct_paths(TreeNode root, String path, LinkedList<String> paths) {
 *         if (root != null) {
 *             path += Integer.toString(root.val);
 *             if ((root.left == null) && (root.right == null))  // 当前节点是叶子节点
 *                 paths.add(path);  // 把路径加入到答案中
 *             else {
 *                 path += "->";  // 当前节点不是叶子节点，继续递归遍历
 *                 construct_paths(root.left, path, paths);
 *                 construct_paths(root.right, path, paths);
 *             }
 *         }
 *     }
 *
 *     public List<String> binaryTreePaths(TreeNode root) {
 *         LinkedList<String> paths = new LinkedList();
 *         construct_paths(root, "", paths);
 *         return paths;
 *     }
 * }
 **/

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
        for (String s:binaryTreePaths(t1)){
            System.out.println(s);
        }
    }
}
