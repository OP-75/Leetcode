public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int minVal = Math.abs((target-sum)); //min val that will be acived but abs

        // int[][] memo = new int[nums.length][(minVal)+(target+sum)+1];
        int[][] memo = new int[nums.length][3*1000];
        // System.out.println("memo size = "+nums.length+", "+((minVal)+(target+sum)+1));

        return recursiveDP(nums, target, 0, memo, minVal);
    }

    private int recursiveDP(int[] nums, int target, int i, int[][] memo, int offset){

        System.out.println(i +" "+ target+", with offset "+(target+offset));

        if (i>=nums.length && target==0) {
            return 1;   
        }
        if (i>=nums.length && target!=0) {
            return 0;
        }

        if (memo[i][target+offset]!=0) {
            return memo[i][target+offset];
        }


        int n = 0;
        n += recursiveDP(nums, target-nums[i], i+1, memo, offset); //taking num[i] as +ve

        n += recursiveDP(nums, target+nums[i], i+1, memo, offset); //taking num[i] as -ve

        memo[i][target+offset] = n;

        return n;
    }

    public static void main(String[] args) {
        TargetSum o = new TargetSum();
        System.out.println(o.findTargetSumWays(new int[]{1,0}, 1));
        System.out.println(o.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
    }

}
