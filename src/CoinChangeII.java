import java.util.ArrayList;
import java.util.Arrays;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        return mySolution(amount, coins);
    }

    private int mySolution(int amount, int[] coins) {

        int[][] memo = new int[coins.length][amount+1];
        boolean[][] isMemoFilled = new boolean[coins.length][amount+1];
        


        // Arrays.sort(coins);

        return recursiveDP(amount, coins, 0, memo, isMemoFilled);
    }

    private int recursiveDP(int amount, int[] coins, int i, int[][] memo, boolean[][] isMemoFilled) {

        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || i>=coins.length) {
            return 0;
        }
        if (isMemoFilled[i][amount]) {
            return memo[i][amount];
        }

        int numOfWays = 0;

        //pick current coin and stay at curr position i
        numOfWays += recursiveDP(amount - coins[i], coins, i, memo, isMemoFilled);
        
        // dont pick curr coin & move forward
        numOfWays += recursiveDP(amount, coins, i + 1, memo, isMemoFilled);

        memo[i][amount] = numOfWays;
        isMemoFilled[i][amount] = true;
        return numOfWays;

    }

    public static void main(String[] args) {

        CoinChangeII o = new CoinChangeII();
        System.out.println(o.change(5, new int[] { 1, 2, 5 }));

    }

}