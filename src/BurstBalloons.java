public class BurstBalloons {
  
    //incomplete!!!!!!!!!!!

    public int maxCoins(int[] nums) {
        // see MCM (Matrix chain multiplication problem) problem before this
        //See strivers solution this problem is too hard!!!
        return mySolution(nums);
    }

    private int mySolution(int[] nums) {
        
        int[] memo = new int[nums.length];
        boolean[] isPopped = new boolean[nums.length];
        return recursiveDP(nums, 0, memo, isPopped);

    }

    private int recursiveDP(int[] nums, int i, int j, int[] memo, boolean[] isPopped) {

        if (i==j) {
            return nums[i];
        }

        int max = 0, indxpicked = -1;
        for (int k = i; k < j; k++) {
            
        }

        return max;
        
        
    }

    private int getLeft(int i, int[] nums, boolean[] isPopped) {

        int left = 1;
        for (int j = i-1; j >=0; j--) {
            if(!isPopped[j]){
                left = nums[j];
                break;
            }
        }
        return left;
    }
    private int getRight(int i, int[] nums, boolean[] isPopped) {

        int right = 1;
        for (int j = i+1; j < isPopped.length; j++) {
            if(!isPopped[j]){
                right = nums[j];
                break;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        BurstBalloons o = new BurstBalloons();
        System.out.println(o.maxCoins(new int[]{3,1,5,8}));
    }
    
}
