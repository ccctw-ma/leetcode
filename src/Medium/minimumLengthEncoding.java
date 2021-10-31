package Medium;

import java.util.*;

public class minimumLengthEncoding {


    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length()-o1.length());
        if(words.length==1) return words[0].length()+1;
        int sum=words[0].length()+1;
        for (int i=1;i<words.length;i++){
            String b=words[i];
            boolean flag=false;
            for (int j=i-1;j>=0;j--){
                String a=words[j];
                if(a.indexOf(b)+b.length()==a.length()){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                sum+=words[i].length()+1;
            }
        }
        return sum;
    }

    //使用hashset,较为节省时间
    public int minimumLengthEncoding2(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }
        System.out.println(set);
        int ans = 0;
        for (String word : set) {
            ans += word.length() + 1;
        }
        return ans;
    }


    //使用字典树，从尾到头对字符串进行统计
    public int minimumLengthEncoding3(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j)
                cur = cur.get(word.charAt(j));
            nodes.put(cur, i);
        }
        int ans = 0;
        for (TrieNode node: nodes.keySet()) {
            if (node.count == 0)
                ans += words[nodes.get(node)].length() + 1;
        }
        return ans;
    }


    class TrieNode {
        TrieNode[] children;
        int count;
        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }

    public static void main(String[] args) {
//        System.out.println("time".indexOf("me"));
    }

}
