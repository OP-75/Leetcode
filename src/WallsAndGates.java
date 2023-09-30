import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    public class Solution {
        /**
         * @param rooms: m x n 2D grid
         * @return: nothing
         */
        public void wallsAndGates(int[][] rooms) {
            mySolution(rooms);
        }

        void mySolution(int[][] rooms){

            Queue<int[]> q = new LinkedList<>();

            //add all 0 cells/gate
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[0].length; j++) {
                    if (rooms[i][j]==0) {
                        q.add(new int[]{i,j});
                    }
                }
            }

            while (!q.isEmpty()) {
                
                int[] cell = q.remove();
                int r = cell[0], c = cell[1];

                int[][] moves = {{1,0},{0,1},{-1,0},{0,-1}};

                for (int[] move : moves) {
                    int rowPosAfterMove = r+move[0], colPosAfterMove = c+move[1];
                    
                    if (!isOutOfBounds(rooms, rowPosAfterMove, colPosAfterMove) && rooms[rowPosAfterMove][colPosAfterMove]>rooms[r][c]+1) {
                        rooms[rowPosAfterMove][colPosAfterMove] = rooms[r][c]+1;
                        q.add(new int[]{rowPosAfterMove, colPosAfterMove});
                    }
                }
            }


        }

        private boolean isOutOfBounds(int[][] grid, int i, int j) {
            return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
        }


    }
}
