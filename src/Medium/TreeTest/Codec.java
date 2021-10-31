package Medium.TreeTest;

public class Codec {


    public int i = 0;

    // Encodes a tree to a single string.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder(  );
        toString(root ,sb);
        return sb.toString();
    }

    static void toString(TreeNode node,StringBuilder sb){
        if(node == null){
            return;
        }
        toString(node.left,sb);
        toString(node.right,sb);
        sb.append((char) node.val);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("".equals( data )){
            return null;
        }

        char[] datas =  data.toCharArray();
        i = datas.length -1;
        return toTree(datas,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    TreeNode toTree( char[] datas, Integer min,Integer max){
        if(i < 0){
            return null;
        }
        char val = datas[i];
        if(val < min || val > max){
            return null;
        }
        i--;

        TreeNode node = new TreeNode(val);
        node.right = toTree(datas,node.val,max);
        node.left = toTree(datas,min,node.val);
        return node;
    }

    public static void main(String[] args) {
        StringBuilder builder=new StringBuilder();
        builder.append(10000);
        builder.append((char)520);
        System.out.println(builder.toString());
    }
}
