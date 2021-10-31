package Medium.String;

import java.util.*;


/*
* 面试题 17.13. 恢复空格
哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。

注意：本题相对原题稍作改动，只需返回未识别的字符数



示例：

输入：
dictionary = ["looked","just","like","her","brother"]
sentence = "jesslookedjustliketimherbrother"
输出： 7
解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
提示：

0 <= len(sentence) <= 1000
dictionary中总字符数不超过 150000。
你可以认为dictionary和sentence中只包含小写字母。?
* */


/**
 * @author 马世臣
 * @// TODO: 2020/6/24  */




public class respace {


    private int count;
    public int respace(String[] dictionary, String sentence) {
        Set<String> set=new HashSet<>();
        Map<Character, PriorityQueue<Integer>> map=new HashMap<>();
        count=Integer.MAX_VALUE;
        for(String s:dictionary){
            if(!set.contains(s)){
                set.add(s);
                map.putIfAbsent(s.charAt(0),new PriorityQueue<>((o1, o2) -> o2-o1));
                map.get(s.charAt(0)).add(s.length());
            }
        }
        search(sentence,0,0,set,map);
        return count;
    }

    private void search(String s,int index,int num,Set<String> set,Map<Character,PriorityQueue<Integer>> map){
        while (index<s.length()&&!map.containsKey(s.charAt(index))){
            num++;
            index++;
        }
        if(num>count) return;
        if(index<s.length()){
            boolean flag=false;
            char ch=s.charAt(index);
            for (int i:map.get(ch)){
                if(index+i<=s.length()){
                    String temp=s.substring(index,index+i);
                    if (set.contains(temp)) {
                        System.out.println(temp);
                        flag=true;
                        search(s,index+i,num,set,map);
                    }
                }
            }

            if(!flag) search(s,index+1,num+1,set,map);
        }else{
            count=Math.min(num,count);
        }

    }

    private trie root=new trie();
    class trie{
        trie[]child=new trie[26];
        boolean isend;
        trie(){
            isend=false;
        }
    }
    public int respace2(String[] dictionary, String sentence) {
        for(String word:dictionary){
            trie start=root;
            for(int i=word.length()-1;i>=0;i--){
                int index=word.charAt(i)-'a';
                if(start.child[index]==null){
                    start.child[index]=new trie();
                }
                if(i==0){
                    start.child[index].isend=true;
                }
                start=start.child[index];
            }
        }
        int[] dp = new int[sentence.length() + 1];
        for(int i=1;i<=sentence.length();i++){
            dp[i]=dp[i-1]+1;//最坏情况
            trie start=root;
            for(int j=i;j>=1;j--){
                int index=sentence.charAt(j-1)-'a';
                if(start.child[index]!=null){
                    if(start.child[index].isend){
                        dp[i]=Math.min(dp[i],dp[j-1]);
                    } else {
                        dp[i]=Math.min(dp[i],dp[j-1]+i-j+1);
                    }
                } else {
                    dp[i]=Math.min(dp[i],dp[j-1]+i-j+1);
                    break;
                }
                start=start.child[index];
            }
        }
        return dp[sentence.length()];
    }





    public int respace3(String[] dictionary,String sentence){
        Tries root=new Tries();

        for (String s:dictionary){
            root.insert(s);
        }

        int n=sentence.length();
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0]=0;
        for (int i=1;i<n;i++){
            dp[i]=dp[i-1]+1;
            Tries temp=root;
            for (int j=i;j>=1;j--){
                int index=sentence.charAt(j-1)-'a';
                if(temp.next[index].isLeaf){
                    dp[i]=Math.min(dp[i],dp[j-1]);
                }else if(temp.next[index]==null){
                    break;
                }
                if(dp[i]==0) break;
                temp=temp.next[index];
            }
        }
        return dp[n];
    }

    class Tries{
        Tries[] next;
        boolean isLeaf;

        public Tries(){
            next=new Tries[26];
            isLeaf=false;
        }

        public void insert(String s){
            Tries cur=this;
            for (int i=s.length()-1;i>=0;i--){
                int index=s.charAt(i)-'a';
                if(cur.next[index]==null){
                    cur.next[index]=new Tries();
                }
                cur=cur.next[index];
            }
            cur.isLeaf=true;
        }
    }


//["ouf","uucuocucoouoffcpuuf","pf","o","fofopupoufuofffffocpocfccuofuupupcouocpocoooupcuu","cf","cffooccccuoocpfupuucufoocpocucpuouofffpoupu","opoffuoofpupcpfouoouufpcuocufo","fopuupco","upocfucuucfucofufu","ufoccopopuouccupooc","fffu","uuopuccfocopooupooucfoufop","occ","ppfcuu","o","fpp","o","oououpuccuppuococcpoucccffcpcucoffupcoppoc","ufc","coupo","ufuoufofopcpfoufoouppffofffuupfco","focfcfcfcfpuouoccupfccfpcooup","ffupfffccpffufuuo","cufoupupppocou","upopupopccffuofpcopouofpoffopcfcuooocppofofuuc","oo","pccc","oupupcccppuuucuuouocu","fuop","ppuocfuppff","focofooffpfcpcupupuuooufu","uofupoocpf","opufcuffopcpcfcufpcpufuupffpp","f","opffp","fpccopc"]
//"fofopupoufuofffffocpocfccuofuupupcouocpocoooupcuufffufffufpccopc"
    public static void main(String[] args) {
        String[] strings=new String[]{"tttttt","ttttttttttt","ttt","ttttttttttttttt","tttttttttttttttt","t","ttttttttttttttttttttttttttttttttttttttttttttttttttt","t","ttttttt","ttttttttt","ttttttttt","tttt","tttttttttt","tttt","ttttttttttttttttttttttttttttttttt","t"};
        System.out.println(new respace().respace2(strings,"tttttttttttttttttttttttttttttttttttttttttttt"));
    }
}
