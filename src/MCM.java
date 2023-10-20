public class MCM {

    //IMP This code was written using striver's reference:
    // https://youtu.be/vRVfmbCFW7Y?si=dWEOu0XKJFZxHSDH

	public static int matrixMultiplication(int[] arr , int N) {
		return mySolution(arr, N);
    }

    private static int mySolution(int[] arr, int N){
        int[][] memo = new int[N][N];
        return recusiveDP(arr, 1, N-1, memo);
    }

    private static int recusiveDP(int[] arr, int i, int j, int[][] memo) {
        if (i==j) {
            return 0; //multiplying by itself takes 0 scalar multiplication
        }

        if (memo[i][j]!=0) {
            return memo[i][j];
        }

        int minScalarMul = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int ans = (arr[i-1] * arr[k] * arr[j]) + recusiveDP(arr, i, k, memo) + recusiveDP(arr, k+1, j, memo);

            minScalarMul = Math.min(minScalarMul, ans); 
        }

        memo[i][j] = minScalarMul;
 
        return minScalarMul;
    }

    public static void main(String[] args) {
        //this code was checked & tested on: https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_975344
        System.out.println(matrixMultiplication(new int[]{4, 5, 3, 2}, 4));
    }
	
}


