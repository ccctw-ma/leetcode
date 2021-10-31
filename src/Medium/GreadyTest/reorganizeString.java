package Medium.GreadyTest;


/*
* 767. 重构字符串
给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

若可行，输出任意可行的结果。若不可行，返回空字符串。

示例 1:

输入: S = "aab"
输出: "aba"
示例 2:

输入: S = "aaab"
输出: ""
注意:

S 只包含小写字母并且长度在[1, 500]区间内。*/


import java.util.PriorityQueue;

/**
 * @author 马世臣
 * @// TODO: 2020/11/30  */


public class reorganizeString {

    public String reorganizeString(String S) {
        node[] arr=new node[26];
        for (int i=0;i<26;i++) {
            arr[i]=new node((char)(i+'a'),0);
        }
        int n=S.length();
        int max= n%2==0 ? n/2+1:(n+1)/2+1;
        for(char c:S.toCharArray()){
            arr[c-'a'].count++;
            if(arr[c-'a'].count==max) return "";
        }
        PriorityQueue<node> queue=new PriorityQueue<>((o1, o2) -> o2.count-o1.count);
        for (node node:arr){
            if(node.count!=0){
                queue.add(node);
            }
        }
        StringBuilder builder=new StringBuilder();
        char pre= ' ';
        while (n>0){
            node temp=queue.poll();
            if(temp.c!=pre){
                builder.append(temp.c);
                pre=temp.c;
                temp.count--;
            }else {
                node next=queue.poll();
                builder.append(next.c);
                pre=next.c;
                next.count--;
                if(next.count!=0){
                    queue.add(next);
                }
            }
            if(temp.count!=0) queue.add(temp);
            n--;
        }
        return builder.toString();
    }

    class node{
        int count;
        char c;
        public node(char c,int a){
            this.count=a;
            this.c=c;
        }
    }


    public static void main(String[] args) {

    }
}
