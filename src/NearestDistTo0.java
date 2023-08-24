import java.util.*;

class NearsetDistTo0 {
    
    //DFS wont work since its desinged to go in depth ie find the longest path (i tried)     

    //So try BFS

    boolean isWithinBounds(int[][] mat, int r, int c){
        int lastRowIndx = mat.length-1;
        int lastColIndx = mat[0].length-1;

        if(r<=lastRowIndx && c<=lastColIndx && r>=0 && c>=0){
            return true;
        }
        else{
            return false;
        }
    }

    
    public int[][] updateMatrix(int[][] mat) {
        
        // the idea is to use bfs and add every cell that is = 0 and every cell that is updated

        // IMP - SEE SOLUTIONS SECTION THAT USES BFS+QUEUE IF U DONT UNDERSTAND

        int m=mat.length, n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
               if(mat[i][j]==0){ 
                   q.add(new int[]{i,j});
                   continue;
               }
               else mat[i][j] = Integer.MAX_VALUE;
            }
        }


        int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}};
        while(!q.isEmpty()){

            int[] cell = q.remove();

            for(int direction[]: directions){
                int r = direction[0] + cell[0];
                int c = direction[1] + cell[1];
                if(isWithinBounds(mat, r, c) && mat[r][c] > mat[cell[0]][cell[1]] + 1){
                    mat[r][c] = mat[cell[0]][cell[1]] + 1;
                    q.add(new int[]{r,c});
                }
            }

        }



        return mat;


    }
}