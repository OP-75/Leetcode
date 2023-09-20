public class DesignAddandSearchWordsDataStructure {
    
}

class TrieNode{
    TrieNode[] list = new TrieNode[26];
    boolean isEnd = false;

    public boolean contains(char c) {
        int indx = c - 'a';
        if (list[indx]==null) {
            return false;
        } else {
            return true;
        }
    }

    public void put(char c) {
        int indx = c - 'a';
        this.list[indx] = new TrieNode();
    }

    public TrieNode get(char c) {
        int indx = c - 'a';
        return this.list[indx];
    }
    

}

class WordDictionary {

    TrieNode root = null;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        
        TrieNode node = root;
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];

            if (!node.contains(c)) {
                node.put(c);
            }

            node = node.get(c);
        }

        node.isEnd = true;

    }
    
    public boolean search(String word) {
        //word can contain "." which means any character
        TrieNode node = root;
        return seacrchRecursive(word, node);
    }

    private boolean seacrchRecursive(String word, TrieNode node){

        if (word.isEmpty() && node.isEnd) {
            //ie it constains the word
            return true;
        }
        else if(word.isEmpty() && !node.isEnd){
            return false;
        }
        
        char c = word.charAt(0);

        if(c!='.' && node.contains(c)){
            node = node.get(c);
            return seacrchRecursive(word.substring(1), node);
        }
        else if(c!='.' && !node.contains(c)){
            return false;
        }
        else if(c=='.'){

            for (int i = 'a'; i <= 'z'; i++) {
                char potentialChar = (char)i;

                if (node.contains(potentialChar)) {
                    TrieNode tmpNodeToExplore = node.get(potentialChar);
                    boolean isMatch = seacrchRecursive(word.substring(1), tmpNodeToExplore);
                    if (isMatch) {
                        return true;
                    }
                    else{
                        //if '.' with potentialChar is not match then explore other chars
                        continue;
                    }
                }

            }

            //if we searched all the chars from a - z for "." then that means all a - z are null so return false
            return false;

        }

        //if all the above options have failed then there is something wrong with the code
        throw new IllegalStateException("All the if-else-if statement failed to execute"+word+" node-"+node);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */