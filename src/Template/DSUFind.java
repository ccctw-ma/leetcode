package sort;

import java.util.Arrays;

public class DSUFind {

    int[] parent;
    int[] size;
    int setCount;
    public DSUFind(int n){
        parent = new int[n];
        size = new int[n];
        setCount = n;
        Arrays.fill(size,1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x){
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public boolean union(int x,int y){
        int px = find(x);
        int py = find(y);
        if(px==py){
            return false;
        }
        if(size[x]<size[y]){
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] +=size[y];
        setCount--;
        return true;
    }

    public boolean isConnected(int x,int y){
        return find(x) == find(y);
    }
}
