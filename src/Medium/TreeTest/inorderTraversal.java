package Medium.TreeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？*/

/**
 * @author 马世臣
 * @// TODO: 2020/4/21
 * */

public class inorderTraversal {


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            list.add(root.val);
            root=root.right;
        }
        return list;
    }


    //该算法将迭代算法的空间复杂度降到了o(1)
    //核心思想就是利用线索二叉树的后继思路，通过建立后继节点避免了通过建立栈来寻找父节点
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        TreeNode temp;
        while(root!=null){
            //若节点的左子树不为空，则找到左子树的最右节点
            if(root.left!=null){
                temp=root.left;
                while(temp.right!=null&&temp.right!=root){
                    temp=temp.right;
                }
                //最右已经找到了，此时将最右节点的右子树设为此时的root节点,以便后续进行遍历
                if(temp.right==null){
                    temp.right=root;
                    root=root.left;
                }else{
                    //此时已经将左子树遍历完了，相当于现在一颗子树的根节点处
                    //这里需要将之前的后继线索删除并向右子树迭代
                    list.add(root.val);
                    temp.right=null;
                    root=root.right;
                }
            }else{
                //节点若没有左节点则直接访问其右节点，无需多做操作
                //这个右节点即为其后继节点，可以是先前处理过的后继节点
                list.add(root.val);
                root=root.right;
            }
        }
        return list;
    }


    public static void main(String[] args) {

    }
}
