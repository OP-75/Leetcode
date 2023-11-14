import java.util.*;

public class UniqueLength_3PalindromicSubsequences {

    //Time complexity O(n^2) (i think)
    // Space complexity = O(n)

    public int countPalindromicSubsequence(String s) {

        HashSet<Character> charsAlreadyDealtWith = new HashSet<>();
        char[] sarr = s.toCharArray();

        int ans = 0;
        

        for (int leftIndx = 0; leftIndx < sarr.length; leftIndx++) {
            if (charsAlreadyDealtWith.contains(sarr[leftIndx])) {
                continue;
            }

            //find the same char from the end/with max indx to get the right ans
            int rightIndx = sarr.length - 1;
            while (sarr[leftIndx]!=sarr[rightIndx]) {
                rightIndx--;
            }


            //count all UNIQUE chars between left & right indx (incase of strings like "bbcbaba" where "bbb" will be repeated so use hashset)
            HashSet<Character> uniqueCharsBetweenLeftAndRight = new HashSet<>();
            for (int i = leftIndx+1; i < rightIndx; i++) {
                uniqueCharsBetweenLeftAndRight.add(sarr[i]);
            }

            ans += uniqueCharsBetweenLeftAndRight.size();

            charsAlreadyDealtWith.add(sarr[leftIndx]);
        }

        return ans;
    }

    public static void main(String[] args) {
        UniqueLength_3PalindromicSubsequences o = new UniqueLength_3PalindromicSubsequences();
        System.out.println(o.countPalindromicSubsequence("bbcbaba"));
    }

}
