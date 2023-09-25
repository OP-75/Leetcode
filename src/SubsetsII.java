import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SubsetsII {

    private void mySolution(int[] nums, int p, List<Integer> tmp, HashSet<List<Integer>> ans){

        if (p==nums.length) {
            List<Integer> tmpToSort = new ArrayList<>(tmp);
            Collections.sort(tmpToSort);
            ans.add(tmpToSort);
            return;
        }

        tmp.add(nums[p]);
        mySolution(nums, p+1, tmp, ans);

        tmp.remove(tmp.size()-1);
        mySolution(nums, p+1, tmp, ans);
    }

    private void neetcodeSolution(int[] nums, int p, List<Integer> tmp, List<List<Integer>> ans){
        //for this soultion to work u need to sort the nums array before calling this function

        if (p==nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        
        tmp.add(nums[p]);
        neetcodeSolution(nums, p+1, tmp, ans);

        tmp.remove(tmp.size()-1);
        int n=1;
        // we are doing this so we can skip over all duplicate numbers
        while (p+n<nums.length && nums[p]==nums[p+n]) {
            n++;
        }
        neetcodeSolution(nums, p+n, tmp, ans);
    }


    
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // HashSet<List<Integer>> ans = new HashSet<>();
        // mySolution(nums, 0, new ArrayList<>(), ans);
        // List<List<Integer>> ansList = new ArrayList<>(ans);
        // return ansList;


        //for this soultion to work u need to sort the nums array before calling this function
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        neetcodeSolution(nums, 0, new ArrayList<>(), ans);
        List<List<Integer>> ansList = new ArrayList<>(ans);
        return ansList;
    }

}
