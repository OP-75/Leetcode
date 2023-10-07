import java.util.Arrays;

public class DecodeWays {

    public int numDecodings(String s) {
        char[] carr = s.toCharArray();

        // memo = new int[carr.length];
        // Arrays.fill(memo, -1);
        // return mySolution(carr, 0);

        return tabulationApprach(carr);
    }

    
    int[] memo;

    private int mySolution(char[] carr, int i) {
        //memoization solution

        if (i >= carr.length) {
            return 1;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int numOfWaysThruI = 0;

        char c1 = carr[i];

        if (c1 >= '1') {
            numOfWaysThruI += mySolution(carr, i + 1);
        }

        if (i + 1 < carr.length) {
            char c2 = carr[i + 1];
            if (c1 == '1' || (c1 == '2' && c2 <= '6')) {
                // if c1 digit is = 1 then c2 can take any value between 0 - 9 but if c1 = 2
                // then c2 has to be <= 6 caz "Z" = 26 there is not "code"/alphabet above 26
                numOfWaysThruI += mySolution(carr, i + 2);
            }
        }

        if (numOfWaysThruI != 0) {
            memo[i] = numOfWaysThruI;
        }

        return numOfWaysThruI;

    }

    private int tabulationApprach(char[] carr) {
        int dp[] = new int[carr.length + 2];
        // see strivers DP playlist to lear this technique

        ;

        for (int i = carr.length; i >= 0; i--) {


            if (i >= carr.length) {
                dp[i] = 1;
                continue;
            }

            int numOfWaysThruI = 0;

            char c1 = carr[i];

            if (c1 >= '1') {
                numOfWaysThruI += dp[i + 1];
            }

            if (i + 1 < carr.length) {
                char c2 = carr[i + 1];
                if (c1 == '1' || (c1 == '2' && c2 <= '6')) {
                    // if c1 digit is = 1 then c2 can take any value between 0 - 9 but if c1 = 2
                    // then c2 has to be <= 6 caz "Z" = 26 there is not "code"/alphabet above 26
                    numOfWaysThruI += dp[i + 2];
                }
            }

            dp[i] = numOfWaysThruI;
        }

        System.out.println(Arrays.toString(dp));
        return dp[0];

    }

}
