import java.util.*;

public class ReductionOperationstoMaketheArrayElementsEqual {

    public int reductionOperations(int[] nums) {
        // return mySolution(nums); //gives TLE!!!!!
        // return mySolution2(nums); //this solution simulates the whole problem/solution
        return mySolution3(nums);
    }

    private int mySolution3(int[] nums) {
       
        //MOST EFFICIENT SOLUTION

        Arrays.sort(nums);
        
        /*
         * in this solution we count all the numbers like,, (FIRST SORT THE ARR) lets say we have input arr = [1,1,2,2,3]
         * so start from the end and chage all 3's to 2 (curr opsCount = 0)
         * [1,1,2,2,2] opsCount = num of 3 = 1
         * now change all the 2's to one so
         * [1,1,1,1,1] , all elements are equal so end the opertion
         * 
         * So in short u just have to count from 1st occurence of every element from the back till the end
         * ie 
         * 
         * sort(nums)
         * for i from end to start:
         *      if i == 0:
         *            break //we dont want to count from 0 to end caz all elements are getting changed to nums[0] at the end of the program
         * 
         *      if nums[i] == first occurence of element:
         *          opsCount += (arr.len - i) //we have to count ALL the element that we have changed at the end of the array too!
         *        
         * 
        */

        int opsCount = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            
            if (i==0) {
                break;
            }

            if (nums[i]==nums[i-1]) { 
                //to get the 1st occurence of each element
                continue;
            }
            else{
                opsCount += (nums.length-i);
            }

        }
        

        return opsCount;

    }

    private int mySolution2(int[] nums) {
        
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int i : nums) {
            int count = tm.getOrDefault(i, 0);
            tm.put(i, count+1);
        }

        //remember TreeMap is in acending order

        int opsCount = 0;
        while (tm.size()>1) {

            Map.Entry<Integer, Integer> largestEntry = tm.lastEntry();
            tm.remove(largestEntry.getKey());

            //now convert all the largest to nextLargest

            Map.Entry<Integer, Integer> nextLargestEntry = tm.lastEntry();
            int newNextLargestCount = nextLargestEntry.getValue() + largestEntry.getValue(); //we changed all the largest entrys to nextLargest
            //now put the new count in map
            tm.put(nextLargestEntry.getKey(), newNextLargestCount);

            opsCount += largestEntry.getValue(); //we changed all the largest entrys to nextLargest

            
        }
        
        
        return opsCount;
    }

    private int mySolution(int[] nums) {
        
        //GIVES TLE!!!!!!!!!!!
        
        Arrays.sort(nums);

        // the following contains {largest_indx, next_largest_element}
        int[] largestIndxAndNextLargestElementArr = getLargestAndNextLargest(nums);
        // System.out.println(Arrays.toString(largestIndxAndNextLargestElementArr));
        int opsCont = 0;

        while (largestIndxAndNextLargestElementArr[0]!=-1 && largestIndxAndNextLargestElementArr[1]!=-1) {
            
            for (int i = largestIndxAndNextLargestElementArr[0]; i < nums.length; i++) {
                
                nums[i] = largestIndxAndNextLargestElementArr[1];
                opsCont++;

            }

            largestIndxAndNextLargestElementArr = getLargestAndNextLargest(nums);
            // System.out.println(Arrays.toString(largestIndxAndNextLargestElementArr));
        }
        
        
        
        return opsCont;
    }
    
    
    private int[] getLargestAndNextLargest(int[] nums){

        int largest=Integer.MIN_VALUE,largest_indx = -1;
        
        for (int i = nums.length-1; i >= 0; i--) {
            if (i!=0 && nums[i]==nums[i-1]) {
                // System.out.println("continue");
                continue;
            }

            if(i==0 || nums[i]!=nums[i-1]){
                largest = nums[i];
                largest_indx = i;
                break; //IMP!!! put break or elese the loop will run till its 0 for eg arr = [1,3,5]
            }
        }

        System.out.println();

        if (largest_indx==0) {
            // System.out.println(largest+" "+largest_indx+" ");
            // System.out.println(Arrays.toString(nums));
            return new int[]{-1, -1}; //meaning all elements are equal!!!!!!!
        }

        return new int[]{largest_indx, nums[largest_indx-1]}; //since largest_indx is the largest with minimum `i` the indx right before it hold the `next_largest` element
        
    }

    public static void main(String[] args) {
        
        ReductionOperationstoMaketheArrayElementsEqual o = new ReductionOperationstoMaketheArrayElementsEqual();

        int[] nums = new int[] {5,1,3};
        System.out.println(o.reductionOperations(nums));

        //better comments
        // ? this is better comments question
        // ! whtddddf
        // rionvoier
        // TODO: to do something

    }
    
}
