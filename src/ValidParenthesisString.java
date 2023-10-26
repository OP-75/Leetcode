import java.util.Stack;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        return mySolution(s);
    }

    private boolean mySolution(String s) {
        char[] arr = s.toCharArray();

        // see neetcode's vid for DP solution, Time complexity O(n^3)
        int[][][] memo = new int[arr.length][arr.length][arr.length];
        return recusiveDP(arr, 0, 0, 0, memo);
    }

    private boolean recusiveDP(char[] arr, int i, int numOfOpeningBrackets, int numOfClosingBrackets, int[][][] memo) {

        // base case
        if (i >= arr.length && numOfOpeningBrackets == numOfClosingBrackets) {
            return true; // string is valid
        } else if (i >= arr.length && numOfOpeningBrackets != numOfClosingBrackets) {
            return false; // string is not valid since there are still brackets in the stack
        }

        // check at every point if numOfOpeningBrackets<numOfClosingBrackets caz we cant
        // recover from it eg - (()))
        if (numOfOpeningBrackets < numOfClosingBrackets) {
            return false;
        }

        //check memo
        if (memo[i][numOfOpeningBrackets][numOfClosingBrackets] == -1) {
            return false;
        }

        if (arr[i] == '*') {

            // '*' represents empty character
            if (recusiveDP(arr, i + 1, numOfOpeningBrackets, numOfClosingBrackets, memo)) {
                return true;
            }

            // assuming/making '*' = '('
            if (recusiveDP(arr, i + 1, numOfOpeningBrackets + 1, numOfClosingBrackets, memo)) {
                return true;
            }

            // assuming/making '*' = ")"
            if (recusiveDP(arr, i + 1, numOfOpeningBrackets, numOfClosingBrackets+1, memo)) {
                return true;
            }

        } else if (arr[i] == '(') {
            if (recusiveDP(arr, i + 1, numOfOpeningBrackets + 1, numOfClosingBrackets, memo)) {
                return true;
            }
        } else if (arr[i] == ')') {
            if (recusiveDP(arr, i + 1, numOfOpeningBrackets, numOfClosingBrackets+1, memo)) {
                return true;
            }
        }

        memo[i][numOfOpeningBrackets][numOfClosingBrackets] = -1;
        return false;
    }

}
