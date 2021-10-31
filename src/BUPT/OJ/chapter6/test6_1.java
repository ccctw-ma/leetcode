package BUPT.OJ.chapter6;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class test6_1 {


    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static boolean judge(Random random) {
        return random.nextInt(10) >= 2;
    }

    public static TreeNode generateTree1(int num) {
        Random random = new Random();
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        deque.add(root);
        int index = 2;
        while (index <= num && !deque.isEmpty()) {
            int n = deque.size();
            for (int k = 0; k < n; k++) {
                TreeNode temp = deque.removeFirst();
                TreeNode left = judge(random) ? new TreeNode(index++) : null;
                TreeNode right = judge(random) ? new TreeNode(index++) : null;
                temp.left = left;
                temp.right = right;
                if (temp.left != null) deque.add(temp.left);
                if (temp.right != null) deque.add(temp.right);
            }
        }
        return root;
    }

    public static TreeNode generateTree2(int num) {
        Random random = new Random();
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        deque.add(root);
        int index = 2;
        while (index <= num) {
            int n = deque.size();
            for (int k = 0; k < n; k++) {
                TreeNode temp = deque.removeFirst();
                if (temp.value == 0) {
                    temp.left = new TreeNode(0);
                    temp.right = new TreeNode(0);
                } else {
                    TreeNode left = judge(random) ? new TreeNode(index++) : new TreeNode(0);
                    TreeNode right = judge(random) ? new TreeNode(index++) : new TreeNode(0);
                    temp.left = left;
                    temp.right = right;
                }
                deque.add(temp.left);
                deque.add(temp.right);
            }
        }
        return root;
    }

    public static int leaf(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leaf(root.left) + leaf(root.right);
    }

    public static String flattenTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        StringBuilder builder = new StringBuilder();
        builder.append(root.value).append(" ");
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.removeFirst();
                if (temp.left == null) {
                    builder.append(0).append(" ");
                } else {
                    builder.append(temp.left.value).append(" ");
                    deque.add(temp.left);
                }
                if (temp.right == null) {
                    builder.append(0).append(" ");
                } else {
                    builder.append(temp.right.value).append(" ");
                    deque.add(temp.right);
                }
            }
        }
        return builder.toString();
    }


    public static void createFile() {
        try {
            File inputFile = new File("src/BUPT/OJ/chapter6/test01.in");
            File outputFile = new File("src/BUPT/OJ/chapter6/test01.out");
            if (inputFile.exists()) inputFile.delete();
            if (outputFile.exists()) outputFile.delete();
            boolean inIsOK = inputFile.createNewFile();
            boolean outIsOK = outputFile.createNewFile();
            if (inIsOK && outIsOK) {
                FileWriter in = new FileWriter(inputFile);
                FileWriter out = new FileWriter(outputFile);
                Random random = new Random();

                in.write("50\n");
                for (int i = 0; i < 50; i++) {
                    // The tree node number;
                    int num = random.nextInt(100) + 2;
                    //generate a random tree
                    TreeNode root = generateTree1(num);
                    String res = flattenTree(root);
                    res += " -1\n";
                    in.write(res);
                    out.write(leaf(root) + "\n");
                }
                in.flush();
                out.flush();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        createFile();

//        int num = new Random().nextInt(10) + 2;
        //generate a random tree
//        TreeNode root = generateTree1(num);
//        flattenTree(root);
//        System.out.println();
//        int n = leaf(root);
//        System.out.println(n);
    }
}
