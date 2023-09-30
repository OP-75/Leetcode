import java.util.Arrays;

public class SurroundedRegions {
    
    boolean[] isSurrounded; //this is arr of 4 , representing if the o's are surrounded in east west north south.
    boolean[][] isVisited;
    boolean isOnEdge; //if we have encountered an Edge during exploration the patch of O's CAN NEVER BE CONSIDERED SURROUNDED
    private boolean explore(char[][] board, int i, int j, boolean shouldTurnToX){

        if (isOutOfBounds(board, i, j) || isVisited[i][j]) {
            if (isOutOfBounds(board, i, j)) {
                isOnEdge = true;
            }
            return false;
        }
        else if (board[i][j]=='X') {
            return true;
        }

        isVisited[i][j] = true;
        if (shouldTurnToX) {
            board[i][j] = 'X';
        }


        if (explore(board, i, j+1, shouldTurnToX) && !isOnEdge) { 
            // isOnEdge will be checked AFTER explore() is run caz of &&
            //east
            isSurrounded[0] = true;
        }
        if (explore(board, i, j-1, shouldTurnToX) && !isOnEdge) {
            //west
            isSurrounded[1] = true;
        }
        if (explore(board, i-1, j, shouldTurnToX)&& !isOnEdge) {
            //north
            isSurrounded[2] = true;
        }
        if (explore(board, i+1, j, shouldTurnToX)&& !isOnEdge) {
            //south
            isSurrounded[3] = true;
        }


        return false;        

    }

    private boolean isOutOfBounds(char[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

    private void mySolution(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                isVisited = new boolean[board.length][board[0].length];
                isSurrounded = new boolean[4];
                isOnEdge = false;
                explore(board, i, j, false);
                if (isAllTrue(isSurrounded) && !isOnEdge) {
                    isVisited = new boolean[board.length][board[0].length];
                    explore(board, i, j, true);
                }
            }
        }

    }
    
    private boolean isAllTrue(boolean[] arr){
        for (boolean b : arr) {
            if (!b) {
                return false;
            }
        }
        return true;
    } 
    


    //below is the neetcode solution
    boolean[][] isConnectedToBorder;
    private void markAllConnected(char[][] board, int i, int j){

        if (isOutOfBounds(board, i, j) || isConnectedToBorder[i][j] || board[i][j]=='X') {
            //if isConnectedToBorder[i][j]==true then that means curr node was previously visited
            return;
        }

        isConnectedToBorder[i][j] = true;

        markAllConnected(board, i, j+1);
        markAllConnected(board, i, j-1);
        markAllConnected(board, i+1, j);
        markAllConnected(board, i-1, j);

    }

    private void neetcodeSolution(char[][] board){
        
        isConnectedToBorder = new boolean[board.length][board[0].length];

        for (int i = 0; i < board[0].length; i++) {
            markAllConnected(board, 0, i);
            markAllConnected(board, board.length-1, i);
        }

        for (int i = 0; i < board.length; i++) {
            markAllConnected(board, i, 0);
            markAllConnected(board, i, board[0].length-1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!isConnectedToBorder[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }




    }
    
    public void solve(char[][] board) {
        // mySolution(board);
        neetcodeSolution(board);
    }

    

}