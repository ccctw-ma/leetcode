package Match.match190;

public class match190 {




//    int res;
//    public int pseudoPalindromicPaths (TreeNode root) {
//        res=0;
//        List<Integer> list=new ArrayList<>();
//        traverse(root,list);
//        return res;
//    }
//
//    public void traverse(TreeNode root, List<Integer> list){
//        if(root==null) return;
//        List<Integer> temp=new ArrayList<>(list);
//        temp.add(root.val);
//        if(root.left==null&&root.right==null){
//            System.out.println(temp);
//            if(isPalindrom(temp)){
//                res++;
//            }
//        }
//        if(root.left!=null){
//            traverse(root.left,temp);
//        }
//        if(root.right!=null){
//            traverse(root.right,temp);
//        }
//    }
//
//
//    public boolean isPalindrom(List<Integer> list){
//        int count=0;
//        int[] arr=new int[10];
//        for(int i:list){
//
//            arr[i]++;
//        }
//        for (int i:arr){
//            if((i&1)==1){
//                count++;
//            }
//        }
//        return count<=1;
//    }



    //动态规划的思想，没有必要使用回溯法。

    //dp[
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int max=Integer.MIN_VALUE;
        int a=nums1.length,b=nums2.length;
        int[][] dp=new int[a][b];
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                int u = nums1[i] * nums2[j];
                dp[i][j]=u;
                if(i>0) dp[i][j]=Math.max(dp[i][j],dp[i-1][j]);
                if(j>0) dp[i][j]=Math.max(dp[i][j],dp[i][j-1]);
                if(i>0&&j>0) dp[i][j]=Math.max(dp[i][j],dp[i-1][j-1]+u);
                max=Math.max(dp[i][j],max);
            }
        }
        return max;
    }




    public static void main(String[] args) {
        System.out.println(new match190()
                .maxDotProduct(new int[]{14,9,-19,5,-19,6,11,8,-1,12,11,-20,-10,-13,0,8,7,-4,-16,-11,17,-2,14,7,-4,-19,-8,-17,15,16,20,17,17,-10,17,16},
                new int[]{-7,-4,15,5,-19,8,-1,-1,12,-17,6,-10,-6,-13,7,-7,5,20,6,-13,-10,13,-16,-13,-6,15}));
    }



}
