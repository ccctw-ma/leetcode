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
        String value;
        TreeNode left;
        TreeNode right;

        public TreeNode(String value) {
            this.value = value;
        }
    }


    public static int leaf(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leaf(root.left) + leaf(root.right);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while ((T--) > 0) {
            Deque<TreeNode> deque = new ArrayDeque<>();
            String next = in.next();
            List<String> list = new ArrayList<>();
            while (!next.equals("#")) {
                list.add(next);
                next = in.next();
            }
//            System.out.println(list);
            int index = 1;
            TreeNode root = new TreeNode(list.get(0));
            deque.add(root);
            while (!deque.isEmpty()) {
                int n = deque.size();
                for (int i = 0; i < n; i++) {
                    TreeNode temp = deque.removeFirst();
                    String left = list.get(index++);
                    String right = list.get(index++);
                    if (Objects.equals(left, "0")) {
                        temp.left = null;
                    } else {
                        temp.left = new TreeNode(left);
                        deque.add(temp.left);
                    }
                    if (right.equals("0")) {
                        temp.right = null;
                    } else {
                        temp.right = new TreeNode(right);
                        deque.add(temp.right);
                    }
                }
            }
            System.out.println(leaf(root));
        }

    }
}
