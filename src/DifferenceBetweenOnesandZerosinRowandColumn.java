public class DifferenceBetweenOnesandZerosinRowandColumn {

    public int[][] onesMinusZeros(int[][] grid) {

        int[] onesRow = new int[grid.length];
        int[] onesColumn = new int[grid[0].length];
        int[] zeroRow = new int[grid.length];
        int[] zeroColumn = new int[grid[0].length];

        //count in each row simultaneously increment the respectove count in zeroColumn or zeroRow
        for (int r = 0; r < grid.length; r++) {
            int countof1 = 0;
            int countof0 = 0;
            for (int i = 0; i < grid[r].length; i++) {
                if (grid[r][i]==1) {
                    countof1++;
                    onesColumn[i]++;
                } else {
                    countof0++;
                    zeroColumn[i]++;
                }
            }

            onesRow[r] = countof1;
            zeroRow[r] = countof0;
        }




        int[][] ans = new int[grid.length][grid[0].length];

        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                
                ans[r][c] = (onesRow[r]+onesColumn[c]) - (zeroRow[r]+zeroColumn[c]);

            }
        }

        return ans;

    }

}
