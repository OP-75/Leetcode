import java.util.*;

public class FrequencyoftheMostFrequentElement {

    public int maxFrequency(int[] nums, int k) {
        // return mySolution(nums, k);
        return neetcodeSolution(nums, k);
    }

    private int neetcodeSolution(int[] nums, int k) {
        
        
        //Solution: https://youtu.be/vgBrQ0NM5vE?si=1oqRn6zS_NbaL7tM

        Arrays.sort(nums);

        int l = 0, r = 0;
        long windowTotal = nums[0];
        int maxFreq = 0;

        while (l<nums.length && r<nums.length) {

            //r-l+1 is the window length
            while (nums[r]*(r-l+1)<=windowTotal+k) {
                maxFreq = Math.max(maxFreq , (r-l+1)); //maxfreq = length of the longest window
                
                r++;
                if (r>=nums.length) {
                    break;
                }
                windowTotal += nums[r]; 
            }
            
            //if above while loop becomes false remove curr element from the window
            windowTotal -= nums[l];
            l++;
        }
        
        
        
        return maxFreq;
    }

    private int mySolution(int[] nums, int k) {

        //my solution is O(n^2)
        
        Arrays.sort(nums);

        int maxFreq = 0;
        for (int i = nums.length-1; i >= 0; i--) {

            if(maxFreq>i+1){
                //This guard clause is veryyyyy IMPORTANT, 
                //without this you will get TLE since the program will check every frequency 
                //even if we have the maximum freq already
                break;
            }

            int tmpFreq = 0;
            int tmpK = k;
            for (int j = i; j >= 0; j--) {
                
                
                tmpK = tmpK - (nums[i]-nums[j]);
                if (tmpK<0) {
                    break;
                }
                tmpFreq += 1;

                maxFreq = Math.max(maxFreq, tmpFreq);
            }
            maxFreq = Math.max(maxFreq, tmpFreq);
            
        }
        
        return maxFreq;
    }


    

}
