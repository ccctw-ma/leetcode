package Medium.BorD_FSTest;

import java.util.*;


/*
* 127. 单词接龙
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。*/

/**
 * @author 马世臣
 * @// TODO: 2020/6/7
 * */
public class ladderLength {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        int res=bfs(beginWord,endWord,wordList);
        return res+1;
    }

    private int bfs(String beginWord,String endWord,List<String> wordList){
        Queue<String> queue=new LinkedList<>();
        Set<String> set=new HashSet<>();
        set.add(beginWord);
        queue.add(beginWord);
        int step=0;
        while (queue.size()!=0){
            int n=queue.size();
            step++;
            for (int i=0;i<n;i++){
                String top=queue.poll();
                for(String s:wordList){
                    if(compare(top,s)==1&&!set.contains(s)){
                        if(s.equals(endWord)) return step;
                        queue.add(s);
                        set.add(s);
                    }
                }
            }
        }
        return -1;
    }

    private int compare(String a,String b){
        int count=0;
        for (int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                count++;
            }
        }
        return count;
    }



    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        //hashset的好处：去重也完成了
        //开始端
        HashSet<String> start = new HashSet<>();
        //结束端
        HashSet<String> end = new HashSet<>();
        //所有字符串的字典
        HashSet<String> dic = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        if (!dic.contains(endWord)) return 0;
        //经历过上面的一系列判定，到这里的时候，若是有路径，则最小是2，所以以2开始
        return bfs(start, end, dic, 2);

    }

    public int bfs(HashSet<String> st, HashSet<String> ed, HashSet<String> dic, int l) {
        //双端查找的时候，若是有任意一段出现了“断裂”，也就是说明不存在能够连上的路径，则直接返回0
        if (st.size() == 0) return 0;
        if (st.size() > ed.size()) {//双端查找，为了优化时间，永远用少的去找多的，比如开始的时候塞进了1000个，而结尾只有3个，则肯定是从少的那一端开始走比较好
            return bfs(ed, st, dic, l);
        }
        //BFS的标记行为，即使用过的不重复使用
        dic.removeAll(st);
        //收集下一层临近点
        HashSet<String> next = new HashSet<>();
        for (String s : st) {

            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char tmp = arr[i];
                //变化
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tmp == c) continue;
                    arr[i] = c;
                    String nstr = new String(arr);
                    if (dic.contains(nstr)) {
                        if (ed.contains(nstr)) return l;
                        else next.add(nstr);
                    }
                }
                //复原
                arr[i] = tmp;
            }
        }
        return bfs(next, ed, dic, l + 1);
    }


    public static void main(String[] args) {
        List<String> words=new ArrayList<>();
        words.add("hot");
        words.add("dog");
        System.out.println(new ladderLength().ladderLength("hot","dog",words));
    }
}
