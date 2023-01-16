package sort;



/*并查集模板*/

/**
 * @author 马世臣
 * @// TODO: 2020/9/2  */
public class DSU {

    private int[] parent;
    public DSU(int n){
        parent=new int[n];
        for (int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int find(int x){
        while (x!=parent[x]){
            parent[x]=parent[parent[x]];
            x=parent[x];
        }
        return x;
    }

    public void Union(int x,int y){
        int px=find(x);
        int py=find(y);
        if(px<py){
            parent[py]=px;
        }else {
            parent[px]=py;
        }
    }

    public boolean isConnected(int x,int y) {
        return find(x)==find(y);
    }
}


