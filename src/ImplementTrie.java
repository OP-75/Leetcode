class TrieNode {
    TrieNode[] links = new TrieNode[26];
    boolean isEnd = false;


    public boolean contains(char c) {
        int indx = c - 'a';
        if(links[indx]==null){
            return false;
        }
        else{
            return true;
        }
    }


    public void put(char c) {
        int indx = c - 'a';
        links[indx] = new TrieNode();
    }


    public TrieNode get(char c) {
        int indx = c - 'a';
        return links[indx];
    }


    public void setAsEnd() {
        this.isEnd = true;
    }
}

class Trie {

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode node = root;
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];

            if(!node.contains(c)){
                node.put(c);
            }

            node = node.get(c);
        }

        //now we are at last node/ all chars are inserted
        node.setAsEnd();
    }

    public boolean search(String word) {

        TrieNode node = root;
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];

            if (!node.contains(c)) {
                return false;
            }

            node = node.get(c);
        }

        //IMP for Trie last node should be set as true, ie isEnd should be set as true to indicate that its the end of the word
        if (node.isEnd) {
            return true;
        }
        else{
            //if its not the end of the word then that means we were only given a prefix of some word
            return false;
        }

    }

    public boolean startsWith(String prefix) {

        TrieNode node = root;
        char[] prefixArr = prefix.toCharArray();

        for (int i = 0; i < prefixArr.length; i++) {
            char c = prefixArr[i];
            if (!node.contains(c)) {
                return false;
            }

            node = node.get(c);
        }

        //if we havent returned false yet that means we have all the chars of the prefix
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

public class ImplementTrie {

}
