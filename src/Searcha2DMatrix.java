public class Searcha2DMatrix {

    private static boolean mySolution(int[][] matrix, int target){

        //if target is < than the lowest ele or > the biggest ele return false 
        if (target<matrix[0][0] || target>matrix[matrix.length-1][matrix[0].length-1]) {
            return false;
        }

        int rStart = 0, rEnd = matrix.length-1;
        int rMid=-1;

        while (rStart<=rEnd) {

            rMid = (rStart+rEnd)/2;
            
            if(target==matrix[rMid][0] || target==matrix[rMid][matrix[rMid].length-1]){
                return true;
            }

            if(target>matrix[rMid][0] && target<matrix[rMid][matrix[rMid].length-1]){
                break;
            }

            else if(target<matrix[rMid][0] && target<matrix[rMid][matrix[rMid].length-1]){
                rEnd = rMid - 1;
            }

            else if(target>matrix[rMid][0] && target>matrix[rMid][matrix[rMid].length-1]){
                rStart = rMid + 1;
            }
        }


        int cStart = 0, cEnd = matrix[rMid].length-1;
        int cMid=-1;
        while (cStart<=cEnd) {
            cMid = (cStart+cEnd)/2;

            
            if(target==matrix[rMid][cMid]){
                return true;
            }

            else if (target<matrix[rMid][cMid]) {
                cEnd = cMid - 1;
            }
            else if(target>matrix[rMid][cMid]){
                cStart = cMid +1;
            }
        }

        //if we have still not found it return false
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        return mySolution(matrix, target);
    }


    public static void main(String[] args) {

        int[][] mat = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};

        System.out.println(searchMatrix(mat, 3));
        // System.out.println(searchMatrix(mat, 13));

    }
}