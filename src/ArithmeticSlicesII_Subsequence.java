import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

// ! Not working :(

public class ArithmeticSlicesII_Subsequence {
    public int numberOfArithmeticSlices(int[] nums) {
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        HashMap<Integer, int[]> memo = new HashMap<>();
        //so that we can store memo[hashcode of list][i] 

        return countSlices(nums, 0, new ArrayList<Integer>(), memo);
    }
    
    private int countSlices(int[] nums, int i, ArrayList<Integer> arithmeticNumsList, HashMap<Integer, int[]> memo) {
        
        if (i>=nums.length) {
            
            if (arithmeticNumsList.size()>=3) {
                System.out.println(arithmeticNumsList);
                return 1;
            }
            return 0;
        }
        
        int hashcode = arithmeticNumsList.hashCode();
        
        
        // if (memo.containsKey(hashcode) && memo.get(hashcode)[i]!=0 && hashcode!=-1) {
        //     System.out.println(arithmeticNumsList);
        //     return memo.get(hashcode)[i];
        // }


        int numOfSlices = 0;

        int n = arithmeticNumsList.size();
        if (n<2 || (nums[i]-arithmeticNumsList.get(n-1))==(arithmeticNumsList.get(1)-arithmeticNumsList.get(0))) {
            //can only take, if there are less than 2 elements in the list OR the diff between last element of list amd curr == diff between 1st 2 elements

            arithmeticNumsList.add(nums[i]);
            numOfSlices += countSlices(nums, i+1, arithmeticNumsList, memo);
            arithmeticNumsList.remove(arithmeticNumsList.size()-1);
        }
        
        
        //not take:
        numOfSlices += countSlices(nums, i+1, arithmeticNumsList, memo);


        int[] correspondingHashcodeArr = memo.getOrDefault(hashcode, new int[nums.length]);
        correspondingHashcodeArr[i] = numOfSlices;
        memo.put(hashcode, correspondingHashcodeArr);
        

        return numOfSlices;
    }

    
    public static void main(String[] args) {
        int[] a = {2,4,6,8,10};
        // a = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        // a = new int[]{79,20,64,28,67,81,60,58,97,85,92,96,82,89,46,50,15,2,36,44,54,2,90,37,7,79,26,40,34,67,64,28,60,89,46,31,9,95,43,19,47,64,48,95,80,31,47,19,72,99,28,46,13,9,64,4,68,74,50,28,69,94,93,3,80,78,23,80,43,49,77,18,68,28,13,61,34,44,80,70,55,85,0,37,93,40,47,47,45,23,26,74,45,67,34,20,33,71,48,96};
        ArithmeticSlicesII_Subsequence o = new ArithmeticSlicesII_Subsequence();

        System.out.println(o.numberOfArithmeticSlices(a));
    }


    
}
