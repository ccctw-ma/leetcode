package Medium.TreeTest;

public class longestZigZag {

    private int max=0,height=0;
    public int longestZigZag(TreeNode root) {
        findMaxHeight(root,0);
        traverse(root,0);
        return max;
    }

    private void findMaxHeight(TreeNode root,int height){
        if(root==null) return;
        this.height=Math.max(this.height,height);
        findMaxHeight(root.left,height+1);
        findMaxHeight(root.right,height+1);
    }

    private void traverse(TreeNode root,int n){
        if(root!=null){
            findMaxZigZag(root.left,0,n+1,false);
            findMaxZigZag(root.right,0,n+1,true);
            traverse(root.left,n+1);
            traverse(root.right,n+1);
        }
    }

    private void findMaxZigZag(TreeNode root,int step,int n,boolean dir){
        if(root==null) return;
        if(!dir){
            step++;
            if(height-n+step<max) return;
            max=Math.max(step,max);
            findMaxZigZag(root.right,step,n+1,true);
        }else {
            step++;
            if(height-n+step<max) return;
            max=Math.max(step,max);
            findMaxZigZag(root.left,step,n+1,false);
        }
    }


    private int res = 0;

    private void check(TreeNode root, boolean left, int cur) {
        if (root == null) return;
        res = Math.max(res, cur + 1);
        //妙呀，left==true就把继续往左的分支数量清0，然后接着递归进行运算，left==true同理
        check(root.left, true, left ? 0 : cur + 1);
        check(root.right, false, left ? cur + 1 : 0);
    }

    //简单
    public int longestZigZag2(TreeNode root) {
        check(root.left, true, 0);
        check(root.right, false, 0);
        return res;
    }

    public static void main(String[] args) {

    }
}
