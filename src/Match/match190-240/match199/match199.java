package Match.match199;

import java.util.ArrayList;
import java.util.List;

public class match199 {

    public String restoreString(String s, int[] indices) {
        char[] chars=new char[s.length()];
        for (int i=0;i<s.length();i++){
            chars[indices[i]]=s.charAt(i);
        }
        return String.valueOf(chars);
    }

    public int minFlips(String target) {
        int count=0;
        char ch='0';
        for (int i=0;i<target.length();i++){
            char temp=target.charAt(i);
            if(temp!=ch){
                count++;
                ch=temp;
            }
        }
        return count;
    }



    private int distance;
    private int count;
    public int countPairs(TreeNode root, int distance) {
        this.distance=distance;
        this.count=0;
        dfs(root);
        return count;
    }

    private List<Integer> dfs(TreeNode root){
        if(root==null){
            return null;
        }
        List<Integer> left=dfs(root.left);
        List<Integer> right=dfs(root.right);
        if(left!=null&&right!=null){
            for (int i:left){
                for (int j:right){
                    if(i+j<=distance){
                        count++;
                    }
                }
            }
        }
        List<Integer> res=new ArrayList<>();
        if(left==null&&right==null){
            res.add(1);
            return res;
        }else {
            if(left!=null){
                for (Integer integer : left) {
                    res.add(integer + 1);
                }
            }
            if(right!=null){
                for (int i:right){
                    res.add(i+1);
                }
            }
            return res;
        }

    }


    public int getLengthOfOptimalCompression(String s, int k) {
        StringBuilder builder=new StringBuilder();
        int index=0;
        List<Node> nodes=new ArrayList<>();
        while (index<s.length()){
            int n=0;
            char ch=s.charAt(index);
            while (index<s.length()&&s.charAt(index)==ch){
                index++;
                n++;
            }
            nodes.add(new Node(n,ch));
        }
        List<Integer> indexs=new ArrayList<>();
        for (int i=0;i<nodes.size();i++){
            if(i-1>=0&&i+1<nodes.size()&&nodes.get(i-1).c==nodes.get(i+1).c&&nodes.get(i).num<=k){
                indexs.add(i);
            }
        }


        return 0;
    }

    private  class Node{
        int num;
        char c;
        public Node(int n,char c){
            this.num=n;
            this.c=c;
        }
    }

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }


    public static void main(String[] args) {
        System.out.println(new match199().getLengthOfOptimalCompression("aaabcccd",2));
    }
}
