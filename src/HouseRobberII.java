import java.util.*;

public class HouseRobberII {

    int[] memo;

    public int rob(int[] nums) {

    //    return mySolution(nums);
       return neetcodeSolution(nums);
    }

    private int neetcodeSolution(int[] nums){
         if (nums.length == 1) {
            return nums[0];
        }

        memo = new int[nums.length - 1];

        Arrays.fill(memo, -1);
        int moneyRobbed1 = neetcodeSolutionRecusive(Arrays.copyOfRange(nums, 0, nums.length - 1), 0);
        Arrays.fill(memo, -1);
        int moneyRobbed2 = neetcodeSolutionRecusive(Arrays.copyOfRange(nums, 1, nums.length), 0);

        return Math.max(moneyRobbed1, moneyRobbed2);
    }

    private int neetcodeSolutionRecusive(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int max = Math.max(nums[i] + neetcodeSolutionRecusive(nums, i + 2), neetcodeSolutionRecusive(nums, i + 1));
        memo[i] = max;
        return max;
    }

    private int mySolution(int[] nums) {
        // Gives TLE :(

        if (nums.length == 1) {
            return nums[0];
        }

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            boolean[] isRobbed = new boolean[nums.length];
            memo = new int[nums.length];
            Arrays.fill(memo, -1);
            int robbed = mySolutionRecursive(nums, i, isRobbed);
            max = Math.max(max, robbed);
        }

        return max;

    }

    private int mySolutionRecursive(int[] nums, int i, boolean[] isRobbed) {

        if (i >= nums.length) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        isRobbed[i] = true;

        if (isAdjacentRobbed(isRobbed, i)) {
            isRobbed[i] = false;
            return 0;
        }

        int maxRobbed = 0;

        for (int j = i; j < nums.length; j++) {

            int amtRobbed = nums[i] + mySolutionRecursive(nums, j + 2, isRobbed);
            if (amtRobbed > maxRobbed) {
                maxRobbed = amtRobbed;
            }

        }

        if (memo[i] < maxRobbed) {
            // memo[i] = maxRobbed;
        }

        isRobbed[i] = false;
        return maxRobbed;
    }

    private boolean isAdjacentRobbed(boolean[] isRobbed, int houseNumber) {
        return (isRobbed[0] && isRobbed[isRobbed.length - 1]) || (houseNumber > 0 && houseNumber < isRobbed.length - 1
                && (isRobbed[houseNumber - 1] || isRobbed[houseNumber + 1]));
    }

}
