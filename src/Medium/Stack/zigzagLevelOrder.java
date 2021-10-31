package Medium.Stack;

import java.util.*;

public class zigzagLevelOrder {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean flag=true;
        Queue<TreeNode> queue=new LinkedList<>();
        List<List<Integer>> listList=new ArrayList<>();
        if(root==null) return listList;
        queue.offer(root);
        while (!queue.isEmpty()){
            if(flag){
                List<Integer> list=new ArrayList<>();
                int n=queue.size();
                for (int i=0;i<n;i++){
                    TreeNode temp=queue.poll();
                    list.add(temp.val);
                    if(temp.left!=null) queue.offer(temp.left);
                    if(temp.right!=null) queue.offer(temp.right);
                }
                listList.add(list);
            }else {
                Stack<Integer> stack=new Stack<>();
                int n=queue.size();
                for (int i=0;i<n;i++){
                    TreeNode temp=queue.poll();
                    stack.push(temp.val);
                    if(temp.left!=null) queue.offer(temp.left);
                    if(temp.right!=null) queue.offer(temp.right);
                }
                List<Integer> list=new ArrayList<>();
                while (!stack.isEmpty()){
                    list.add(stack.pop());
                }
                listList.add(list);
            }
            flag=!flag;
        }
        return listList;
    }

    public static void main(String[] args) {

    }
}
