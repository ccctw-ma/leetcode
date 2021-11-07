package BUPT.OJ;


import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/22 23:27
 */
public class Main {


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

    private static TreeNode preTreeNode;

    public static TreeNode priorNode(TreeNode root) {
        if (root.leftType) return root.left;
        TreeNode pre = root.left;
        while (pre != null && !pre.rightType) {
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

    public static String findPriorAndNextNode(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.removeFirst();
                TreeNode pre = priorNode(temp);
                TreeNode next = nextNode(temp);
                builder.append(pre == null ? 0 : pre.value).append(" ");
                builder.append(next == null ? 0 : next.value).append(" ");
                if (temp.left != null && !temp.leftType) deque.add(temp.left);
                if (temp.right != null && !temp.rightType) deque.add(temp.right);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((T--) > 0) {
            Deque<TreeNode> deque = new ArrayDeque<>();
            int next = in.nextInt();
            List<Integer> list = new ArrayList<>();
            while (next != -1) {
                list.add(next);
                next = in.nextInt();
            }
            int index = 1;
            TreeNode root = new TreeNode(list.get(0));
            deque.add(root);
            while (!deque.isEmpty()) {
                int n = deque.size();
                for (int i = 0; i < n; i++) {
                    TreeNode temp = deque.removeFirst();
                    int left = list.get(index++);
                    int right = list.get(index++);
                    if (left == 0) {
                        temp.left = null;
                    } else {
                        temp.left = new TreeNode(left);
                        deque.add(temp.left);
                    }
                    if (right == 0) {
                        temp.right = null;
                    } else {
                        temp.right = new TreeNode(right);
                        deque.add(temp.right);
                    }
                }
            }
            preTreeNode = null;
            inThreadOrder(root);
            System.out.println(findPriorAndNextNode(root));

        }

    }
}
