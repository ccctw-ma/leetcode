package Difficult.TreeTest;



/*
* 1028. 从先序遍历还原二叉树
我们从二叉树的根节点 root 开始进行深度优先搜索。

在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。

如果节点只有一个子节点，那么保证该子节点为左子节点。

给出遍历输出 S，还原树并返回其根节点 root。



示例 1：



输入："1-2--3--4-5--6--7"
输出：[1,2,5,3,4,6,7]
示例 2：



输入："1-2--3---4-5--6---7"
输出：[1,2,5,3,null,6,null,4,null,7]
示例 3：



输入："1-401--349---90--88"
输出：[1,401,null,349,88,90]


提示：

原始树中的节点数介于 1 和 1000 之间。
每个节点的值介于 1 和 10 ^ 9 之间。*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 马世臣
 * @// TODO: 2020/6/18  */


public class recoverFromPreorder {

    public TreeNode recoverFromPreorder(String s) {
        if(s.length()==0) return null;
        List<String> list=new ArrayList<>();
        int left=0;
        int right=0;
        while (left<s.length()){
            while (right<s.length()&&Character.isDigit(s.charAt(right))){
                right++;
            }
            if(right!=left) list.add(s.substring(left,right));
            left=right;
            while (right<s.length()&&s.charAt(right)=='-'){
                right++;
            }
            if(right!=left) list.add(s.substring(left,right));
            left=right;
        }
//        System.out.println(list);
        return recover(list,0,list.size()-1);
    }

    private TreeNode recover(List<String> list,int left,int right){
        if(left>right) return null;
        if(left==right) return new TreeNode(Integer.parseInt(list.get(left)));
        TreeNode root=new TreeNode(Integer.parseInt(list.get(left)));
        String deep=list.get(left+1);
        int index=left+2;
        while (index<=right){
            if(list.get(index).equals(deep)){
                break;
            }
            index++;
        }
        root.left=recover(list,left+2,index-1);
        root.right=recover(list,index+1,right);
        return root;
    }



    //先序遍历。既然字符串是有先序遍历得到的，那么就用先序遍历去解析该字符串
    private int idx;
    public TreeNode recoverFromPreorder2(String S) {
        idx = 0;
        return helper(S,0);
    }

    private TreeNode helper(String S, int depth) {
        if (idx>=S.length()) return null;
        int curDepth = 0;
        int k = idx;
        while ( k<S.length() && S.charAt(k)=='-'){
            curDepth++;
            k++;
        }
        if (curDepth!=depth) return null;
        idx = k;
        int val = 0;
        while (idx < S.length() && Character.isDigit(S.charAt(idx))){
            val = val*10 + (S.charAt(idx) - '0');
            idx++;
        }
        TreeNode node = new TreeNode(val);
        node.left = helper(S,depth+1);
        node.right = helper(S,depth+1);
        return node;
    }



    //迭代法
    public TreeNode recoverFromPreorder3(String S) {
        int level, val;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length();) {
            for (level = 0; S.charAt(i) == '-'; i++) {
                level++;
            }
            for (val = 0; i < S.length() && S.charAt(i) != '-'; i++) {
                val = val * 10 + (S.charAt(i) - '0');
            }
            while (stack.size() > level) {
                stack.pop();
            }
            TreeNode node = new TreeNode(val);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.add(node);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }




    public static void main(String[] args) {
        System.out.println(new recoverFromPreorder().recoverFromPreorder3("1-2--3--4-5--6--7"));
    }
}
