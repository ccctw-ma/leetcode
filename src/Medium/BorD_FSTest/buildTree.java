package Medium.BorD_FSTest;


/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/15  */

public class buildTree {


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int a=preorder.length;
        if(a==0) return null;
        TreeNode root=new TreeNode(preorder[0]);
        int index=0;
        for (int i=0;i<a;i++){
            if(inorder[i]==preorder[0]){
                index=i;
            }
        }
        root.left=build(preorder,1,index+1,inorder,0,index);
        root.right=build(preorder,index+1,a,inorder,1+index,a);
        return root;
    }

    private TreeNode build(int[] preorder,int start,int end ,int[] inorder,int left,int right){
        if(start<end){
            TreeNode root=new TreeNode(preorder[start]);
            int index=left;
            for (int i=left;i<right;i++){
                if(inorder[i]==preorder[start]){
                    index=i;
                }
            }//这一步可以用hash来进行效率比较高
            root.left=build(preorder,start+1,index-left+start+1,inorder,left,index);
            root.right=build(preorder,index-left+start+1,end,inorder,1+index,right);
            return root;
        }
        return null;
    }


    private int pre=0;
    private int in=0;
    public TreeNode buildTree2(int [] preorder, int [] inorder) {
        return buildTree(preorder,inorder,Integer.MAX_VALUE+1);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, long stop){
        //数组为空则返回null
        if(pre==preorder.length){
            return null;
        }
        //中序遍历序列数组顺序值等于终止值，则依次后移
        //表示此节点为空
        if(inorder[in]==stop){
            in++;
            return null;
        }
        //按照先序遍历顺序值新建节点
        int val=preorder[pre++];
        TreeNode root=new TreeNode(val);
        //建立左节点，终止值为当前节点值
        root.left=buildTree(preorder,inorder,val);
        //建立右节点，终止值为上一节点值
        root.right=buildTree(preorder,inorder,stop);
        //返回当前节点
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new buildTree().buildTree2(new int[]{3,9,4,8,20,15,7},new int[]{4,9,8,3,15,20,7}));
    }
}
