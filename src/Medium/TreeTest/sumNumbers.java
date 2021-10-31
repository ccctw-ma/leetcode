package Medium.TreeTest;

public class sumNumbers {


    //麻烦了，完全可以不用数组来进行数值存储
    private int sum;
    public int sumNumbers(TreeNode root) {
        sum=0;
        dfs(root,new int[1000],0);
        return sum;
    }

    private void dfs(TreeNode root,int[] path,int p){
        if(root==null) return;
        path[p]=root.val;
        int num=0;
        if(root.left==null&&root.right==null){
            for (int i=0;i<=p;i++){
                num*=10;
                num+=path[i];
            }
            sum+=num;
        }
        dfs(root.left,path,p+1);
        dfs(root.right,path,p+1);
    }


    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recur(root, 0);
        return sum;
    }
    public void recur(TreeNode root, int number) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            number = number * 10 + root.val;
            sum += number;
            return;
        }
        number = number * 10 + root.val;
        recur(root.left, number);
        recur(root.right, number);
    }

    public static void main(String[] args) {

    }
}
