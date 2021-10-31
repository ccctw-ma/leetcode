package Easy.TreeTest;



/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/20 501. 二叉搜索树中的众数
 * @implNote 二叉搜索树的中序遍历就是一个递增序列*/
public class findMode {


    Map<Integer,Integer> map=new HashMap<>();
    public int[] findMode(TreeNode root) {
        find(root);
        int max=Integer.MIN_VALUE;
        List<Integer> list=new ArrayList<>();
        for (Integer integer:map.keySet()){
            if(map.get(integer)>max) {
                list.clear();
                list.add(integer);
                max = map.get(integer);
            }else if(map.get(integer)==max){
                list.add(integer);
            }
        }
        int[] result=new int[list.size()];
        int k=0;
        for (int i:list){
            result[k++]=i;
        }
        return result;
    }

    public void find(TreeNode root){
        if(root==null){
            return;
        }
        int i=map.getOrDefault(root.val,0);
        map.put(root.val,i+1);
        find(root.left);
        find(root.right);
    }


    /**
     * class Solution {
     *
     * // private boolean firstNode = true;
     *
     *     private int pre;
     *
     *     private int count = 0;
     *
     *     private int maxCount = 0;
     *
     *     private List<Integer> list = new ArrayList<>();
     *
     *     public int[] findMode(TreeNode root) {
     *         inOrderTraversal(root);
     *         int[] result = new int[list.size()];
     *         for (int i = 0; i < list.size(); i++) {
     *             result[i] = list.get(i);
     *         }
     *         return result;
     *     }
     *
     *     private void inOrderTraversal(TreeNode treeNode) {
     *         if (null == treeNode) {
     *             return;
     *         }
     *         inOrderTraversal(treeNode.left);
     *
     *            if (pre == treeNode.val) {
     *                 count++;
     *             } else {
     *                 count = 1;
     *                 pre = treeNode.val;
     *             }
     *
     *         if (count > maxCount) {
     *             maxCount = count;
     *             list.clear();
     *             list.add(treeNode.val);
     *         } else if (count == maxCount) {
     *             list.add(treeNode.val);
     *         }
     *         inOrderTraversal(treeNode.right);
     *     }
     *
     *
     *
     * }*/

    public static void main(String[] args) {

    }
}
