import java.util.List;

// ! Incomplete code, this code gives TLE, see leetcode hint for hints

public class DiagonalTraverseII {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        return mySolution(nums);
    }

    private int[] mySolution(List<List<Integer>> nums) {

        int ansArrSize = 0;
        for (int i = 0; i < nums.size(); i++) {
            ansArrSize += nums.get(i).size();
        }

        int[] ans = new int[ansArrSize];
        int ansIndx = 0;

        for (int i = 0; i < nums.size(); i++) {

            int r = i;
            int c = 0;

            while (r >= 0) {
                int numOfColsInCurrRow = nums.get(r).size();
                if (c < numOfColsInCurrRow) {
                    ans[ansIndx] = nums.get(r).get(c);
                    ansIndx++;
                }

                r--;
                c++;
            }

        }
        

        

        return ans;
    }

}
