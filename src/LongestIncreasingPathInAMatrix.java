public class LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        return mySolution(matrix);
    }

    private int mySolution(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, recursiveDP(matrix, i, j, isVisited, memo)); //this is nothing but DFS
            }
        }

        return max;
    }

    private int recursiveDP(int[][] matrix, int i, int j, boolean[][] isVisited, int[][] memo) {
        

        if (isWithinBounds(i, j, matrix) && memo[i][j]!=0) {
            return memo[i][j];
        }

        //MARKING isVisited[i][j] = true IS VERYYY IMPORTANT TO PREVENT THE DFS FROM GOING IN LOOPS (ALSO MARK IT AS isVisited[i][j] = false before returning so we can run DFS for other cells in matrix) 
        isVisited[i][j] = true;

        int maxPathLen = 0;

        int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        for (int[] move : directions) {
            int newR = i+move[0];
            int newC = j+move[1];

            if (isWithinBounds(newR, newC, matrix) && matrix[newR][newC]>matrix[i][j] && !isVisited[newR][newC]) {
                maxPathLen = Math.max(maxPathLen, recursiveDP(matrix, newR, newC, isVisited, memo));
            }
        }


        memo[i][j] = maxPathLen+1;
        isVisited[i][j] = false;
        return maxPathLen+1;


    }

    private boolean isWithinBounds(int newR, int newC, int[][] matrix) {
        return newR>=0 && newC>=0 && newR<matrix.length && newC<matrix[0].length;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix o = new LongestIncreasingPathInAMatrix();
        int[][] a = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(o.longestIncreasingPath(a));
    }



}
