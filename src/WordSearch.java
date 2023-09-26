public class WordSearch {

    private boolean isOutOfBounds(char[][] board, int i, int j){
        return i<0 || j<0 || i>=board.length || j>=board[0].length;
    }

    private boolean backtrack(char[][] board, char[] word, boolean[][] isVisited, int wordPtr, int i, int j){
        
        if (wordPtr>=word.length) {
            return true;
        }

        if(!isOutOfBounds(board, i, j) && (isVisited[i][j] || board[i][j]!=word[wordPtr]) ){
            return false;
        }

        if (!isOutOfBounds(board, i, j) && board[i][j]==word[wordPtr]) {
            
            isVisited[i][j] = true;

            boolean wordIsPresent = backtrack(board, word, isVisited, wordPtr+1, i+1, j) || backtrack(board, word, isVisited, wordPtr+1, i, j+1) || backtrack(board, word, isVisited, wordPtr+1, i-1, j) || backtrack(board, word, isVisited, wordPtr+1, i, j-1);

            isVisited[i][j] = false;

            return wordIsPresent;
        }
        
        return false;
    }

    public boolean exist(char[][] board, String word) {

        char[] wordArr = word.toCharArray();
        boolean[][] isVisited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(backtrack(board, wordArr, isVisited, 0, i, j)){
                    return true;
                }
            }
        }

        return false;

    }

}
