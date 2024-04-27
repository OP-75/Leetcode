class Solution {

    public int findRotateSteps(String ring, String key) {

        char ringArr[] = ring.toCharArray();

        char keyArr[] = key.toCharArray();

        int dp[][] = new int[ringArr.length][keyArr.length];
        //approach - there are only 3 possiblites = move dial clockwise, move dial anticlockwise or press the button IF the curr char at 12:00 == key char
        // if `keyPtr` >= keyArr.length then we have pressed all keys so return 0

        //this solution is pretty inefficient but we can run it
        // here is a much more efficient solution: https://youtu.be/NOgnlTXidSs?si=kkAZItQOKCasxDDZ
        return moveDial(ringArr, keyArr, 0, 0, 0, dp);

    }

    private int moveDial(char[] ringArr, char[] keyArr, int ringPtr, int keyPtr, int rotationsTillNow, int dp[][]) {

        if (keyPtr >= keyArr.length) {
            return 0;
        }

        if (ringPtr > ringArr.length - 1 || rotationsTillNow > ringArr.length * keyArr.length) {
            // ie ptr out of bounds or we have spun the dial more than `keyArr.length` full
            // rotation which is the max ans
            return 99999;
        }

        if (dp[ringPtr][keyPtr]!=0) {
            return dp[ringPtr][keyPtr];
        }
        // ---------end of base conditions--------

        int takeSteps = Integer.MAX_VALUE;
        if (keyArr[keyPtr] == ringArr[ringPtr]) {
            takeSteps = 1 + moveDial(ringArr, keyArr, ringPtr, keyPtr + 1, rotationsTillNow, dp);
        }

        // rotate dial
        int clockWiseSteps = 1
                + moveDial(ringArr, keyArr, rotateDialClockWise(ringArr, ringPtr), keyPtr, rotationsTillNow + 1, dp);
        int antiClockWiseSteps = 1
                + moveDial(ringArr, keyArr, rotateDialAntiClockWise(ringArr, ringPtr), keyPtr, rotationsTillNow + 1, dp);
        
        int minSteps = Math.min(takeSteps, Math.min(clockWiseSteps, antiClockWiseSteps));
        dp[ringPtr][keyPtr] = minSteps;

        return minSteps;


    }

    private int rotateDialAntiClockWise(char[] ringArr, int ptr) {
        if (ptr == ringArr.length - 1) {
            ptr = 0;
            return ptr;
        } else {
            ptr++;
            return ptr;
        }
    }

    private int rotateDialClockWise(char[] ringArr, int ptr) {
        if (ptr == 0) {
            ptr = ringArr.length - 1;
            return ptr;
        } else {
            ptr--;
            return ptr;
        }
    }
}