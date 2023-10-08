import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // memo = new int[nums.length];
        // Arrays.fill(memo, -1);

        // return recursive(nums, 0, Integer.MIN_VALUE);

        return tabulation(nums);
    }

    int[] memo;

    private int recursive(int[] nums, int start, int prevNum) {
        //uses memoization
        if (start >= nums.length) {
            return 0;
        }

        if (memo[start] != -1) {
            return memo[start];
        }

        int longest = 0;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > prevNum || start == 0) {
                // if start = 0 then there is no prevNum
                // +1 to account for curr num[i] of sequence
                int seqLen = 1 + recursive(nums, i + 1, nums[i]);
                if (seqLen > longest) {
                    longest = seqLen;
                }

            }
        }

        memo[start] = longest;
        return longest;
    }

    private int tabulation(int[] nums) {
        //bottom-up approach
        int[] dp = new int[nums.length + 1];

        dp[nums.length] = 0;
        dp[nums.length-1] = 1;

        int actualLongest = 1; // any non empty array will contain ATLEAST 1 num which is a sequnce in itself eg [10, 9, 8] so [8] is a increasing seq in & of itself similarly so is [10] & [9] 

        for (int i = nums.length - 2; i >= 0; i--) {
            //this loop is bottom-up approach

            int currLongest = 1; //is = 1 caz a squence with only 1 val is still a sequence eg [10]
            int prevNum = nums[i];

            for (int j = i; j < nums.length; j++) {

                if (nums[j] > prevNum) {
                    // if start = 0 then there is no prevNum
                    // +1 to account for curr num[i] of sequence
                    int seqLen = 1 + dp[j];
                    if (seqLen > currLongest) {
                        currLongest = seqLen;
                    }

                }
            }

            dp[i] = currLongest;
            if(currLongest>actualLongest){
                actualLongest = currLongest;
            }
        }

        // System.out.println(Arrays.toString(dp));

        return actualLongest;
    }

}
