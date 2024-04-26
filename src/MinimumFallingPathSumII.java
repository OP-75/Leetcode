import java.util.Arrays;

class Solution {
    public int minFallingPathSum(int[][] grid) {
        int[][] minRowSumMemo = new int[grid.length][grid[0].length];
        
        getMinSum(grid,-1,-1, minRowSumMemo);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < minRowSumMemo[0].length; i++) {
            if (min>minRowSumMemo[0][i] && minRowSumMemo[0][i]!=0) {
                min = minRowSumMemo[0][i];
            }
        }
        return min;
    }

    private int getMinSum(int[][] grid, int prevR, int prevC, int[][] minRowSumMemo) {

        
        int currR = prevR+1;
        if (currR>=grid.length) {
            return 0;
        }
        int illegalC = prevC;

       


        int minSum = Integer.MAX_VALUE;
        for (int c = 0; c < grid[0].length; c++) {

            if (c==illegalC) {
                //skip this column
                continue;
            }

            int tmpSum;

            if (minRowSumMemo[currR][c]!=0) {
               tmpSum = minRowSumMemo[currR][c];
            }
            else{
                tmpSum = grid[currR][c] + getMinSum(grid, currR, c, minRowSumMemo);
                minRowSumMemo[currR][c] = tmpSum; // IMP!!! Store the sum for `currR` and `c`, REGARDLESS OF IF tmpSum<minSum
            }

            if (tmpSum<minSum) {
                minSum = tmpSum;
            }

        }


        
        return minSum;

    }
}