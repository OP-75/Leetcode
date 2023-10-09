import java.util.Arrays;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {

        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();

        // return memoizationApproach(s1, s2);
        return tabulationApprach(s1, s2);

        
        
    }

    int[][] memo;
    private int memoizationApproach(char[] s1, char[] s2){

        memo = new int[s1.length][s2.length];

        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return recursiveDP(s1, s2, 0, 0);
    }

    private int recursiveDP(char[] s1, char[] s2, int i, int j) {

        if (i >= s1.length || j >= s2.length) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1[i] == s2[j]) {
            return 1 + recursiveDP(s1, s2, i + 1, j + 1);
        } else {
            int lenOfSeq1 = recursiveDP(s1, s2, i + 1, j);
            int lenOfSeq2 = recursiveDP(s1, s2, i, j + 1);
            int biggestSeq = Math.max(lenOfSeq1, lenOfSeq2);
            memo[i][j] = biggestSeq;
            return biggestSeq;
        }

    }

    private int tabulationApprach(char[] s1, char[] s2) {

        int dp[][] = new int[s1.length + 1][s2.length + 1];

        for (int i = s1.length - 1; i >= 0; i--) {
            for (int j = s2.length - 1; j >= 0; j--) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    int lenOfSeq1 = dp[i + 1][j];
                    int lenOfSeq2 = dp[i][j + 1];
                    int biggestSeq = Math.max(lenOfSeq1, lenOfSeq2);
                    dp[i][j] = biggestSeq;
                }
            }
        }

        return dp[0][0];

    }

}
