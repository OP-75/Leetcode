public class JumpGame {
    public boolean canJump(int[] nums) {
        // int[] memo = new int[nums.length];
        // return recursiveDP(nums, 0, memo);

        return greedy(nums);
    }

    private boolean recursiveDP(int[] nums, int i, int[] memo) {
        
        //base cases
        if (i>=nums.length-1) {
            //ie we have reached the last indx
            return true;
        }
        if (nums[i]==0) {
            return false;
        }

        if (memo[i]==-1) {
            return false;
        }




        for (int j = i+nums[i]; j > i; j--) {
            if (recursiveDP(nums, j, memo)) {
                return true;
            }
        }

        memo[i] = -1;

        return false;

    }

    private boolean greedy(int[] nums){

        int goal = nums.length-1;

        for (int i = nums.length-1; i >= 0; i--) {
            if (i+nums[i]>=goal) {
                //ie we can reach the goal thru i so i becomes our new goal
                goal = i;
            }
        }

        return goal==0? true : false;

    }

        
}
