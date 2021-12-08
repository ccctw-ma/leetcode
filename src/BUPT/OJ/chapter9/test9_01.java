package BUPT.OJ.chapter9;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class test9_01 {


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
        Set<Integer> set = new HashSet<>();
        while (set.size() != num) set.add(random.nextInt(1000));
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
//        System.out.println(Arrays.toString(arr));
        return dfs(arr, 0, arr.length - 1);
    }

    public static TreeNode dfs(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = dfs(arr, l, mid - 1);
        root.right = dfs(arr, mid + 1, r);
        return root;
    }


    public static int leaf(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leaf(root.left) + leaf(root.right);
    }


    public static int getLevel(TreeNode root, int level, int target) {
        if (root.value == target) {
            return level;
        } else if (root.value > target) {
            if (root.left == null) {
                return -1;
            }
            return getLevel(root.left, level + 1, target);
        } else {
            if (root.right == null) {
                return -1;
            }
            return getLevel(root.right, level + 1, target);
        }
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
            File inputFile = new File("src/BUPT/OJ/chapter9/test01.in");
            File outputFile = new File("src/BUPT/OJ/chapter9/test01.out");
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
                    int num = random.nextInt(100) + 10;
                    //generate a random tree
                    System.out.println(num);
                    Set<Integer> set = new HashSet<>();
                    while (set.size() != num) set.add(random.nextInt(200)+1);
                    List<Integer> list = new ArrayList<>(set);
                    Collections.sort(list);
                    int[] arr = new int[list.size()];
                    for (int j = 0; j < arr.length; j++) {
                        arr[j] = list.get(j);
                    }
                    TreeNode root = dfs(arr, 0, arr.length - 1);
                    int target = arr[random.nextInt(arr.length)];

                    boolean isOk = judge(random);
                    target = target + (isOk ? 0 : 1);
                    in.write(target+"\n");
                    String res = flattenTree(root);
                    res += " -1\n";
                    in.write(res);

//                    out.write(getLevel(root, 1, target) + " "+isOk+"\n");
                    out.write(getLevel(root, 1, target) +"\n");
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
//        generateTree1(100);
    }


}



