import java.util.Arrays;

public class JumpGameII {

    public int jump(int[] nums) {
        // return neetcodeSolution(nums);
        return mySolution(nums);
    }

    private int neetcodeSolution(int[] nums) {
        // takes O(n) time, This is greedy approach

        int jumps = 0;
        int left = 0, right = 0;

        // BFS type search
        while (right < nums.length - 1) {

            int farthestJump = 0;
            for (int i = left; i <= right; i++) {
                farthestJump = Math.max(farthestJump, i + nums[i]);
            }

            left = right + 1;
            right = farthestJump;
            jumps++;
        }

        return jumps;
    }

    private int mySolution(int[] nums) {
        // DP approch takes O(n^2)

        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return recursiveDP(nums, 0, memo);

    }

    private int recursiveDP(int[] nums, int i, int[] memo) {
        if (i >= nums.length - 1) {
            return 0;
        }

        if (memo[i]!=-1) {
            return memo[i];
        }

        
        int minJumps = Integer.MAX_VALUE;
        
        int start = i + 1, end = i + nums[i];

        for (int j = start; j <= end; j++) {
            int tmpJumps = recursiveDP(nums, j, memo);
            if (tmpJumps!=Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, 1+tmpJumps);
            }
        }

        memo[i] = minJumps;

        return minJumps;

    }
}
