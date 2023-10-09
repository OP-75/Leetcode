public class UniquePaths {

    public int uniquePaths(int m, int n) {

        memo = new int[m][n];
        return recursiveDP(m-1, n-1);
    }

    int[][] memo; 
    private int recursiveDP(int i, int j){
        if (i==0 && j==0) {
            return 1;
        }
        if (i<0 || j<0) {
            //out of bounds
            return 0;
        }

        if (memo[i][j]!=0) {
            return memo[i][j];
        }

        int numOfPaths = recursiveDP(i-1, j) + recursiveDP(i, j-1); 
        memo[i][j] = numOfPaths;

        return numOfPaths;
    }

}
