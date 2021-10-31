package Difficult.TreeTest;


/*
* 968. 监控二叉树
给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。



示例 1：



输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。
示例 2：



输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

提示：

给定树的节点数的范围是 [1, 1000]。
每个节点的值都是 0。*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/22
 * */


public class minCameraCover {





    //I can't work out this problem, ah! come on
    private int ans = 0;

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        if (dfs(root) == 3) ans++;
        return ans;
    }

    // 1：该节点安装了监视器 2：该节点可观，但没有安装监视器 3：该节点不可观
    private int dfs(TreeNode node) {
        if (node == null)
            return 2;
        int left = dfs(node.left), right = dfs(node.right);
        if (left == 3 || right == 3) {
            ans++;
            return 1;
        } else if (left == 1 || right == 1){
            return 2;
        } else
            return 3;
    }


    public static void main(String[] args) {


        
    }
}
