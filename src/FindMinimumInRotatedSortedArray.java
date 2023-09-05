public class FindMinimumInRotatedSortedArray {

    private static int disscussionSolution(int[] nums){

        int up = nums.length-1, down=0;
        int mid;

        if(nums[0]<nums[nums.length-1]){
            return nums[0]; //rotation = 0
        }

        while (up>=down) {
            mid = (up+down)/2;

            if(mid-1>=0 && nums[mid-1]>nums[mid]){
                return nums[mid];
            }
            else if (mid+1<nums.length && nums[mid]>nums[mid+1]) {
                return nums[mid+1];
            }
            else if(mid-1<0 && mid+1>nums.length-1){
                return nums[mid];
            }

            else if(nums[mid]<nums[nums.length-1]){
                up = mid - 1; 
            }
            else if(nums[mid]>nums[nums.length-1]){
                down = mid + 1;
            }
        }


        return Integer.MIN_VALUE;

    }

    

    static int findMin(int[] nums) {
       return disscussionSolution(nums);
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[]{11,13,15,17}));
        System.out.println(findMin(new int[]{2,1}));
        System.out.println(findMin(new int[]{1}));
        System.out.println(findMin(new int[]{5,1,2,3,4}));
        System.out.println(findMin(new int[]{2,3,4,5,1}));
    }
}
