package Easy.TreeTest;


/**
 * 538. 把二叉搜索树转换为累加树
 *
 *
 *
 *
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13*/


import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/20  */
public class convertBST {

    List<Integer> list=new ArrayList<>();
    int index=0;
    public TreeNode convertBST(TreeNode root) {
        inOrderTraversal(root);
        int[] array=new int[list.size()];
        int k=0;
        for (int i:list){
            array[k++]=i;
            System.out.println(i);
        }
        for (int i=array.length-2;i>=0;i--){
            array[i]=array[i+1]+array[i];
        }
        for (int i:array){
            System.out.println(i);
        }
        changeValue(root,array);
        return root;
    }

    public void inOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        list.add(root.val);
        inOrderTraversal(root.right);
    }

    public void changeValue(TreeNode root,int[] array){
        if(root==null){
            return;
        }
        changeValue(root.left,array);
        root.val=array[index++];
        changeValue(root.right,array);
    }

    /**
     *  private int sum = 0;
     *
     *     public TreeNode convertBST(TreeNode root) {
     *         if (root != null) {
     *             convertBST(root.right);
     *             sum += root.val;
     *             root.val = sum;
     *             convertBST(root.left);
     *         }
     *         return root;
     *     }
     **/

    public static void main(String[] args) {

    }
}
