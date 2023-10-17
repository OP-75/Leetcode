public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return mySolution(s1, s2, s3);
    }

    private boolean mySolution(String s1, String s2, String s3) {
        
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();

        int[][] memo = new int[c1.length+1][c2.length+1];

        return recursiveDP(c1, c2, c3, 0, 0, 0, memo);
    }

    private boolean recursiveDP(char[] c1, char[] c2, char[] c3, int i, int j, int k, int[][] memo) {
        if (i>=c1.length && j>=c2.length && k>=c3.length) {
            return true;
        }


        if (i<c1.length && j<c2.length && memo[i][j]==-1) {
            return false;
        }


        boolean isInterleaving = false;
        if (i<c1.length && k<c3.length && c1[i]==c3[k]) {
            System.out.println("(i) c1["+i+"] = "+c1[i]+", c3["+k+"] = "+c3[k]);
            isInterleaving = recursiveDP(c1, c2, c3, i+1, j, k+1, memo);
        }
        
        if (isInterleaving) {
            return isInterleaving;
        }
        
        if (j<c2.length && k<c3.length && c2[j]==c3[k]) {
            System.out.println("(k) c2["+j+"] = "+c2[j]+", c3["+k+"] = "+c3[k]);
            isInterleaving = recursiveDP(c1, c2, c3, i, j+1, k+1, memo);
        }

        if (i<c1.length && j<c2.length) {
            memo[i][j] = -1;
        }        

        return isInterleaving;
    }

    public static void main(String[] args) {
        InterleavingString o = new InterleavingString();

        System.out.println(o.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }


}
