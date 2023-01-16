package Match.match198;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class match198 {


    public int numWaterBottles(int numBottles, int numExchange) {
        int sum=0;
        int t=0;
        while (numBottles!=0){
            sum+=numBottles;
            t+=numBottles;
            numBottles=t/numExchange;
            t=t%numExchange;
        }
        return sum;
    }

    class node {
        int index;
        char ch;
        List<Integer> nexts;

        public node(int index, char ch){
            this.index=index;
            this.ch=ch;
            this.nexts=new ArrayList<>();
        }
    }


    private int[] res;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        node[] nodes=new node[n];
        for (int i=0;i<n;i++){
            nodes[i]=new node(i,labels.charAt(i));
        }

        for (int[] arr:edges){
            int a=arr[0];
            int b=arr[1];
            nodes[a].nexts.add(b);
            nodes[b].nexts.add(a);
        }
        res=new int[n];
        boolean[] visit=new boolean[n];
        dfs(nodes,nodes[0],visit);
        return res;
    }

    private int[] dfs(node[] nodes,node root,boolean[] visit){
        int index=root.index;
        visit[index]=true;
        List<Integer> list=new ArrayList<>(root.nexts);
        char ch=root.ch;
        int count=1;

        int[] chars=new int[26];
        chars[ch-'a']++;
        for (int n:list){
            if(visit[n]) continue;
            int[] arr=dfs(nodes,nodes[n],visit);
            count+=arr[ch-'a'];
            for (int i=0;i<26;i++){
                chars[i]+=arr[i];
            }
        }
        res[index]=count;
        return chars;
    }



    //too complex
    public int closestToTarget(int[] arr, int target) {
        int min=Integer.MAX_VALUE;
        int n=arr.length;
        int[][] bits=new int[n+1][32];
        for (int i=0;i<n;i++){
            int a=arr[i];
            int index=0;
            while (a!=0){
                bits[i+1][index]=((a&1)==1?1:0)+bits[i][index];
                a>>=1;
                index++;
            }
        }

        for (int i=0;i<n;i++){
            for (int j=i;j<n;j++){
                int temp=Math.abs(func(bits,arr,i,j)-target);
                if(temp==0) return 0;
                min=Math.min(min,temp);
            }
        }
        return min;
    }

    private int func(int[][] bits,int[] arr,int l,int r){
        if(l==r) return arr[l];
        int n=r-l+1;
        int res=0;
        for (int i=31;i>=0;i--){
            int a=bits[r+1][i]-bits[l][i];
            if(n==a) {
                res+=1;
            }
            res>>=1;
        }
        return res;
    }



    public static void main(String[] args) {
        Deque<Integer> stack=new ArrayDeque<>();
        System.out.println(new match198().closestToTarget(new int[]{1000000,1000000,1000000},1));
    }
}
