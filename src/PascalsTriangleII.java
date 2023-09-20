import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    

    //since we are doing recusion at start & there is no previous arrary initalized before recursion, so space complexity = O(rowIndx)
    private List<Integer> mySolution(int rowIndex){

        if(rowIndex==0){
            //remember rowIndx is 0-indexed
            List<Integer> ans = new ArrayList<>(2);
            ans.add(1);
            return ans;
        }
        else if (rowIndex==1) {
            List<Integer> ans = new ArrayList<>(3);
            ans.add(1);
            ans.add(1);
            return ans;
        }

        List<Integer> prevRow = mySolution(rowIndex-1);

        List<Integer> ans = new ArrayList<>(rowIndex+2); //set initial capacity to improve time
        ans.add(1);
        for (int i = 0; i < prevRow.size() - 1; i++) {
            int sum = prevRow.get(i) + prevRow.get(i+1);
            ans.add(sum); 
        }
        ans.add(1); // put 1 at first & last indx in list


        return ans;
    }

    public List<Integer> getRow(int rowIndex) {
        return mySolution(rowIndex);
    }


    public static void main(String[] args) {
        PascalsTriangleII o = new PascalsTriangleII();
        // System.out.println(o.getRow(0));
        // System.out.println(o.getRow(1));
        System.out.println(o.getRow(3));
    }

}
