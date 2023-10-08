public class MaximumProductSubarray {
    // IMP!!!!!!!!!!!!!! 
    // https://youtu.be/hnswaLJvr6g?si=oKKT9dTsvOJVY_-c
    
    public int maxProduct(int[] nums) {

        // return bruteForce(nums);

        //recursive doesnt work
        // recursiveDP(nums, 0, 1);
        // return maxProduct;

        return striverSolution(nums);
    }


    private int striverSolution(int[] nums){
        int max = Integer.MIN_VALUE;

        int p = 1;
        for (int i = 0; i < nums.length; i++) {
            p *= nums[i];
            if (p>max) {
                max = p;
            }
            if (p==0) {
                p=1;
            }
        }

        p = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            p *= nums[i];
            if (p>max) {
                max = p;
            }
            if (p==0) {
                p=1;
            }
        }

        return max;
    }

    private int bruteForce(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int product = 1;
            for (int j = i; j < nums.length; j++) {

                product *= nums[j];
                max = Integer.max(max, product);
            }

        }

        return max;

    }

    int maxProduct = Integer.MIN_VALUE;

    private int recursiveDP(int[] nums, int i, int productTillNow) {
        
        //doest work properly
        // time complexity O(n*numOfNegatives)

        if (productTillNow > maxProduct) {
            maxProduct = productTillNow;
        }

        if (i >= nums.length) {
            return productTillNow;
        }


        if (nums[i] == 0) {
            // skip 0
            return Integer.max(0, recursiveDP(nums, i + 1, 1)) ;

        } else if (nums[i] > 0) {
            return recursiveDP(nums, i + 1, productTillNow * nums[i]);

        } else if (nums[i] < 0) {
            // here a split will happen weather to take or not take -ve num

            int p1 = recursiveDP(nums, i + 1, productTillNow * nums[i]); // taking
            int p2 = recursiveDP(nums, i + 1, 1); // not taking
            return Integer.max(p1, p2);
        }
        
        return 0;

    }

}
