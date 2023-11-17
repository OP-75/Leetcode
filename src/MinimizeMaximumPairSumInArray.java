import java.util.Arrays;

public class MinimizeMaximumPairSumInArray {

    public int minPairSum(int[] nums) {
        return mySolution(nums);
    }

    private int mySolution(int[] nums) {

        // Greedy apprach : To find the min pair sum, sort the given array and pair every element on the right to corresponding element on left this ensures that all the max elements are paired with min elements ie minimizing the sum of any x,y pairs
        
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        int max = Integer.MIN_VALUE;
        for (int l = 0, r = nums.length-1; l < r; l++, r--) {
            System.out.println("Pair: "+nums[l]+"-"+nums[r]);
            max = Math.max(max, nums[l]+nums[r]);
        }

        return max;
    }

}
