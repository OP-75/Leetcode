public class TrappingRainWater {

    private static int findRightWall(int[] height, int leftWall){

    

        int rightWall = leftWall+1;
        int tmpMaxHeightIndx = rightWall;

        while(rightWall<height.length){

            if(height[tmpMaxHeightIndx]<height[rightWall]){
                tmpMaxHeightIndx = rightWall;
            }

            if(height[rightWall]>=height[leftWall]){
                break;
            }

            rightWall++;
        }

        if(rightWall==height.length){ //ie it has gone outside the array
            return tmpMaxHeightIndx;
        }
        else{
            return rightWall;
        }

    }

    private static int getWaterBetweenWalls(int[] height, int leftWall, int rightWall){

        int totalWater = 0;
        int tmpPtr = leftWall+1;
        int minHeight = Math.min(height[leftWall], height[rightWall]);
        while (tmpPtr<rightWall) {
            totalWater += minHeight - height[tmpPtr];
            tmpPtr++; 
        }

        return totalWater;

    }


    private static int mySolution(int[] height){

        int waterTrapedAns = 0;
        
        //take 2 ptrs which keep track of left and right wall, where height of right wall should be >= left wall, when u get such a right wall run a ptr between these and with water collected at curr block = min(heigh of left wall, height of right wall) - currBlock
        //then change the right wall to left wall in next iter & the find a right wall again!
        //but what if we have a left wall = 100 and no right wall>= to that size, then we have to keep track of the max height of the wall in case we run out of the arr

        // this is a O(n) solution

        //these are ptrs
        int leftWall = 0, rightWall = 1;
        if(height.length<=2) return waterTrapedAns;

        while (leftWall<height.length-1) {
            
            rightWall = findRightWall(height, leftWall);

            waterTrapedAns += getWaterBetweenWalls(height, leftWall, rightWall);

            leftWall = rightWall;

        }


        return waterTrapedAns;
    }

    public static void main(String[] args) {
        
        System.out.println(mySolution(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }
}
