import java.util.Stack;

//this is a DP problem
//Solution link: https://youtu.be/HAA8mgxlov8?si=Zu3uuQ2Ax4ajpxRk
public class RegularExpressionMatching {
    public static void main(String[] args) {

        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("ab", ".*c"));
        System.out.println(isMatch("aaa", "a*a"));
    }

 

    private static boolean mySolution(char[] s, char[] p, int i, int j) {

        //Non-memoization approach
        
        //base conditions
        if (j>=p.length && i>=s.length) {
            return true;
        }
        if(j>=p.length && i<s.length){
            return false;
        }
    

        //recursion
        boolean match = (i<s.length) && (s[i]==p[j] || p[j]=='.');
        boolean isNextStar = j+1<p.length && p[j+1]=='*';

        if(match && isNextStar){
            boolean isMatching = mySolution(s, p, i+1, j);
            if (isMatching) {
                return true;
            }

            //if it is a match & next is star then too try skipping the star incase if s = "aaa" & "a*a"
            isMatching = mySolution(s, p, i, j+2);
            if (isMatching) {
                return true;
            }
        }
        if(!match && isNextStar){
            boolean isMatching = mySolution(s, p, i, j+2);
            if (isMatching) {
                return true;
            }
        }
        if(match && !isNextStar){
            boolean isMatching = mySolution(s, p, i+1, j+1);
            if (isMatching) {
                return true;
            }
        }
        if (!match && !isNextStar) {
            return false;
        }


        return false;
    }


    
    private static boolean mySolution2(char[] s, char[] p, int i, int j, int[][] memo) {

        //memoization approach
        
        //base conditions
        if (j>=p.length && i>=s.length) {
            return true;
        }
        if(j>=p.length && i<s.length){
            return false;
        }

        //now we know that "j" is for sure < p.len caz above 2 if didnt execute
        //though we dont know if i < s.len

        //check if ans is in memo, if memo[i][j]==0 then that means we havent yet caclulated ans for that i,j
        if(i<s.length && memo[i][j]==-1){
            return false;
        }
        else if(i<s.length && memo[i][j]==1){
            return true;
        }


    


        //recursion
        boolean match = (i<s.length) && (s[i]==p[j] || p[j]=='.');
        boolean isNextStar = j+1<p.length && p[j+1]=='*';

        if(match && isNextStar){
            boolean isMatching = mySolution2(s, p, i+1, j, memo);
            if (isMatching) {
                return true;
            }
            
            //if it is a match & next is star then too try skipping the star incase if s = "aaa" & "a*a"
            isMatching = mySolution2(s, p, i, j+2, memo);
            if (isMatching) {
                return true;
            }

            //put the ans in memo
            memo[i][j] = -1;
            //actually if its true then function wouldve already returned true by now
        }
        if(!match && isNextStar){
            boolean isMatching = mySolution2(s, p, i, j+2, memo);
            if (isMatching) {
                return true;
            }

            //put the ans in memo
            //check i again since !match
            if(i<s.length) memo[i][j] = -1;
            //actually if its true then function wouldve already returned true by now
        }
        if(match && !isNextStar){
            boolean isMatching = mySolution2(s, p, i+1, j+1, memo);
            if (isMatching) {
                return true;
            }
            //put the ans in memo
            memo[i][j] = -1;
            //actually if its true then function wouldve already returned true by now
        }
        if (!match && !isNextStar) {

            //check i again since !match
            //put the ans in memo
            if(i<s.length) memo[i][j] = -1;
            return false;
        }


        return false;
    }



    

    
    
    public static boolean isMatch(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        // return mySolution(sArr, pArr, 0, 0);

        int[][] memo = new int[sArr.length][pArr.length];
        return mySolution2(sArr, pArr, 0, 0, memo);
    }

}
