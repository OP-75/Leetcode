import java.util.*;

public class CountNicePairsinanArray {

    public int countNicePairs(int[] nums) {

        int[] reverseNumsArr = reverseNumsInArr(nums);

        //! rearangged condition arr: The condition can be rearranged to (nums[i] - rev(nums[i])) == (nums[j] - rev(nums[j])).
        int[] rearangged = new int[nums.length];

        for (int i = 0; i < reverseNumsArr.length; i++) {
            rearangged[i] =  nums[i] - reverseNumsArr[i];
        }


        HashMap<Integer, Long> numCountMap = new HashMap<>(nums.length);
        
        System.out.println(Arrays.toString(rearangged));

        long count = 0;
        for (int i = 0; i < rearangged.length; i++) {

            
            long tmpCount = numCountMap.getOrDefault(rearangged[i], 0L);
            count += tmpCount;
            /*
             * Ok so the reason y we are doing count += tmpCount; is caz: consider the rearangged arr = [-18, 9, -18, -18, 9]
             * now consider the 1st '-18' rearranged[0] = -18 it isnt in hashmap so tmpCount=0, before it there are no '-18' so it form 0 pairs so far so count += 0(tmpCount)   
             * now consider rearangged[2] = -18, before it there is 1 '-18' (tmpCount/freq of '-18' in hm=1 since we encountered '-18' one time before) so we can form 1 pair so count += 1(tmpCount)   
             * now considered rearangged[3] = -18, in hashmap we already have -18:2, ie there are two '-18' before it forms 2 pairs so count += 2(tmpCount)  
            */
            numCountMap.put(rearangged[i], tmpCount+1);
        }


        return (int)(count%(long)(Math.pow(10, 9)+7));

    }

    private int[] reverseNumsInArr(int[] nums){

        int[] ans = new int[nums.length];

        for (int i = 0; i < ans.length; i++) {          
           StringBuilder n = new StringBuilder(Integer.toString(nums[i]));
           n = n.reverse();
           ans[i] =  Integer.parseInt(n.toString());
        }

        return ans;

    }

}
