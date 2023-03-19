package InterviewTest;

import java.util.Scanner;

/**
 * @author msc
 * @version 1.0
 * @date 2023/3/19 21:29
 */

class DSU {

    private int[] parent;
    public int cnt;

    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        cnt = n;
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void Union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[py] = px;
            cnt -= 1;
        }

    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}


public class mihayou {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            String s = sc.next();
            char[] ss = s.toCharArray();
            System.arraycopy(ss, 0, g[i], 0, n);
        }
//        System.out.println(Arrays.deepToString(g));
        DSU dsu1 = new DSU(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i < m - 1 && g[i][j] == g[i + 1][j]) {
                    dsu1.Union(i * n + j, (i + 1) * n + j);
                }
                if (j < n - 1 && g[i][j] == g[i][j + 1]) {
                    dsu1.Union(i * n + j, i * n + j + 1);
                }
            }
        }
//        System.out.println(dsu1.cnt);
        DSU dsu2 = new DSU(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i < m - 1 && (g[i][j] == g[i + 1][j] || (g[i][j] == 'G' && g[i + 1][j] == 'B') || (g[i][j] == 'B' && g[i + 1][j] == 'G'))) {
                    dsu2.Union(i * n + j, (i + 1) * n + j);
                }
                if (j < n - 1 && (g[i][j] == g[i][j + 1] || (g[i][j] == 'G' && g[i][j + 1] == 'B') || (g[i][j] == 'B' && g[i][j + 1] == 'G'))) {
                    dsu2.Union(i * n + j, i * n + j + 1);
                }
            }
        }
        System.out.println(dsu1.cnt - dsu2.cnt);
    }
}
