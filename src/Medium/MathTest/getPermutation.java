package Medium.MathTest;


/*
* 60. 第k个排列
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"*/

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马世臣
 * @// TODO: 2020/9/5
 * */

public class getPermutation {



    //超时了
    private String res;
    private int k;
    public String getPermutation2(int n, int k) {
        res="";
        this.k=k;
        boolean[] visited=new boolean[n];
        trace(visited,new StringBuilder());
        return  res;
    }

    private void trace(boolean[] visited,StringBuilder builder){
        if(builder.length()==visited.length){
            k--;
            if(k==0) res=builder.toString();
            return;
        }
        for (int i=0;i<visited.length;i++){
            if(!visited[i]){
                visited[i]=true;
                builder.append(i+1);
                trace(visited,new StringBuilder(builder));
                builder.delete(builder.length()-1,builder.length());
                visited[i]=false;
            }
        }
    }

    public String getPermutation(int n, int k) {
        List<Integer> list=new ArrayList<>();
        for (int i=1;i<=n;i++) list.add(i);
        int[] div=new int[10];
        int sum=1;
        div[0]=sum;
        for (int i=1;i<10;i++){
            sum*=i;
            div[i]=sum;
        }
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<n;i++){
            int temp;
            if(k==0){
                temp=0;
            }else {
                temp=k/div[n-i-1];
                if(temp*div[n-i-1]==k)  temp--;
                k-=(temp*div[n-i-1]);
            }
            builder.append(list.get(temp));

            list.remove(temp);
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(new getPermutation().getPermutation(9,296662));
    }
}

