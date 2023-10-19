public class EditDistance {
    public int minDistance(String word1, String word2) {
        return mySolution(word1, word2);
    }

    private int mySolution(String word1, String word2) {
        char[] c1 = word1.toCharArray(), c2 = word2.toCharArray();
      
        int[][] memo = new int[c1.length][c2.length];
        
        return recursiveDP(c1, c2, 0, 0, memo);
    }

    private int recursiveDP(char[] c1, char[] c2, int i, int j, int[][] memo) {
        
        if (i>=c1.length && j>=c2.length) {
            return 0;
        }

        if (i<c1.length && j<c2.length && memo[i][j]!=0) {
            return memo[i][j];
        }

        int minOps = Integer.MAX_VALUE;

        if (i<c1.length && j<c2.length && c1[i]==c2[j]) {
            //IMP!!! If curr char of both is same then skip it
            return recursiveDP(c1, c2, i+1, j+1, memo);
        }
        
        int ans=Integer.MAX_VALUE;

        //replace:
        if(i<c1.length && j<c2.length) ans = recursiveDP(c1, c2, i+1, j+1, memo);
        if (ans!=Integer.MAX_VALUE) {
            minOps = Math.min(minOps, 1+ans);
        }

        //deletion: (we can only delete if i<c1.len)
        if(i<c1.length) ans = recursiveDP(c1, c2, i+1, j, memo);
        if (ans!=Integer.MAX_VALUE) {
            minOps = Math.min(minOps, 1+ans);
        }

        //Insertion: (special case: we can only perform insertion on word1 if j < end of word 2 else it will cause stack overflow)
        if(j<c2.length) ans = recursiveDP(c1, c2, i, j+1, memo); //we are inserting on c2[j] char in c1 at indx i-1 (abstraction/imagine)
        if (ans!=Integer.MAX_VALUE) {
            minOps = Math.min(minOps, 1+ans);
        }
        
        if (i<c1.length && j<c2.length) {
            memo[i][j] = minOps;
        }

        return minOps;
    }

    public static void main(String[] args) {
        EditDistance o = new EditDistance();
        System.out.println(o.minDistance("horse", "ros"));
        System.out.println(o.minDistance("intention", "execution"));
        System.out.println(o.minDistance("", "a"));
        System.out.println(o.minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
    }


}
