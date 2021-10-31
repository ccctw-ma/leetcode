package Easy.TreeTest;


/**
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 以 10^9 + 7 为模，返回这些数字之和。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *  
 *
 * 提示：
 *
 * 树中的结点数介于 1 和 1000 之间。
 * node.val 为 0 或 1 。
 **/

/**
 * @author 马世臣 
 * @// TODO: 2020/1/22 1022. 从根到叶的二进制数之和 */

public class sumRootToLeaf {




    /*public int sumRootToLeaf(TreeNode root) {
        if(root==null){
            return 0;
        }
        int sum=0;
        Queue<List<Integer>> lists=traversal(root);
        while (!lists.isEmpty()){
            List<Integer> list=lists.poll();
            int temp=0;
            for (int i:list){
                temp<<=1;
                temp+=i;
            }
            sum+=temp;
        }
        return sum;
    }

    public Queue<List<Integer>> traversal(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        Queue<List<Integer>> list=new LinkedList<>();
        if(root==null)  return list;
        List<Integer> integerList=new ArrayList<>();
        integerList.add(root.val);
        list.offer(integerList);
        queue.offer(root);
        while (!queue.isEmpty()){
            int n=queue.size();
            for (int i=0;i<n;i++){
                TreeNode treeNode=queue.poll();
                List<Integer> list1=list.poll();
                List<Integer> list2=new ArrayList<>();
                list2.addAll(list1);
                if(treeNode.left!=null){
                    queue.offer(treeNode.left);
                    list1.add(treeNode.left.val);
                    list.offer(list1);
                }
                if(treeNode.right!=null){
                    queue.offer(treeNode.right);
                    list2.add(treeNode.right.val);
                    list.offer(list2);
                }
                if(treeNode.left==null&&treeNode.right==null){
                    list.offer(list1);
                }
            }
        }
        return list;
    }*/


    private int n=0;
    public int sumRootToLeaf(TreeNode root) {
        if(root==null){
            return 0;
        }
        traversalSum(root,0);
        return n;
    }

    public void traversalSum(TreeNode root,int sum){
        if(root==null){
            return;
        }
        sum<<=1;
        sum+=root.val;
        if(root.left==null&&root.right==null){
            n+=sum;
        }
        traversalSum(root.left,sum);
        traversalSum(root.right,sum);
    }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(0);
        TreeNode t3=new TreeNode(1);
        TreeNode t4=new TreeNode(0);
        TreeNode t5=new TreeNode(1);
        TreeNode t6=new TreeNode(0);
        TreeNode t7=new TreeNode(1);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        System.out.println(new sumRootToLeaf().sumRootToLeaf(t1));
    }
}
