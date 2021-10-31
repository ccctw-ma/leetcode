package Easy.TreeTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 637. 二叉树的层平均值
 *
 *
 *
 *
 * 题目描述
 * 评论 (161)
 * 题解(54)
 * 提交记录
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 示例 1:
 *
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 *
 * 节点值的范围在32位有符号整数范围内。*/

/**
 * @author 马世臣
 * @// TODO: 2020/1/21  */

public class averageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list=new ArrayList<>();
        if(root==null){
            return list;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count=queue.size();
            double sum=0;
            for (int i=0;i<count;i++){
                TreeNode node=queue.poll();
                double temp=node.val;
                sum+=temp;
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            list.add(sum/count);
        }
        return list;
    }


    private int depth;
    private long[] val;
    private int[] num;

    public List<Double> averageOfLevels2(TreeNode root) {
        depth=0;
        num=new int[1133];
        val=new long[1133];
        List<Double> list=new ArrayList<>();

        dfs(root,0);

        for(int i=0;i<=depth;i++)
            list.add(1.0*val[i]/num[i]);

        return list;
    }

    private void dfs(TreeNode root,int length) {
        if(root==null) return;
        val[length]+=root.val;
        num[length]++;
        depth=Math.max(depth, length);
        dfs(root.left,length+1);
        dfs(root.right,length+1);
    }

    public static void main(String[] args) {

    }
}
