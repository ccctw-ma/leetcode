package Medium.TreeTest;


/*
* 337. 打家劫舍 III
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.*/

/**
 * @author 马世臣
 * @// TODO: 2020/5/29  */


public class rob {



    public int rob(TreeNode root) {
        if(root==null) return 0;
        int[] res = helper(root);
        return Math.max(res[0],res[1]);
    }

    public int[] helper(TreeNode r){
        if(r == null) return new int[2];//边界条件，r为null时，跳出
        int[] left = helper(r.left);//对于以r.left为根的树，计算抢劫根节点(r.left)与不抢劫根节点可获得最大金额. left[0]则为不抢r.lrft可获得的最大金额,left[1]则为抢劫r.left可获得的最大金额  以下right[] 分析同理
        int[] right = helper(r.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);//计算不抢劫当前根节点可获得的最大金额(那么其左右子树可以随便抢)
        res[1] = r.val + left[0] + right[0];//计算若抢劫根节点可获得的最大金额(此时,其左右子树的根节点不能被抢)
        return res;
    }


    //思路不对，当不要root时，它的左右不是全部都必须要的，是随意的
    private int robb(TreeNode root, boolean able){
        if(root==null) return 0;
        //过多重复计算
        int a=root.val+ robb(root.left,false)+ robb(root.right,false);
        int b= robb(root.left,true)+ robb(root.right,true);
        if(able)   return Math.max(a,b);
        return  b;
    }

/*
    private void traverse(TreeNode root){
        if(root!=null){
            traverse(root.left);
            list.add(root.val);
            traverse(root.right);
        }
    }
*/

    public static void main(String[] args) {

    }
}
