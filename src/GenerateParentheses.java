import java.util.*;

class Solution {

    List<String> ans = new ArrayList<>(); 

    private void recursive(String ansTillNow, int n, int countOfOpening, int countOfClosing){
        
        if (countOfOpening==countOfClosing && countOfOpening == n) {
            ans.add(ansTillNow);
            return;
        }

        if(countOfOpening>countOfClosing){
        recursive(ansTillNow+")", n, countOfOpening, countOfClosing+1);
        }

        if (countOfOpening<n) {
            recursive(ansTillNow+"(", n, countOfOpening+1, countOfClosing);
        }



    }
    

    private List<String> mySolution(int n){

        recursive("", n, 0, 0);
        return ans;

    }

    public List<String> generateParenthesis(int n) {
        return mySolution(n);
    }
}


public class GenerateParentheses {
    

    public static void main(String[] args) {

        Solution o = new Solution();


        System.out.println(o.generateParenthesis(3));
    }

}
