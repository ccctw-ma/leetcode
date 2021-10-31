package Medium.DesignTest;

public class Trie {

    private Trie[] children;
    private boolean isEnd;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (trie.children[c - 'a'] == null) {
                trie.children[c - 'a'] = new Trie();
            }
            trie = trie.children[c - 'a'];
        }
        trie.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String prefix) {
        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (trie.children[c - 'a'] == null)
                return null;
            trie = trie.children[c - 'a'];
        }
        return trie;
    }


}


