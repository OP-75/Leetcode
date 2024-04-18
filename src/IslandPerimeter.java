public class IslandPerimeter {
    
}


class Solution {

    public int islandPerimeter(int[][] grid) {
            
        int r = grid.length, c = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j]==1) {
                    perimeter += getCellPerimeter(grid,i,j);
                }
            }
        }

        return perimeter;

    }

    private int getCellPerimeter(int[][] grid, int i, int j) {
        int cellPerimeter = 0;
        int r = grid.length, c = grid[0].length;

        if (i==0 || grid[i-1][j]==0) {
            //ie cell is on top edge of grid or has water on above it the add 1 to cell perimeter
            cellPerimeter += 1;
        }

        if (i==r-1 || grid[i+1][j]==0) {
            //ie cell is on bottom edge of grid or has water on below it the add 1 to cell perimeter
            cellPerimeter += 1;
        }

        if (j==0 || grid[i][j-1]==0) {
            //ie cell is on left edge of grid or has water on left of it the add 1 to cell perimeter
            cellPerimeter += 1;
        }

        if (j==c-1 || grid[i][j+1]==0) {
            //ie cell is on right edge of grid or has water on right of it the add 1 to cell perimeter
            cellPerimeter += 1;
        }

        return cellPerimeter;

    }
}