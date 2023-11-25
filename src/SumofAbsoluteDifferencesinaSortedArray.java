import java.util.*;

public class SumofAbsoluteDifferencesinaSortedArray {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        // return mySolution(nums); //! gives TLE
        return hintSolution(nums);
    }

    private int[] hintSolution(int[] nums) {
        
        int[] result = new int[nums.length];

        int[] prefixSumArr = new int[nums.length+1];
        int[] suffixSumArr = new int[nums.length+1];
        

        //fill prefix sum
        for (int i = 1; i < prefixSumArr.length; i++) {
            //for 0th indx since there are no elements before it therefore prefixSum[0] = 0
            prefixSumArr[i] = prefixSumArr[i-1] + nums[i-1];   //ie sum of all previos elements
        }

        //fill suffix sum
        for (int i = nums.length-2; i >= 0; i--) { //for last element since there are no nums after it therefore suffixSum[lastIndx] = 0
            suffixSumArr[i] = suffixSumArr[i+1] + nums[i+1]; 
        }


        for (int i = 0; i < nums.length; i++) {
            
            result[i] += nums[i]*((i-1)+1) - prefixSumArr[i]; //sum of all the elements before i
            result[i] += suffixSumArr[i] - nums[i]*(nums.length-i-1); //sum of all the elements after i minus nums[i]*number of elements after i

        }



        return result;
        
    }

    private int[] mySolution(int[] nums) {
        //! gives TLE 
       
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        //stores value - freq pair

        for (int i : nums) {
            int count = freqMap.getOrDefault(i, 0);
            freqMap.put(i, count+1);
        }
        
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            
            if (i!=0 && nums[i]==nums[i-1]) {
                result[i] = result[i-1];
                continue;
            }

            for (Map.Entry<Integer, Integer> en : freqMap.entrySet()) {
                result[i] += Math.abs(nums[i]-en.getKey())*en.getValue();
            }

        }

        return result;
    }

}
