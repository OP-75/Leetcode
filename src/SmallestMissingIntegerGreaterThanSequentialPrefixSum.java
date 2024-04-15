import java.util.HashSet;

public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {

    public int missingInteger(int[] nums) {
        HashSet<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(nums[i]);
        }

        int longestPrefixSum = 0;

        for (int j = 0; j < nums.length; j++) {

            if (j != 0 && nums[j] != nums[j - 1] + 1) {
                break;
            } else {
                longestPrefixSum += nums[j];
            }

        }

        int ans = longestPrefixSum;
        while (numsSet.contains(ans)) {
            ans = ans + 1;
        }

        return ans;
    }

}
