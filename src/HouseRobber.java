import java.util.Arrays;

public class HouseRobber {

    int[] memo;
    private int robRecursive( int[] nums, int i, boolean[] isRobbed){
        //this solution seems wrong but works for some reason!
        if (i>=nums.length || isRobbed[i]) {
            return 0;
        }

        if (memo[i]!=Integer.MIN_VALUE) {
            return memo[i];
        }

        isRobbed[i] = true;
        
        int max = 0;
        for (int j = i; j < nums.length; j++) {
            int currRobbedMoney = nums[j] + robRecursive(nums, j+2, isRobbed);
            if(currRobbedMoney>max){
                max = currRobbedMoney;
            }
        }
        
        isRobbed[i] = false;
        memo[i] = max;
        return max;
        
    }

    private int neetcodeSolution(int[] nums, int i){

        if (i>=nums.length) {
            return 0;
        }

        if (memo[i]!=Integer.MIN_VALUE) {
            return memo[i];
        }

        int max = Math.max(nums[i] + neetcodeSolution(nums, i+2), neetcodeSolution(nums, i+1));
        memo[i] = max;
        return max;
    }

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        // return robRecursive(nums, 0, new boolean[nums.length]);

        return neetcodeSolution(nums, 0);
    }
    
}
