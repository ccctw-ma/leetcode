package Difficult.ArrayTest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class findAllPeople {

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        ArrayList<int[]>[] lists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] meeting : meetings) {
            lists[meeting[0]].add(new int[] { meeting[1], meeting[2] });
            lists[meeting[1]].add(new int[] { meeting[0], meeting[2] });
        }
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((o, p) -> o[1] - p[1]);
        for (queue.addAll(Set.of(new int[2], new int[] { firstPerson, 0 })); !queue.isEmpty();) {
            int[] poll = queue.poll();
            if (lists[poll[0]] != null) {
                list.add(poll[0]);
                for (int[] i : lists[poll[0]]) {
                    if (i[1] >= poll[1]) {
                        queue.offer(i);
                    }
                }
                lists[poll[0]] = null;
            }
        }
        return list;
    }
}
