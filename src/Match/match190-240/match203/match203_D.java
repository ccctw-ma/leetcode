package Match.match203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class match203_D {



    public String thousandSeparator(int n) {
        if(n==0) return "0";
        StringBuilder builder=new StringBuilder();
        int index=0;
        while (n!=0){
            builder.append(n%10);
            n/=10;
            index++;
            if(index==3){
                builder.append('.');
                index=0;
            }
        }
        if(builder.charAt(builder.length()-1)=='.'){
            builder.delete(builder.length()-1,builder.length());
        }
        return builder.reverse().toString();


    }


    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] degrees=new int[n];
        for (List<Integer> list:edges){
            int a=list.get(0);
            int b=list.get(1);
            degrees[b]++;
        }
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<n;i++){
            if(degrees[i]==0){
                list.add(i);
            }
        }
        return list;
    }


    public int minOperations(int[] nums) {
        int n=nums.length;
        int[] arr1=new int[n];
        int[] arr2=new int[n];
        for (int i=0;i<n;i++){
            int temp=nums[i];
            while (temp!=0){
                if(temp%2==0){
                    temp/=2;
                    arr2[i]++;
                }else {
                    temp--;
                    arr1[i]++;
                }
            }
        }
        return Arrays.stream(arr1).sum()+Arrays.stream(arr2).max().getAsInt();
    }



    private int n;
    private int m;
    private char[][] grid;
    private boolean[][] visited;
    private boolean ans;
    public boolean containsCycle(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        this.grid = grid;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]){
                    continue;
                }
                dfs(i, j, -1, -1);
            }
        }
        return  ans;
    }

    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void dfs(int i, int j, int a, int b){
        if(visited[i][j]){
            ans = true;
            return;
        }
        visited[i][j] = true;
        for(int[] dir : dirs){
            int x = dir[0] + i;
            int y = dir[1] + j;
            if(x < 0 || y < 0 || x >= n || y >= m){
                continue;
            }
            if(x == a && y == b){
                continue;
            }
            if(grid[i][j] != grid[x][y]){
                continue;
            }
            dfs(x, y, i, j);
        }
    }


    public boolean containsCycle2(char[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        DSU dsu = new DSU(w * h);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                char cur = grid[i][j];
                // 向右搜索
                if (j + 1 < w && cur == grid[i][j + 1]) {
                    if (dsu.union(i * w + j, i * w + j + 1)) {
                        return true;
                    }
                }
                // 向下搜索
                if (i + 1 < h && cur == grid[i + 1][j]) {
                    if (dsu.union(i * w + j, (i + 1) * w + j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    class DSU {
        int[] parent;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * 若合并前，x和y的parent相同，则表示形成环，返回true。
         *
         * @param x
         * @param y
         * @return
         */
        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return true;
            }
            if (parentX < parentY) {
                parent[parentY] = parentX;
            } else {
                parent[parentX] = parentY;
            }
            return false;
        }
    }





    public static void main(String[] args) {
//        [["c","a","d"],["a","a","a"],["a","a","d"],["a","c","d"],["a","b","c"]]
        char[][] chars=new char[][]{{'a','b','b'},{'b','b','b'},{'b','b','a'}};
        System.out.println(new match203_D().containsCycle2(chars));
    }
}
