package InterviewTest;

import java.util.*;

public class huawei_04_06 {

    public static void test02(int N, int M, List<List<Integer>> depend) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> s = new HashSet<>();
        List<Set<Integer>> depends = new ArrayList<>();
        for (List<Integer> integers : depend) {
            Set<Integer> set = new HashSet<>(integers);
            depends.add(set);
        }
        visited.add(M);
        s.add(M);
        while (!s.isEmpty()) {
            Set<Integer> temp = new HashSet<>();
            for (Integer integer : s) {
                Set<Integer> dep = depends.get(integer);
                for (Integer next : dep) {
                    if (visited.contains(next)) {
//                        存在循环依赖
                        System.out.println("-1");
                        return;
                    } else {
                        temp.add(next);
                        visited.add(next);
                    }
                }
            }
            s = temp;
        }
        visited.remove(M);
        if (visited.isEmpty()) {
            System.out.println("null");
        } else {
            List<Integer> res = new ArrayList<>(visited);
            Collections.sort(res);
            StringBuilder builder = new StringBuilder();
            for (Integer re : res) {
                builder.append(re).append(",");
            }
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
            System.out.println(builder);
        }
    }


    public static void test03(int N, int M, int[] arr) {
        int l = 0, r;
        int res = 0;
        while (l < M) {
            while (l < M && arr[l] >= 0) l++;
            r = l;
            while (r < M && arr[r] < 0) r++;
            if ((r - l) >= N) {
                // [l, r)
                res += operation(arr, N, l, r);
            }
            l = r;
        }
        System.out.println(res);
    }



    public static int operation(int[] arr, int N, int left, int right) {
        int[] depths = new int[right - left];
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < N; i++) {
            queue.add(arr[left + i]);
        }
        depths[N - 1] = Math.abs(queue.peek());
        for (int i = N; i < (right - left); i++) {
            queue.remove(arr[left + i - N]);
            queue.add(arr[left + i]);
            depths[i] = Math.abs(queue.peek());
        }
        int max = depths[N - 1];
        for (int i = N; i < depths.length; i++) {
            depths[i] = Math.max(depths[i], depths[i] + depths[i - N]);
            max = Math.max(max, depths[i]);
        }
        return max;
    }


    static class MyQueue {
        private final Deque<Integer> deque;

        public MyQueue() {
            deque = new ArrayDeque<>();
        }

        public void push(int x) {
            while (!deque.isEmpty() && x > deque.getLast()) {
                deque.removeLast();
            }
            deque.addLast(x);
        }

        public void pop(int x) {
            if (!deque.isEmpty() && x == deque.getFirst()) {
                deque.removeFirst();
            }
        }

        public int front() {
            return deque.getFirst();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> depend = new ArrayList<>();
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        List<Integer> arr3 = new ArrayList<>();
        List<Integer> arr4 = new ArrayList<>();
        arr2.add(0);
        arr3.add(1);
        arr4.add(0);
        arr4.add(1);
        depend.add(arr1);
        depend.add(arr2);
        depend.add(arr3);
        depend.add(arr4);

//        List<List<Integer>> depend = new ArrayList<>();
//        List<Integer> arr1 = new ArrayList<>();
//        List<Integer> arr2 = new ArrayList<>();
//
//        arr1.add(1);
//        arr2.add(0);
//
//        depend.add(arr1);
//        depend.add(arr2);
//
//
//        test02(4, 2, depend);

//        Queue<Integer> queue = new PriorityQueue<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        queue.remove(1);
//        System.out.println(queue.peek());

        test03(2, 8, new int[]{0, -1, -2, -2, -1, -1, -1, -2});
    }
}
