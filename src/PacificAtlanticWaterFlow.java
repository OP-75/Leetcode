import java.util.*;

public class PacificAtlanticWaterFlow { 

    //below is the brute force code (see the commented part in pacificAtlantic func too!)
    boolean[][] isVisited = null;
    private void DFS(int[][] heights, int i, int j, boolean[] isConnectedToOcean){
        // isConnectedToOcean[0] represents if the waters connects to Pacific ocean. 
        // isConnectedToOcean[1] represents if the waters connects to Atlantic ocean. 

        //if we are at any of the 2 oceans then we are out of bounds.
        if (isPacific(heights, i, j)) {
            isConnectedToOcean[0] = true;
            return;
        }
        else if(isAtlantic(heights, i, j)){
            isConnectedToOcean[1] = true;
            return;
        } else if(isVisited[i][j]){
            return;
        }

        isVisited[i][j] = true;


        //do DFS traversal IF AND ONLY IF the next block has LOWER or Equal height
        int[][] amtToMove = { {1,0}, {0,1}, {-1,0}, {0,-1} }; //add this to row & col

        for (int[] move : amtToMove) {
            
            if (isPacific(heights, i+move[0], j+move[1]) || isAtlantic(heights, i+move[0], j+move[1]) || heights[i+move[0]][j+move[1]]<=heights[i][j]) { //if the next block to move to is out of bounds then move to it or if its lower/equal elevation
                DFS(heights, i+move[0], j+move[1], isConnectedToOcean);
            }

        }


    }

    private boolean isAtlantic(int[][] heights, int i, int j) {
        return i>=heights.length || j>=heights[0].length;
    }

    private boolean isPacific(int[][] heights, int i, int j) {
        return i<0 || j<0;
    }







    private boolean isOutOfBounds(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

    private void neetcodeSolution(int[][] heights, int i, int j, boolean[][] isVisitedPt){

        if (isVisitedPt[i][j] || isOutOfBounds(heights, i, j)) {
            return;
        }

        isVisitedPt[i][j] = true;

        //do DFS traversal IF AND ONLY IF the next block has LOWER or Equal height
        int[][] amtToMove = { {1,0}, {0,1}, {-1,0}, {0,-1} }; //add this to row & col

        for (int[] move : amtToMove) {
            int amtRowToMove = move[0];
            int amtColToMove = move[1];

            if (!isOutOfBounds(heights, i+amtRowToMove, j+amtColToMove) && heights[i+amtRowToMove][j+amtColToMove]>=heights[i][j]) { //if the next block to move to is out of bounds then move to it or if its lower/equal elevation
                neetcodeSolution(heights, i+amtRowToMove, j+amtColToMove, isVisitedPt);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        
        //below code is neetcode solution which is much more efficient
        
        //first get all the  points which connect to Pacific ocean  then get all the points connected to Atlantic ocean. Both of these pts should be stored in sets, do intersection of these sets, this gives us the ans.        

    
        //go from ocean to the inner pts using DFS(neetcode solution) 
        boolean[][] isVisitedPacific = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            neetcodeSolution(heights, i, 0, isVisitedPacific);    
        }
        for (int j = 0; j < heights[0].length; j++) {
            neetcodeSolution(heights, 0, j, isVisitedPacific);    
        }
        //now all the pts that are marked isVisted are the onces connected to pacific ocean
        

        
        boolean[][] isVisitedAtlantic = new boolean[heights.length][heights[0].length];

        int endcolIndx = heights[0].length-1;
        
        for (int i = 0; i < heights.length; i++) {
            neetcodeSolution(heights, i, endcolIndx, isVisitedAtlantic);    
        }
        
        int endrowIndx = heights.length-1;
        for (int j = 0; j < heights[0].length; j++) {
            neetcodeSolution(heights, endrowIndx, j, isVisitedAtlantic);    
        }
        //now all the pts that are marked isVisted are the onces connected to atlantic ocean

        //common pts in both are the once which flow to both oceans
        List<List<Integer>> ansPts = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if(isVisitedPacific[i][j] && isVisitedAtlantic[i][j]){
                    List<Integer> pts = new ArrayList<>();
                    pts.add(i);
                    pts.add(j);
                    ansPts.add(pts);
                }
            }
        }

        
        return ansPts;


        
        
        
        // Use DFS for this
        //below is my solution
        // List<List<Integer>> ansPoints = new ArrayList<>();
                
        // for (int i = 0; i < heights.length; i++) {
        //     for (int j = 0; j < heights[0].length; j++) {

        //         isVisited = new boolean[heights.length][heights[0].length];
        //         boolean[] isConnectedToOcean = new boolean[2];
        //         // isConnectedToOcean[0] represents if the waters connects to Pacific ocean. 
        //         // isConnectedToOcean[1] represents if the waters connects to Atlantic ocean. 
        //         DFS(heights, i, j, isConnectedToOcean);

        //         if (isConnectedToOcean[0] && isConnectedToOcean[1]) {
        //             //ie it connects to both oceans
        //             List<Integer> point = new ArrayList<>();
        //             point.add(i);
        //             point.add(j);
        //             ansPoints.add(point);
        //         }
        //     }
        // }

        // return ansPoints;
    }
}
