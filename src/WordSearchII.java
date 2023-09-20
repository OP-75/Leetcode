import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class TrieNode {
    TrieNode[] list = new TrieNode[26];
    boolean isEnd = false;

    public boolean contains(char c) {
        int indx = c - 'a';
        if (list[indx] == null) {
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

public class WordSearchII {

    TrieNode root = null;

    public WordSearchII() {
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

    private boolean isWithinBounds(char[][] board, int i, int j) {
        if (i < 0 || j < 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;

        if (i > m - 1 || j > n - 1) {
            return false;
        }

        return true;
    }

    HashSet<String> answerSet = new HashSet<>();

    private String backtrack(char[][] board, int i, int j, boolean[][] isVisted, String currWord, TrieNode node) {

        if (node.isEnd) {
            answerSet.add(currWord);
            
        }
        if (!isWithinBounds(board, i, j) || isVisted[i][j]) {
            return null;
        }

        //mark current node as visited before expolring/DFS to avoid going in loop
        isVisted[i][j] = true;

        char c = board[i][j];

        if (node.contains(c)) {

            currWord += c;
            
            backtrack(board, i + 1, j, isVisted, currWord, node.get(c));
            
            backtrack(board, i, j + 1, isVisted, currWord, node.get(c));
            
            backtrack(board, i - 1, j, isVisted, currWord, node.get(c));
        
            backtrack(board, i, j - 1, isVisted, currWord, node.get(c));
            
        }
        
        //mark current node as unvisited since we are done expolring it
        isVisted[i][j] = false;
        return null;

    }

    public List<String> findWords(char[][] board, String[] words) {

        // first add all words in Trie
        for (String string : words) {
            addWord(string);
        }

        int m = board.length, n = board[0].length;
        // List<String> answerArray = new ArrayList<>();
        boolean[][] isVisted = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                TrieNode node = root;
                backtrack(board, i, j, isVisted, "", node);
                
            }
        }

        List<String> answerArray = new ArrayList<>();
        for (String string : answerSet) {
            answerArray.add(string);
        }
        return answerArray;
    }

    public static void main(String[] args) {
        WordSearchII solution = new WordSearchII();

        char[][] board1 = {
                { 'o', 'a', 'a', 'n' },
                { 'e', 't', 'a', 'e' },
                { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' }
        };
        String[] words1 = { "oath", "pea", "eat", "rain" };
        List<String> result1 = solution.findWords(board1, words1);
        System.out.println("Result 1: " + Arrays.toString(result1.toArray())); // Output should be ["eat", "oath"]

        solution = new WordSearchII();
        char[][] board2 = {
                { 'a', 'b' },
                { 'c', 'd' }
        };
        String[] words2 = { "abcb" };
        List<String> result2 = solution.findWords(board2, words2);
        System.out.println("Result 2: " + Arrays.toString(result2.toArray())); // Output should be []

        solution = new WordSearchII();
        char[][] board3 = {
                { 'o', 'a', 'b', 'n' },
                { 'o', 't', 'a', 'e' },
                { 'a', 'h', 'k', 'r' },
                { 'a', 'f', 'l', 'v' }
        };
        String[] words3 = { "oa", "oaa" };
        List<String> result3 = solution.findWords(board3, words3);
        System.out.println("Result 3: " + Arrays.toString(result3.toArray())); // Output should be ["oa", "oaa"]

        solution = new WordSearchII();
        char[][] board4 = {
                { 'a', 'b', 'c', 'e' },
                { 'x', 'x', 'c', 'd' },
                { 'x', 'x', 'b', 'a' }
        };
        String[] words4 = { "abc", "abcd" };
        List<String> result4 = solution.findWords(board4, words4);
        System.out.println("Result 4: " + Arrays.toString(result4.toArray())); // Output should be ["abc", "abcd"]

        // Add more test cases as needed
    }

}
