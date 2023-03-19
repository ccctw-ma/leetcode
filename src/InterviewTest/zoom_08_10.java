package InterviewTest;

import java.io.BufferedInputStream;
import java.util.*;

public class zoom_08_10 {


    class DSUFind {

        int[] parent;
        int[] size;
        int setCount;

        public DSUFind(int n) {
            parent = new int[n];
            size = new int[n];
            setCount = n;
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            setCount--;
            return true;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }


    static class DSU {

        List<Integer> parent;
        List<Integer> size;
        Map<String, Integer> shareMap;
        int index;

        public DSU() {
            parent = new ArrayList<>();
            size = new ArrayList<>();
            shareMap = new HashMap<>();
            index = 0;
        }

        public int findShareIndex(String share) {
            if (!shareMap.containsKey(share)) {
                int res = index;
                parent.add(index);
                size.add(1);
                shareMap.put(share, index++);
                return res;
            } else {
                return shareMap.get(share);
            }
        }

        public int find(int x) {
            while (x != parent.get(x)) {
                parent.set(x, parent.get(parent.get(x)));
                x = parent.get(x);
            }
            return x;
        }

        public int getSize(int x) {
            return size.get(find(x));
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return false;
            }
            if (size.get(px) < size.get(py)) {
                int temp = px;
                px = py;
                py = temp;
            }
            parent.set(py, px);
            size.set(px, size.get(px) + size.get(py));
            return true;
        }

        public void unionList(List<Integer> shares) {
            if (shares.size() >= 2) {
                for (int i = 0; i < shares.size() - 1; i++) {
                    union(shares.get(i), shares.get(i + 1));
                }
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        Map<String, List<Integer>> nameMap = new HashMap<>();


        DSU dsu = new DSU();
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int q = Integer.parseInt(in.nextLine().trim());

        while ((--q) >= 0) {
            String[] temp = in.nextLine().trim().split(" ");
            String name = temp[1];
            // 注册
            if (temp[0].equals("1")) {
                int n = Integer.parseInt(temp[2]);
                String[] strs = in.nextLine().trim().split(" ");
                List<Integer> shares = new ArrayList<>();
                for (String str : strs) {
                    int index = dsu.findShareIndex(str);
                    shares.add(index);
                }
                nameMap.put(name, shares);
                dsu.unionList(shares);
            }
            // 查询
            else {
                if (!nameMap.containsKey(name)) {
                    System.out.println("error");
                } else {
                    List<Integer> shares = nameMap.get(name);
                    System.out.println(dsu.getSize(shares.get(0)) - shares.size());
                }
            }
        }
    }
}

