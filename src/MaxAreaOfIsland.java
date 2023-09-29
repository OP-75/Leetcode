public class MaxAreaOfIsland {

    // doing DFS on the island
    int maxArea = 0, currArea = 0;
    private void explore(int[][] grid, boolean[][] isExplored, int i, int j) {
        if (isOutOfBounds(grid, i, j) || grid[i][j] == 0 || isExplored[i][j]) {
            return;
        }

        isExplored[i][j] = true;
        currArea++;

        explore(grid, isExplored, i + 1, j);
        explore(grid, isExplored, i, j + 1);
        explore(grid, isExplored, i - 1, j);
        explore(grid, isExplored, i, j - 1);

        if (currArea>maxArea) {
            maxArea = currArea;
        }

    }

    private boolean isOutOfBounds(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

    public int maxAreaOfIsland(int[][] grid) {
        
        boolean[][] isExplored = new boolean[grid.length][grid[0].length];
        maxArea = 0;
        currArea = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {
                currArea = 0;
                explore(grid, isExplored, i, j); //use DFS to explore
            }
        }

        return maxArea;

    }
}
