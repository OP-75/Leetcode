import java.lang.reflect.Array;
import java.util.Arrays;

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        return mySolution(s,t);
    }

    private int mySolution(String s, String t) {
        char[] sc = s.toCharArray(), tc = t.toCharArray();
        int[][] memo = new int[sc.length][tc.length];

        //IMP for avoiding TLE
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return recursiveDP(sc, tc, 0, 0, memo);
    }

    private int recursiveDP(char[] sc, char[] tc, int i, int j, int[][] memo) {
        
        if (i>=sc.length && j>=tc.length) {
            return 1;
        }
        if (i>=sc.length && j<tc.length) {
            return 0;
        }

        if (i<sc.length && j<tc.length && memo[i][j]!=-1) {
            return memo[i][j];
        }

        int numOfSubSeq = 0;

        if (i<sc.length && j<tc.length && sc[i]==tc[j]) {
            //if chars are = then take
            numOfSubSeq += recursiveDP(sc, tc, i+1, j+1, memo);
        }

        //try NOT TAKING curr char i regardless if sc[i]==tc[j]
        numOfSubSeq += recursiveDP(sc, tc, i+1, j, memo);

        if (i<sc.length && j<tc.length) {
            memo[i][j] = numOfSubSeq;
        }
        
        return numOfSubSeq;
    }

    public static void main(String[] args) {
        DistinctSubsequences o = new DistinctSubsequences();
        System.out.println(o.numDistinct("rabbbit", "rabbit"));
        System.out.println(o.numDistinct("babgbag", "bag"));

    }

}
