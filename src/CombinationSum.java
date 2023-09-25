import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombinationSum {

    
    private void mySolution(int[] candidates, int start, int tmpTarget, List<List<Integer>> ans, List<Integer> tmpList){

        if(tmpTarget==0){
            ans.add(new ArrayList<>(tmpList));
            return;
        }
        else if(tmpTarget<0){
            return;
        }


        for (int i = start; i < candidates.length; i++) {
            
            int num = candidates[i];

            tmpList.add(num);
            mySolution(candidates, i, tmpTarget-num, ans, tmpList);
            tmpList.remove(tmpList.size()-1);
        }


    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        
        mySolution(candidates, 0, target, ans, tmpList);

        return ans;
    }


    public static void main(String[] args) {
        CombinationSum o = new CombinationSum();

        System.out.println(o.combinationSum(new int[]{2,3,4,5,6,7}, 7).toString());
    }
}
