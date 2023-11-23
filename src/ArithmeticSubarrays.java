import java.util.*;

public class ArithmeticSubarrays {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {

        List<Boolean> ans = new ArrayList<>();


        for (int i = 0; i < r.length; i++) {
            

            int[] tmpNums = Arrays.copyOfRange(nums,l[i], r[i]+1); 
            //! make copy caz sorting will move the nums around giving wrong answer in next iteration if next indexes overlap with curr ones

            Arrays.sort(tmpNums);

            int commonDiff = tmpNums[1] - tmpNums[0];
            boolean isArithmetic = true;

            for (int j = 1; j < tmpNums.length; j++) {
                if((tmpNums[j] - tmpNums[j-1])!=commonDiff){
                    isArithmetic = false;
                    break;
                }
            }

            ans.add(isArithmetic);
        
        }


        return ans;

    }

}
