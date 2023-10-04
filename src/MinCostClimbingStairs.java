import java.util.*;

public class MinCostClimbingStairs {
    
    // HashMap<Integer, Integer> memo = new HashMap<>();
    int[] memo;
    private int mySolution(int[] cost, int step){

        if (step>=cost.length) {
            return 0;
        }

        // if (memo.containsKey(step)) {
        //     return memo.get(step);
        // }
        if (memo[step]!=-1) {
            return memo[step];
        }

        int oneCost = mySolution(cost, step+1);
        int twoCost = mySolution(cost, step+2);

        int costTillNow = cost[step]+ Math.min(oneCost, twoCost);
        // memo.put(step, costTillNow);
        memo[step] = costTillNow;

        return costTillNow;
    }

    public int minCostClimbingStairs(int[] cost) {
        memo = new int[cost.length];
        Arrays.fill(memo, -1);
        return Math.min(mySolution(cost, 0), mySolution(cost, 1));
    }
}
