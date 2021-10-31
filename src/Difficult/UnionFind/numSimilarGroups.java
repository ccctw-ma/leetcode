package Difficult.UnionFind;


/*
* 839. 相似字符串组
如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？



示例 1：

输入：strs = ["tars","rats","arts","star"]
输出：2
示例 2：

输入：strs = ["omv","ovm"]
输出：1


提示：

1 <= strs.length <= 100
1 <= strs[i].length <= 1000
sum(strs[i].length) <= 2 * 104
strs[i] 只包含小写字母。
strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。


备注：

      字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。*/

import java.util.Arrays;

/**
 * @author 马世臣
 * @// TODO: 2021/1/31
 * */


public class numSimilarGroups {

    public int numSimilarGroups(String[] strs) {
        int len = strs[0].length();
        int n = strs.length;
        if(len<=2) return 1;
        DSU dsu = new DSU(n);
        for (int i=0;i<n-1;i++){
            for (int j=i+1;j<n;j++){
                if(isAnagram(strs[i],strs[j])){
                    dsu.union(i,j);
                }
            }
        }
        return dsu.count;
    }

    private boolean isAnagram(String a,String b){
        int count = 0;
        for (int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                count++;
            }
        }
        return count == 2 || count == 0;
    }

    class DSU{
        int[] parent;
        int[] size;
        int count;

        public DSU(int n){
            parent = new int[n];
            for (int i=0;i<n;i++){
                parent[i] = i;
            }
            size = new int[n];
            Arrays.fill(size,1);
            count = n;
        }

        public int find(int x){
            while (x!=parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return parent[x];
        }

        public boolean union(int x,int y){
            x = find(x);
            y = find(y);
            if(x == y) return false;
            if(size[x]<size[y]){
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            count--;
            return true;

        }

        public boolean isConnected(int x,int y){
            return find(x) == find(y);
        }
    }


    public static void main(String[] args) {

    }
}
