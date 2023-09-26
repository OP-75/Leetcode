import java.util.*;

public class CombinationSumII {

    private void mySolution(int[] candidates, int target, int startIndx, List<Integer> tmp, List<List<Integer>> ans){

        if(target==0){
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if(target<0 || startIndx>=candidates.length){
            return;
        }

        int num = candidates[startIndx];

        tmp.add(num);
        mySolution(candidates, target-num, startIndx+1, tmp, ans);
        
        tmp.remove(tmp.size()-1);
        int n = 1;
        while (startIndx+n<candidates.length && candidates[startIndx]==candidates[startIndx+n]) {
            n++;
        }
        mySolution(candidates, target, startIndx+n, tmp, ans);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> ans = new ArrayList<>();
        //Sorting is IMP!!! otherwise the solution wont work
        Arrays.sort(candidates); 

        mySolution(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

}
