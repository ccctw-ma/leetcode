package Difficult.TreeTest;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        StringBuilder builder=new StringBuilder();
        while (!queue.isEmpty()){
            int n=queue.size();
            for (int i=0;i<n;i++){
                TreeNode node=queue.poll();
                if(node==null){
                    builder.append("null ");
                }else {
                    builder.append(node.val).append(" ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        return builder.toString().trim();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings= data.split(" ");
        String s=strings[0];
        Queue<TreeNode> queue=new LinkedList<>();
        if(s.equals("")) return null;
        TreeNode root=new TreeNode(Integer.parseInt(s));
        queue.add(root);
        int index=1;
        while (!queue.isEmpty()){
            TreeNode temp=queue.poll();
            String s1=strings[index++];
            String s2=strings[index++];
            if(!s1.equals("null")){
                temp.left=new TreeNode(Integer.parseInt(s1));
                queue.add(temp.left);
            }
            if(!s2.equals("null")){
                temp.right=new TreeNode(Integer.parseInt(s2));
                queue.add(temp.right);
            }
        }
        return root;
    }



    //使用递归
    public String serialize2(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((char) (root.val + '0'));
        String left = serialize(root.left);
        String right = serialize(root.right);
        sb.append(left).append(right);
        return sb.toString();
    }

    private int index = -1;

    public TreeNode deserialize2(String data) {
        index++;
        if (index >= data.length()) {
            return null;
        }
        char c = data.charAt(index);
        if (c == '#') {
            return null;
        }
        TreeNode node = new TreeNode((c - '0'));
        node.left = deserialize(data);
        node.right = deserialize(data);
        return node;
    }


    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        Codec codec=new Codec();
        System.out.println(codec.deserialize(codec.serialize(node1)));
    }
}
