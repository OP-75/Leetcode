import java.util.Arrays;
import java.util.HashMap;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        // Arrays.sort(coins);
        // memo = new HashMap<>();
        // int ans = recursionDP(coins, amount);

        // System.out.println(memo.toString());

        int ans = neetcodeTabulationApproach(coins, amount);

        

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    HashMap<Integer, Integer> memo;

    private int recursionDP(int[] coins, int amount) {
        // memoization appproach

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }

        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        int minWays = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int tmpAns = recursionDP(coins, amount - coins[i]);
            if (tmpAns == Integer.MAX_VALUE) {
                continue;
            }
            minWays = Integer.min(minWays, 1 + tmpAns);
        }

        memo.put(amount, minWays);

        return minWays;
    }

    private int neetcodeTabulationApproach(int[] coins, int amount) {
        //tabulation approach

        int[] dp = new int[amount + 1];
        
        dp[0] = 0;

        for (int tmpAmt = 1; tmpAmt < dp.length; tmpAmt++) {

            int minWays = Integer.MAX_VALUE;

            for (int i = coins.length - 1; i >= 0; i--) {

                int tmpAns = (tmpAmt - coins[i])>=0? dp[tmpAmt - coins[i]] : Integer.MAX_VALUE;
                if (tmpAns == Integer.MAX_VALUE) {
                    continue;
                }
                minWays = Integer.min(minWays, 1 + tmpAns);
            }

            dp[tmpAmt] = minWays;
        }

        return dp[amount];

    }

}
