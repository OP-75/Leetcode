import java.util.*;

public class LongestConsecutiveSequence {

    private static int mySolution(int[] nums){


        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        System.out.println(set);


        //store all start vals in a set
        HashSet<Integer> startVals = new HashSet<>(); //store all the start vals of sequences
        for (Integer integer : set) {
            if (!set.contains(integer-1)) {
                startVals.add(integer);
            }
        }



        int longestSeq = 0;
        for (Integer integer : startVals) { //these are start vals of all seqences in arr
            int tmpCounter = 1; //start from 1 since set already contains the starting value
            while (set.contains(integer+1)) {
                tmpCounter++;
                integer = integer + 1;
            }

            if (tmpCounter>longestSeq) {
                longestSeq = tmpCounter;
            }

        }
        //the above loops time complexity is O(n) since outer loop only has starting vals of sequences and inner while loop only iterates over the seqence starting with "integer" once so all in all it iterates over the whole array once


        return longestSeq;
    }

    

    public static void main(String[] args) {
     
        int[] arr;

        arr = new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        System.out.println(mySolution(arr));

    }
}
