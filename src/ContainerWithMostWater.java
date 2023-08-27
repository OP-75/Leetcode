public class ContainerWithMostWater {

    private static int mySolution(int[] height){

        int ans = 0;
        
        //bruteforce solution O(n^2)
        // for (int i = 0; i < height.length; i++) {
        //     for (int j = i+1; j < height.length; j++) {
        //         int wallHeight = (int)Math.min(height[i], height[j]);
        //         int breadth = (int) Math.abs(i-j);
        //         int volume = wallHeight*breadth;
        //         if (volume>ans) {
        //             ans = volume;
        //         }
        //     }
        // }


        //ok so a 2 pointer approach, the idea is comare height at up & down position, whichever has the smaller height has to move left or right (depending on if its up or down)
        int up = height.length-1, down=0;
        ans = 0;
        while (up>down) {
            
            int wallHeight = (int)Math.min(height[up], height[down]);
            int breadth = (int) Math.abs(up-down);
            int volume = wallHeight*breadth;
            if (volume>ans) {
                ans = volume;
            }

            if(height[up]<height[down]){
                up--;
            }
            else{
                down++;
            }

        }



        

        return ans;
    }

    public static void main(String[] args) {

        System.out.println(mySolution(new int[]{1,8,6,2,5,4,8,3,7})); //expected = 49
    }
}
