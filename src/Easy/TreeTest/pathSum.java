package Easy.TreeTest;


/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 **/

import java.util.LinkedList;

/**
 * @author 马世臣
 * @// TODO: 2020/1/20  437. 路径总和 III*/

public class pathSum {


    public int pathSum(TreeNode root, int sum) {
        int n=0;
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> treeNodes=new LinkedList<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()){
            int count=treeNodes.size();
            for (int i=0;i<count;i++){
                TreeNode temp=treeNodes.poll();
                n+=sumP(temp,sum);
                if(temp.left!=null){
                    treeNodes.offer(temp.left);
                }
                if(temp.right!=null){
                    treeNodes.offer(temp.right);
                }
            }
        }
        return n;
    }

    public int sumP(TreeNode root,int sum){
        if(root==null){
            return 0;
        }else if (sum-root.val==0){
            System.out.println(root.val);
            return 1+sumP(root.left,sum-root.val)+sumP(root.right,sum-root.val);
        }
        return sumP(root.left,sum-root.val)+sumP(root.right,sum-root.val);
    }

    public int pathSum2(TreeNode root, int sum) {
        return pathSumHelper(root, sum, new int[1000], 0);
    }

    public int pathSumHelper(TreeNode root, int sum, int[] path/*保存路径*/, int p/*指向路径终点*/){
        if(root == null) return 0;
        path[p] = root.val;
        int curSum = root.val;
        int n = curSum==sum? 1:0;
        for(int i = p-1; i>=0; i--){
            curSum += path[i];
            if(curSum == sum) n++;
        }

        int n1 = pathSumHelper(root.left, sum, path, p+1);
        int n2 = pathSumHelper(root.right, sum, path, p+1);
        return n+n1+n2;
    }
    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(-2);
        TreeNode t3=new TreeNode(-3);
        TreeNode t4=new TreeNode(1);
        TreeNode t5=new TreeNode(3);
        TreeNode t6=new TreeNode(-2);
        TreeNode t7=new TreeNode(-1);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t4.left=t7;
        System.out.println("answer"+new pathSum().pathSum(t1,-1));
    }
}
