import java.util.*;

public class LargestSubmatrixWithRearrangements {
    public int largestSubmatrix(int[][] matrix) {
        return hintSolution(matrix);
    }

    private int hintSolution(int[][] matrix) {
        
        int[][] consecutive1sColumnArr = new int[matrix[0].length][matrix.length]; //this conatins the colums of the matrix

        for (int c = 0; c < matrix[0].length; c++) {
            
            int prev;
            for (int r = 0; r < matrix.length; r++) {
                // in consecutive1sColumnArr c & r are flipped/reveresed

                if(r==0){
                    prev = 0;
                }
                else{
                    prev = consecutive1sColumnArr[c][r-1];
                }

                if(matrix[r][c]==0){
                    //reset if we encounter a 0
                    consecutive1sColumnArr[c][r] = 0;
                }
                else{
                    consecutive1sColumnArr[c][r] = prev + matrix[r][c];
                }
            }
        }


        int globalMaxArea = 0;

        for (int r = 0; r < consecutive1sColumnArr[0].length; r++) {

            ArrayList<Integer> consecutive1sInThisRow = new ArrayList<>();

            for (int c = 0; c < consecutive1sColumnArr.length; c++) {
                consecutive1sInThisRow.add(consecutive1sColumnArr[c][r]);
            }

            //remember we need to sort in decending order
            Collections.sort(consecutive1sInThisRow);
            Collections.reverse(consecutive1sInThisRow);

            int tmpMaxArea = Integer.MIN_VALUE;

            for (int i = 0; i < consecutive1sInThisRow.size(); i++) {
                
                int numOfColumns = i+1;

                tmpMaxArea = Math.max(tmpMaxArea, numOfColumns*consecutive1sInThisRow.get(i));
            }

            globalMaxArea = Math.max(globalMaxArea, tmpMaxArea);
        }

        
        
        return globalMaxArea;
    }
}
