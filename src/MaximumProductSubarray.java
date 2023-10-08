public class MaximumProductSubarray {
    // Incomplete!!!!!!!!!!!!!!!!!!
    public int maxProduct(int[] nums) {

        memo = new int[nums.length];
        return recursiveDP();
    }

    private int bruteForce(int[] nums){
        int max=Integer.MIN_VALUE;
            int currMax = 1;
    
            for(int i =0; i<nums.length; i++){
    
                int product = 1;
                for(int j = i; j<nums.length; j++){
    
                    product *= nums[j];
                    max = Integer.max(max, product);
                }
    
            }
    
            return max;
    
    }

    int[] memo;
    private int recursiveDP(int[] nums, int i){

        if (i>=nums.length) {
            return 1;
        }

        recursiveDP(nums, i+1);

        int max = 1, p=1;
        for (int j = i; j < nums.length; j++) {
            p *= nums[i];
            if () {
                
            }
        }


    }

}
