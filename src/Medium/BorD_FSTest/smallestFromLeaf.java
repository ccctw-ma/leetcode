package Medium.BorD_FSTest;


/*
* 988. 从叶结点开始的最小字符串
给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 'b'，依此类推。

找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。

（小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。）



示例 1：



输入：[0,1,2,3,4,3,4]
输出："dba"
示例 2：



输入：[25,1,3,1,3,0,2]
输出："adz"
示例 3：



输入：[2,2,1,null,1,0,null,0]
输出："abc"


提示：

给定树的结点数介于 1 和 8500 之间。
树中的每个结点都有一个介于 0 和 25 之间的值。*/

/**
 * @author 马世臣
 * @// TODO: 2020/7/7  */


public class smallestFromLeaf {


    private StringBuilder min;
    public String smallestFromLeaf(TreeNode root) {
        min=new StringBuilder();
        dfs(root,new StringBuilder());
        return min.reverse().toString();
    }

    private void dfs(TreeNode root,StringBuilder builder){
        if(root==null) return;
        builder.append((char)(root.val+'a'));
        if(root.left==null&&root.right==null){

            if(min.length()==0||compare(builder,min)){
                min=new StringBuilder(builder);
            }
        }
        dfs(root.left,builder);
        dfs(root.right,builder);
        builder.deleteCharAt(builder.length()-1);
    }

    private boolean compare(StringBuilder a,StringBuilder b){
        int x=a.length()-1;
        int y=b.length()-1;
        for (;x>=0&&y>=0;x--,y--){
            int diff=a.charAt(x)-b.charAt(y);
            if(diff<0) {
                return true;
            } else if(diff>0){
                return false;
            }
        }
        return x==-1;
    }

    public static void main(String[] args) {
        StringBuilder a=new StringBuilder("ccba");
        StringBuilder b=new StringBuilder("cba");
        System.out.println(new smallestFromLeaf().compare(b,a));
    }
}
