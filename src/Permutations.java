import java.util.*;

public class Permutations {
    

    private void mySolution(int[] nums, List<Integer>tmp, HashSet<Integer> indxsAlreadyPicked,List<List<Integer>> ans){

        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
        }

        for (int i = 0; i < nums.length; i++) {
            if(indxsAlreadyPicked.contains(i)){
                continue;
            }

            tmp.add(nums[i]);
            indxsAlreadyPicked.add(i);
            mySolution(nums, tmp, indxsAlreadyPicked, ans);
            tmp.remove(tmp.size()-1);
            indxsAlreadyPicked.remove(i);
        }

    }

    private void mySolution2(int[] nums, List<Integer>tmp, boolean[] indxsAlreadyPicked,List<List<Integer>> ans){
        //this solution uses an array instead of hashset for "indxsAlreadyPicked" so it could be faster

        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
        }

        for (int i = 0; i < nums.length; i++) {
            if(indxsAlreadyPicked[i]){
                continue;
            }

            tmp.add(nums[i]);
            indxsAlreadyPicked[i] = true;
            mySolution2(nums, tmp, indxsAlreadyPicked, ans);
            tmp.remove(tmp.size()-1);
            indxsAlreadyPicked[i] = false;
            
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> ans = new ArrayList<>(nums.length+1);
        // mySolution(nums, new ArrayList<>(nums.length+1), new HashSet<>(nums.length+1), ans);
        mySolution2(nums, new ArrayList<>(nums.length+1), new boolean[nums.length], ans);
        return ans;
    }

    public static void main(String[] args) {
        Permutations o = new Permutations();
        System.out.println(o.permute(new int[]{1,2,3}));
    }

}
