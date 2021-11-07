package BUPT.OJ.chapter6;


import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class test6_3 {


    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        // false 表示为子节点，true表示为线索节点
        boolean leftType;
        boolean rightType;

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


    private static TreeNode preTreeNode;

    //中序线索化二叉树
    public static void inThreadOrder(TreeNode root) {
        if (root == null) return;
        inThreadOrder(root.left);
        if (root.left == null) {
            root.left = preTreeNode;
            root.leftType = true;
        }
        if (preTreeNode != null && preTreeNode.right == null) {
            preTreeNode.right = root;
            preTreeNode.rightType = true;
        }
        preTreeNode = root;
        inThreadOrder(root.right);
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

//    public static List<Integer> generateTest(int num) {
//        Random random = new Random();
//        int n = num / 10 + 1;
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            int temp = random.nextInt(num) + 1;
//            while (set.contains(temp)) {
//                temp = random.nextInt(num) + 1;
//            }
//            set.add(temp);
//        }
//        List<Integer> res = new ArrayList<>(set);
//        res.sort(Comparator.comparingInt(o -> o));
//        return res;
//    }

    public static String findPriorAndNextNode(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int n = deque.size();
            for (int i=0;i<n;i++){
                TreeNode temp = deque.removeFirst();
                TreeNode pre = priorNode(temp);
                TreeNode next = nextNode(temp);
                builder.append(pre == null ? 0 : pre.value).append(" ");
                builder.append(next == null ? 0 : next.value).append(" ");
                if(temp.left!=null&&!temp.leftType) deque.add(temp.left);
                if(temp.right!=null&&!temp.rightType) deque.add(temp.right);
            }
        }
        return builder.toString();
    }

//    public static void inOrderTraversal(TreeNode root, List<Integer> list, int[] arr) {
//        if (root == null) return;
//        inOrderTraversal(root.left, list, arr);
//        int index = list.indexOf(root.value);
//        if (index >= 0) {
//            int priorIndex = index * 2;
//            int nextIndex = index * 2 + 1;
//            TreeNode pre = priorNode(root);
//            TreeNode next = nextNode(root);
//            arr[priorIndex] = pre == null ? 0 : pre.value;
//            arr[nextIndex] = next == null ? 0 : next.value;
//        }
//        inOrderTraversal(root.right, list, arr);
//    }

    public static TreeNode priorNode(TreeNode root) {
        if (root.leftType) return root.left;
        TreeNode pre = root.left;
        while (pre!=null&&!pre.rightType) {
            pre = pre.right;
        }
        return pre;
    }

    public static TreeNode nextNode(TreeNode root) {
        if (root.rightType) return root.right;
        TreeNode next = root.right;
        while (next != null && !next.leftType) {
            next = next.left;
        }
        return next;
    }


    public static void createFile() {
        try {
            File inputFile = new File("src/BUPT/OJ/chapter6/test03.in");
            File outputFile = new File("src/BUPT/OJ/chapter6/test03.out");
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
                    String s = flattenTree(root);
//                    System.out.println(s);
                    in.write(s + " -1\n");
                    preTreeNode = null;
                    inThreadOrder(root);
                    String res = findPriorAndNextNode(root);
//                    System.out.println(res);
                    out.write(res + "\n");
                    in.flush();
                    out.flush();
                }
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
//        System.out.println(generateTest(10));
    }
}
