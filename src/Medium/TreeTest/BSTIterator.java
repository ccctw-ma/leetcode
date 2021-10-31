package Medium.TreeTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {


    private Deque<TreeNode> stack;
    private TreeNode node;
    public BSTIterator(TreeNode root) {
        stack=new ArrayDeque<>();
        this.node=root;
        while (node!=null){
            stack.push(node);
            node=node.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        node=stack.pop();
        int res=node.val;
        node=node.right;
        while (node!=null){
            stack.push(node);
            node=node.left;
        }
        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }


}
