import java.util.ArrayList;
import java.util.List;


//THIS TOPIC IS EXTREMLY IMPORTANT!!!!!!!!
public class Subsets {
    
    public void mySolution(int[] nums, int i, List<Integer> tmpList, List<List<Integer>> ans){

        if(i>=nums.length){
            ans.add(new ArrayList<>(tmpList));
            return;
        }

        tmpList.add(tmpList.size(), nums[i]);
        mySolution(nums, i+1, tmpList, ans);

        tmpList.remove(tmpList.size()-1);
        mySolution(nums, i+1, tmpList, ans);
        
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();

        mySolution(nums, 0, tmpList, ans);

        return ans;
    }

    public static void main(String[] args) {
        Subsets o = new Subsets();

        System.out.println(o.subsets(new int[]{1,2,3}).toString());
    }

}
