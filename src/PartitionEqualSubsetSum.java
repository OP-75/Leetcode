import java.util.HashMap;
import java.util.HashSet;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

        return memoizationApproach(nums);
        // return neetcodeDPSolution(nums);

    }

    private boolean neetcodeDPSolution(int[] nums) {
        //slower & more complex
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) {
            // u cant divide odd numbers into 2 equal parts (using int)
            return false;
        }

        int halfsum = sum / 2;

        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);

        for (int i : nums) {

            HashSet<Integer> tmpHs = new HashSet<>();

            for (Integer otherSums : hs) {
                if (otherSums + i == halfsum) {
                    return true;
                }
                tmpHs.add(otherSums + i);
            }

            hs.addAll(tmpHs);
        }

        return false;

    }

    private boolean memoizationApproach(int[] nums) {
        int sum1 = 0, sum2 = 0;
        for (int i : nums) {
            sum2 += i;
        }

        if (sum2 % 2 != 0) {
            // u cant divide odd numbers into 2 equal parts (using int)
            return false;
        }

        memo = new int[nums.length + 1][sum2 + 1];

        return recursive(nums, 0, sum1, sum2);
    }

    int[][] memo;

    private boolean recursive(int[] nums, int i, int sum1, int sum2) {
        // uses memoization but is VERY inefficient

        if (sum1 == sum2) {
            return true;
        }

        if (i >= nums.length || memo[i][sum1] == -1) {
            return false;
        }

        if (recursive(nums, i + 1, sum1 + nums[i], sum2 - nums[i])) {
            // taking nums[i]
            return true;
        }
        if (recursive(nums, i + 1, sum1, sum2)) {
            // NOT taking nums[i]
            return true;
        }

        memo[i][sum1] = -1;

        return false;
    }

}
