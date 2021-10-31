package Difficult.StringTest;


/*
* 336. 回文对
给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

示例 1:

输入: ["abcd","dcba","lls","s","sssll"]
输出: [[0,1],[1,0],[3,2],[2,4]]
解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
示例 2:

输入: ["bat","tab","cat"]
输出: [[0,1],[1,0]]
解释: 可拼接成的回文串为 ["battab","tabbat"]*/



import java.util.*;


/**
 * @author 马世臣
 * @// TODO: 2020/8/6  */



public class palindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {

        Map<String,Integer> map=new HashMap<>();
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<words.length;i++){
            if(isPalindrome(words[i])) list.add(i);
            map.put(words[i],i);
        }
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<words.length;i++){
            String s=words[i];
            if(s.length()==0){
                for (int k:list){
                    List<Integer> list1=new ArrayList<>();
                    list1.add(i);
                    list1.add(k);
                    List<Integer> list2=new ArrayList<>();
                    list2.add(k);
                    list2.add(i);
                    res.add(list1);
                    res.add(list2);
                }
            }
            String temp=reverse(s);
            if(map.containsKey(temp)&&!s.equals(temp)){
                List<Integer> item=new ArrayList<>();
                item.add(map.get(temp));
                item.add(i);
                res.add(item);
            }
            for (int j=1;j<s.length();j++){
                String l=s.substring(0,j);
                String r=s.substring(j);
                String rl=reverse(l);
                String rr=reverse(r);
                if(isPalindrome(l)&&map.containsKey(rr)){
                    List<Integer> item=new ArrayList<>();
                    item.add(map.get(rr));
                    item.add(i);
                    res.add(item);
                }
                if(isPalindrome(r)&&map.containsKey(rl)){
                    List<Integer> item=new ArrayList<>();
                    item.add(i);
                    item.add(map.get(rl));
                    res.add(item);
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s){
        if(s.length()==0) return false;
        int l=0,r=s.length()-1;
        while (l<r){
            if(s.charAt(l)==s.charAt(r)){
                l++;
                r--;
            }else {
                break;
            }
        }
        return l>=r;
    }

    private String reverse(String s){
        StringBuilder builder=new StringBuilder(s);
        return builder.reverse().toString();
    }

    public List<List<Integer>> palindromePairs2(String[] words) {
        //构建trie
        TrieNode root = buildTire(words);
        List<List<Integer>> res = new ArrayList<>();
        //对于每个单词,在trie中搜索
        for(int i = 0;i < words.length;i++){
            search(words[i],i,root,res);
        }
        return res;
    }

    private void search(String word,int i,TrieNode node,List<List<Integer>> res){
        int k = word.length(),j = 0;
        for(;j < k;j++){
            //循环中在trie中发现的单词是比当前word长度要短的
            char c = word.charAt(j);
            if(node.index != -1&&isPalidrome(word,j,k-1)){
                res.add(Arrays.asList(i,node.index));
            }
            //所有可能被排除，提前退出
            if(node.children[c-'a']==null) return;

            node = node.children[c-'a'];
        }
        //长度等于当前搜索的word的单词
        if(node.index != -1 && node.index != i){
            res.add(Arrays.asList(i,node.index));
        }
        //长度长于当前搜索的word的单词
        for(int rest : node.belowIsPali){
            res.add(Arrays.asList(i,rest));
        }
    }

    private TrieNode buildTire(String[] words){
        TrieNode root = new TrieNode();
        for(int i = 0;i<words.length;i++){
            addWord(root,words[i],i);
        }
        return root;
    }

    private void addWord(TrieNode root,String word,int i){
        for(int j = word.length()-1;j >= 0;j--){
            if(isPalidrome(word,0,j)){
                root.belowIsPali.add(i);
            }
            char c = word.charAt(j);
            if(root.children[c-'a'] == null){
                root.children[c-'a'] = new TrieNode();
            }
            root = root.children[c-'a'];
        }
        root.index = i;
    }

    private boolean isPalidrome(String word,int i,int j){
        if(word.length()<=1){
            return true;
        }
        while(i<j){
            if(word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }
    class TrieNode {
        int index;
        List<Integer> belowIsPali;
        TrieNode[] children;

        public TrieNode() {
            index = -1;
            belowIsPali = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        String[] words=new String[]{"a","b","c","ab","ac","aa"};
        System.out.println(new palindromePairs().palindromePairs(words));
    }
}
