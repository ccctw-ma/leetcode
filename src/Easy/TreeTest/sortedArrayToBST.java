package Easy.TreeTest;


/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 **/

import java.util.LinkedList;

/**
 * @author 马世臣 
 * @// TODO: 2020/1/18 108. 将有序数组转换为二叉搜索树 */
public class sortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        LinkedList<TreeNode> treeNodes=new LinkedList<>();
        int i=1;
        if(nums==null){
            return null;
        }
        TreeNode root=new TreeNode(nums[nums.length/2]);
        treeNodes.offer(root);
        while (i<nums.length){
            int n=treeNodes.size();
            for (int j=0;j<n;j++){
                TreeNode treeNode=treeNodes.poll();
                if(i<nums.length){
                    TreeNode treeNode1=new TreeNode(nums[i++]);
                    treeNode.left=treeNode1;
                    treeNodes.offer(treeNode1);
                }else {
                    break;
                }
                if(i<nums.length){
                    TreeNode treeNode1=new TreeNode(nums[i++]);
                    treeNode.right=treeNode1;
                    treeNodes.offer(treeNode1);
                }else {
                    break;
                }
            }
        }
        return root;
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length);
    }
    private TreeNode sortedArrayToBST(int[] nums,int start,int end){
        if(start == end){
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums,start,mid);
        root.right = sortedArrayToBST(nums,mid + 1,end);
        return root;
    }

    public static void main(String[] args) {

    }
}
