import java.util.ArrayList;
import java.util.List;

public class NQueens {
    
    private boolean canQueensAttack(List<int[]> queenPos){
        // the queen in 0st row will be at 0th indx, 1 in 1st indx and so on
        
        // since our algo only puts 1 queen in 1 row so just check if quuen can attack in colum
        for (int i = 0; i < queenPos.size(); i++) {
            int rowPos = i;
            int colPos = queenPos.get(i)[1];
            for (int j = i+1; j < queenPos.size(); j++) {
                int rowPosToCheck = j;
                int colPosToCheck = queenPos.get(j)[1];
                if (colPosToCheck==colPos) {
                    return true;
                }
                //check diagonals, check if any queens are in diagonals
                if (Math.abs(rowPos-rowPosToCheck)==Math.abs(colPos-colPosToCheck)) {
                    return true;
                }
            }
        }

        //if all checks are passed then there is no collision/attack by queens
        return false;


    }

    private void backtrack(int n, int curRow, List<int[]> queenPos, List<List<int[]>> ans){

        if (curRow==n) {
            ans.add(new ArrayList<>(queenPos));
            return;
        }

        for (int col = 0; col < n; col++) {
            queenPos.add(new int[]{curRow, col});
            if (!canQueensAttack(queenPos)) {
                backtrack(n, curRow+1, queenPos, ans);
            }
            queenPos.remove(queenPos.size()-1);
        }

    }

    private void backtrack2(int n, int curRow, List<int[]> queenPos, List<List<String>> ans){

        if (curRow==n) {
            List<int[]> oneSolution = queenPos;

            List<String> strSolution = new ArrayList<>();
            for (int j = 0; j < oneSolution.size(); j++) {
                
                int colPos = oneSolution.get(j)[1];

                StringBuilder s = new StringBuilder();
                for (int j2 = 0; j2 < n; j2++) {
                    if (j2==colPos) {
                        s.append('Q');
                    }
                    else{
                        s.append('.');
                    }
                }

                strSolution.add(s.toString());

            }

            ans.add(strSolution);
            return;
        }

        for (int col = 0; col < n; col++) {
            queenPos.add(new int[]{curRow, col});
            if (!canQueensAttack(queenPos)) {
                backtrack2(n, curRow+1, queenPos, ans);
            }
            queenPos.remove(queenPos.size()-1);
        }

    }

    public List<List<String>> solveNQueens(int n) {
        
        // List<List<int[]>> ans = new ArrayList<>();
        // backtrack(n, 0, new ArrayList<>(), ans);
        
        
        // List<List<String>> ansStringSolution = new ArrayList<>();
        // for (int i = 0; i < ans.size(); i++) {

        //     List<int[]> oneSolution = ans.get(i);

        //     List<String> strSolution = new ArrayList<>();
        //     for (int j = 0; j < oneSolution.size(); j++) {
                
        //         int colPos = oneSolution.get(j)[1];

        //         StringBuilder s = new StringBuilder();
        //         for (int j2 = 0; j2 < n; j2++) {
        //             if (j2==colPos) {
        //                 s.append('Q');
        //             }
        //             else{
        //                 s.append('.');
        //             }
        //         }

        //         strSolution.add(s.toString());

        //     }

        //     ansStringSolution.add(strSolution);
        // }

        // return ansStringSolution;



        List<List<String>> ans = new ArrayList<>();
        backtrack2(n, 0, new ArrayList<>(), ans);
        return ans;

    }
    

}
