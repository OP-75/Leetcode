import java.util.*;

public class SetMatrixZeroes{

    private static void mySolution(int[][] matrix){

        HashSet<Integer> rowsToZero = new HashSet<>();
        HashSet<Integer> colsToZero = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]==0) {
                    rowsToZero.add(i);
                    colsToZero.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rowsToZero.contains(i) || colsToZero.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println(rowsToZero);
        System.out.println(colsToZero);

    }

    public static void main(String[] args) {
        
    }

}