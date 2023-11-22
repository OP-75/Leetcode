import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class DiagonalTraverseII {
    
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // return mySolution(nums);
        return mySolution2(nums);
    }
    
    private int[] mySolution(List<List<Integer>> nums) {
        // ! This solution gives TLE

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


    private int[] mySolution2(List<List<Integer>> nums) {

        int ansArrSize = 0;
        for (int i = 0; i < nums.size(); i++) {
            ansArrSize += nums.get(i).size();
        }

        int[] ans = new int[ansArrSize];

        PriorityQueue<NumNode> pq = new PriorityQueue<>(new NumNodeComparator()); 
        for (int i = 0; i < nums.size(); i++) {

            for (int j = 0; j < nums.get(i).size(); j++) {
                pq.add(new NumNode(i+j, i, j, nums.get(i).get(j)));
            }

        }


        for (int i = 0; i < ans.length; i++) {
            ans[i] = pq.poll().val;
        }
        
        return ans;
    }

}


class NumNode{
    int sum, row, col, val;
    NumNode(int sum, int row, int col, int val){
        this.sum = sum;
        this.row = row;
        this.col = col;
        this.val = val;
    }

}
class NumNodeComparator implements Comparator<NumNode>{
    @Override
    public int compare(NumNode o1, NumNode o2){
        if (o1.sum==o2.sum) {
            return Integer.compare(o2.row, o1.row); // ! IMP this is reverse comparion(since o2 & o1 places are flipped) ie we will get rows in decending order if o1.sum = o2.sum
        }
        else{
            //compare sum normally
            return Integer.compare(o1.sum, o2.sum);
        }
    }
}
