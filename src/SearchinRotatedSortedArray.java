public class SearchinRotatedSortedArray {

    // for this question u need to know problem - Minimum in Rotated Sorted Array -
    // Binary Search - Leetcode 153
    // the minimum in rotated array is nothing but the border/pivot element in
    // rotated arr, so once u get its indx u can do simple binary search on the 2
    // subarray arrays divided by pivot

    private static int mySolution(int[] nums, int target) {

        int pivotIndx = nums.length - 1;

        // getting the pivot/minimum
        int up = nums.length - 1, down = 0;
        int mid;
        while (up >= down) {
            mid = (up + down) / 2;

            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                pivotIndx = mid;
                // System.out.println(pivotIndx);
                break;
            } else if (mid + 1 < nums.length && nums[mid + 1] < nums[mid]) {
                pivotIndx = mid + 1;
                // System.out.println(pivotIndx);
                break;
            }

            else if (nums[mid] < nums[down]) {
                // search in left subarr
                up = mid - 1;
            } else if (nums[mid] >= nums[down]) {
                // search in right subarr, pivot is in right
                down = mid + 1;
            }
        }

        int ans = -1;
        // search the subarr 0 to pivotIndx - 1 for target
        down = 0;
        up = pivotIndx - 1;
        mid = -1;
        while (up >= down && target <= nums[pivotIndx - 1]) { // only run this loop if target is between 0 and pivot - 1

            mid = (up + down) / 2;

            if (target == nums[mid]) {
                ans = mid;
                return ans;
            } else if (target > nums[mid]) {
                down = mid + 1;
            } else if (target < nums[mid]) {
                up = mid - 1;
            }

        }

        // search the subarr pivotIndx to nums.length-1 for target
        down = pivotIndx;
        up = nums.length - 1;
        mid = -1;
        while (up >= down && target >= nums[pivotIndx]) { // only run this loop if target is between 0 and pivot - 1

            mid = (up + down) / 2;

            if (target == nums[mid]) {
                ans = mid;
                return ans;
            } else if (target > nums[mid]) {
                down = mid + 1;
            } else if (target < nums[mid]) {
                up = mid - 1;
            }

        }

        return ans;

    }

    public static int search(int[] nums, int target) {
        // for this question u need to know problem - Minimum in Rotated Sorted Array- Binary Search - Leetcode 153
        // the minimum in rotated array is nothing but the border/pivot element in
        // rotated arr, so once u get its indx u can do simple binary search on the 2
        // subarray arrays divided by pivot

        return mySolution(nums, target);

    }

    public static void main(String[] args) {

        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 2));
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7));

        System.out.println(search(new int[] { 1 }, 0));
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));
    }
}
