import java.util.Arrays;

public class RemoveDuplicatesfromSortedArrayII {
    
    // btw the optimal solution for this problem would be by using binary search to seacrch the start & end of the repeating sequences, once u get this u can shift the elements from the "end" by the distance between start & end!.
    // this solution takes alot of time if the array is filled with many of same elements

    private int mySolution(int[] nums){
        //remember array is sorted

        int numOfElementsRemoved = 0;

        for (int i = 1; i < (nums.length-numOfElementsRemoved)-1; i++) {
            if(nums[i]==nums[i+1] && nums[i-1]==nums[i]){
                numOfElementsRemoved++;
                shiftElementsLeft(nums, i); 
                i--; //decrement i by 1 since we have to recheck curr postion since we shifted elements
                // we wanna make "i" th element disappear so we will shift elements i+1, i+2, etc to the left by 1 place
            }
        }

        return nums.length - numOfElementsRemoved;
    }

    private void shiftElementsLeft(int[] nums, int i) {
    
        for (int j = i; j < nums.length-1; j++) {
            nums[j] = nums[j+1];
        }
    
    }

    public int removeDuplicates(int[] nums) {
        return mySolution(nums);
    }


    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArrayII obj = new RemoveDuplicatesfromSortedArrayII();

        // int[] arr = new int[]{0,0,1,1,1,1,2,3,3};
        int[] arr = new int[3*10000];
        Arrays.fill(arr, 1);
    
        System.out.println(obj.removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

}
