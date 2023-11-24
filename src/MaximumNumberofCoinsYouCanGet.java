import java.util.Arrays;

class MaximumNumberofCoinsYouCanGet {
    public int maxCoins(int[] piles) {
        return mySolution(piles);
    }

    private int mySolution(int[] piles) {
        
        Arrays.sort(piles);
        piles = reverseArr(piles);

        int aliceIndx=0, meIndx=1, bobIndx = piles.length-1;

        int myCoinCount = 0;

        while (bobIndx>meIndx && aliceIndx<piles.length && meIndx<piles.length ) {

            myCoinCount += piles[meIndx];

            aliceIndx = meIndx+1; //update alice index 
            meIndx = aliceIndx+1; // my index will be alice + 1

            bobIndx--; //since bobIndx is at the end of the arr (not consecutive to meIndx)

            //bobIndx & meIndx,aliceIndx  will converge to the middle the array (i think)
            
        }
        
        return myCoinCount;
    }

    private int[] reverseArr(int[] arr){

        int[] revAns = new int[arr.length];
        int ansPtr = 0;

        for (int i = arr.length-1; i >= 0; i--, ansPtr++) {
            revAns[ansPtr] = arr[i];
        }

        return revAns;
    }
}