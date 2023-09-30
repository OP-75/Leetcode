import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {



    private int mySolution(int[][] grid){
        //start BFS from EVERY rotten cell

        int minTime = -1; //make this -1 caz at we are doing minTime++ but we have put all the rotten oranges in the queue initally so we dont want to do ++ at that time... ie at time = 0 we havent made any new oranges rotten
        Queue<int[]> cellQueue = new LinkedList<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==2) {
                    cellQueue.add(new int[]{i,j});
                }
            }
        }

        //now do BFS
        while (!cellQueue.isEmpty()) {
            
            int size = cellQueue.size();
            minTime++;
            for (int i = 0; i < size; i++) {
                int[] cellIndx = cellQueue.remove();
                int r = cellIndx[0], c = cellIndx[1];

                int[][] indxsToMove = { {1,0}, {0,1}, {-1,0}, {0,-1} };
                for (int[] move : indxsToMove) {
                    int rowPosAfterMove = r+move[0], colPosAfterMove = c+move[1];

                    if(!isOutOfBounds(grid, rowPosAfterMove, colPosAfterMove) && grid[rowPosAfterMove][colPosAfterMove]==1){
                        grid[rowPosAfterMove][colPosAfterMove]=2; //make the orange rot
                        cellQueue.add(new int[]{rowPosAfterMove, colPosAfterMove});
                    }
                    
                }
                
            }
        }


        //now check if any of the oranges are still not rotten
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1) {
                    return -1;
                }
            }
        }

        //if minTime is still -1 that means grid CONTAINS NO ORANGES
        return minTime==-1? 0: minTime;
    }

    private boolean isOutOfBounds(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }


    public int orangesRotting(int[][] grid) {

        return mySolution(grid);

    }
}
