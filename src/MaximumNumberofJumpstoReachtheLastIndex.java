import java.util.*;

public class MaximumNumberofJumpstoReachtheLastIndex {

    private static int myMaxJumps(int[] nums, int target, int i, HashMap<Integer, Integer> memo){

        if (i==nums.length-1) {
            return 0;
        }

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        int maxJumps = Integer.MIN_VALUE;
        for (int j = i+1; j < nums.length; j++) {

            if (Math.abs(nums[i]-nums[j])<=target) {
                int currJumps = 1+myMaxJumps(nums, target, j, memo);

                if (currJumps<0) { //return value was -ve which means we were unable to go to n-1 thru j
                    continue;
                }

                if (maxJumps<currJumps) {
                    maxJumps = currJumps;
                }


            }
        }

        memo.put(i, maxJumps);
        return maxJumps;
    }


    public static int hintApproach(int nums[], int target){

        //this arr keeps the count of jumps
        int[] dpMemo = new int[nums.length];

        Arrays.fill(dpMemo, Integer.MIN_VALUE);
        dpMemo[0] = 0;
        for (int i = 0; i < dpMemo.length; i++) {

            //imp condition!!! If u have previosly not reached "i" u cannot start jumping from "i" to any j
            if(dpMemo[i]==Integer.MIN_VALUE) continue;

            for (int j = i+1; j < dpMemo.length; j++) {
                if (Math.abs(nums[i]-nums[j])<=target) {
                    dpMemo[j] = Math.max(dpMemo[j], 1+dpMemo[i]);
                }
            }
        }


        return dpMemo[dpMemo.length-1];
    }

    public static void main(String[] args) {
        
        int[] arr;
        int target;

        arr = new int[]{1,3,6,4,1,2};
        target = 2;
        System.out.println(myMaxJumps(arr, target, 0, new HashMap<Integer, Integer>()));
        System.out.println(myMaxJumps(arr, 3, 0, new HashMap<Integer, Integer>()));
        System.out.println(myMaxJumps(arr, 0, 0, new HashMap<Integer, Integer>()));
        
        arr = new int[] {1,0,2};
        target =1;
        System.out.println(myMaxJumps(arr, target, 0, new HashMap<Integer, Integer>()));


        System.out.println("Hint approach:");

        arr = new int[]{1,3,6,4,1,2};
        System.out.println(hintApproach(arr, 2));
        System.out.println(hintApproach(arr, 3));
        System.out.println(hintApproach(arr, 0));
        
        arr = new int[] {1,0,2};
        System.out.println(hintApproach(arr, 1));
        arr = new int[] {0,2,1,3};
        System.out.println(hintApproach(arr, 1));

        

    }
}
