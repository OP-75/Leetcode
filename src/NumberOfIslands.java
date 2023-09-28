public class NumberOfIslands {

    boolean[][] isExplored;

    public int numIslands(char[][] grid) {

        isExplored = new boolean[grid.length][grid[0].length];

        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '0' || isExplored[i][j]) {
                    continue;
                }

                numOfIslands++;
                explore(grid, isExplored, i, j);

            }
        }

        return numOfIslands;

    }

    // doing DFS on the island
    private void explore(char[][] grid, boolean[][] isExplored, int i, int j) {
        if (isOutOfBounds(grid, i, j) || grid[i][j] == '0' || isExplored[i][j]) {
            return;
        }

        isExplored[i][j] = true;

        explore(grid, isExplored, i + 1, j);
        explore(grid, isExplored, i, j + 1);
        explore(grid, isExplored, i - 1, j);
        explore(grid, isExplored, i, j - 1);

    }

    private boolean isOutOfBounds(char[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

    public static void main(String[] args) {



        char[][] grid = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        

        NumberOfIslands o = new NumberOfIslands();

        System.out.println(o.numIslands(grid));
        System.out.println(o.numIslands(grid2));

    }

}
