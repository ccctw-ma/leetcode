package Medium.UnionFindTest;


/**
 * 959. 由斜杠划分区域
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 *
 * 返回区域的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 * 解释：2x2 网格如下：
 *
 * 示例 2：
 *
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 * 解释：2x2 网格如下：
 *
 * 示例 3：
 *
 * 输入：
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 2x2 网格如下：
 *
 * 示例 4：
 *
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 2x2 网格如下：
 *
 * 示例 5：
 *
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 * 解释：2x2 网格如下：
 *
 *
 *
 * 提示：
 *
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2021/1/25  */


public class regionsBySlashes {


    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSUFind dsuFind = new DSUFind(4*n*n);
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                char tar = grid[i].charAt(j);
                int index = i*n+j;
                int a =4*index;
                int b =4*index+1;
                int c =4*index+2;
                int d =4*index+3;
                if(tar==' '){
                    dsuFind.union(a,b);
                    dsuFind.union(c,d);
                    dsuFind.union(a,c);
                }else if(tar=='/'){
                    dsuFind.union(a,d);
                    dsuFind.union(b,c);
                }else {
                    dsuFind.union(a,b);
                    dsuFind.union(c,d);
                }
                if(i>0){
                    int t = 4*((i-1)*n+j)+2;
                    dsuFind.union(a,t);
                }
                if(j>0){
                    int l = 4*(i*n+j-1)+1;
                    dsuFind.union(d,l);
                }
            }
        }
        return dsuFind.setCount;
    }


    class DSUFind {

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
            while (x!=parent[x]){
                parent[x]=parent[parent[x]];
                x=parent[x];
            }
            return parent[x];
        }

        public boolean union(int x,int y){
            x = find(x);
            y = find(y);
            if(x==y){
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
    }

    public static void main(String[] args) {
        String[] grid = new String[]{"/\\","\\/"};
        System.out.println(new regionsBySlashes().regionsBySlashes(grid));
    }
}
