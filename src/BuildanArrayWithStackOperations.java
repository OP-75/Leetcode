import java.util.*;

public class BuildanArrayWithStackOperations {


    public List<String> buildArray(int[] target, int n) {
        return bruteForce(target, n);
    }

    private List<String> bruteForce(int[] target, int n) {
        List<String> ans = new ArrayList<>();

        int streamNum = 1, indx = 0;

        while (indx<target.length && streamNum<=n) {
            
            if (streamNum==target[indx]) {
                ans.add("Push");
                indx++;
                streamNum++;
            }
            else{
                ans.add("Push");
                ans.add("Pop");
                indx++;
            }

        }

        return ans;
    }

}
