import java.util.*;

public class KnightDialer {
    public int knightDialer(int n) {
        return mySolution(n);
    }

    private int mySolution(int n) {
        int[][] pad = {{1,2,3}, {4,5,6}, {7,8,9}, {-1,0,-1}};

        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        knightMoves(hm, pad); //filling the with list of pos where knight can go from every start position

        long noOfDistinctPhoneNumbers = 0;

        long[][] memo = new long[10][n+1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }


        for (int i = 0; i < pad.length; i++) {
            for (int j = 0; j < pad[0].length; j++) {
                if (pad[i][j]==-1) {
                    continue;
                }

                noOfDistinctPhoneNumbers +=  (knightDFS(pad[i][j], n-1, hm, memo)%(long)(Math.pow(10, 9)+7L)) ;  //n-1 caz we already have one number pad[i][j]
            }
        }
        
        

        return (int) (noOfDistinctPhoneNumbers%(long)(Math.pow(10, 9)+7L));
    }

    private long knightDFS(int start, int n, HashMap<Integer, List<Integer>> hm, long[][] memo) {
        if (n==0) {
            return 1;
        }

        if (memo[start][n]!=-1) {
            return memo[start][n];
        }

        List<Integer> posToMove = hm.get(start);
        long numbers = 0;
        for (Integer pos : posToMove) {
            numbers += knightDFS(pos, n-1, hm, memo);
        }


        memo[start][n] = numbers%(long)(Math.pow(10, 9)+7L);


        return numbers%(long)(Math.pow(10, 9)+7L);
    }

    
    private void knightMoves(HashMap<Integer, List<Integer>> hm, int[][] pad) {

        int[][] moves = {{2,1},{2,-1}, {-2,1},{-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2}};

        for (int startR = 0; startR < pad.length; startR++) {
            for (int startC = 0; startC < pad[0].length; startC++) {

                if (pad[startR][startC]==-1) {
                    continue;
                }
                
                List<Integer> placesKnightCanMoveFromThisStart = hm.getOrDefault(pad[startR][startC], new ArrayList<>());
                for (int[] move : moves) {
                    int newR = startR + move[0];
                    int newC = startC + move[1];
                    if (isWithinBounds(pad, newR, newC) && pad[newR][newC]!=-1) {
                        placesKnightCanMoveFromThisStart.add(pad[newR][newC]);
                    }
                }

                hm.put(pad[startR][startC], placesKnightCanMoveFromThisStart);
                
            }
        }

    }


    private boolean isWithinBounds(int[][] pad, int r, int c){
        return r>=0 && r<pad.length && c>=0 && c<pad[0].length;
    }

}
