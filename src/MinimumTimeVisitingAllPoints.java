public class MinimumTimeVisitingAllPoints {

    /*
     * 
     * 
     * This problem requires the knowlege of chebyshev distance to do it efficiently
     * 
        Also when we go diagonally we need to increment `time` by sqrt(2) 
        but since `time` variable is `int` therefore sqrt(2) = 1.414 = 1 (when typecasted to integer). 
        Hence we can use chebyshev distance here!!!!!!
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        // return mySolution(points);

        // This problem requires the knowlege of chebyshev distance
        return mySolution2(points); //much more efficent
    }

    private int mySolution(int[][] points) {

        int time = 0;
        int prevX = points[0][0];
        int prevY = points[0][1];

        for (int i = 1; i < points.length; i++) {

            int[] point = points[i];

            int x = point[0];
            int y = point[1];

            while (prevX != x || prevY != y) {

                if (prevX != x && prevY != y) {

                    if (x > prevX && y > prevY) {
                        prevX += 1;
                        prevY += 1;
                        time += Math.sqrt(2);
                    }
                    if (x < prevX && y > prevY) {
                        prevX -= 1;
                        prevY += 1;
                        time += Math.sqrt(2);
                    }
                    if (x > prevX && y < prevY) {
                        prevX += 1;
                        prevY -= 1;
                        time += Math.sqrt(2);
                    }
                    if (x < prevX && y < prevY) {
                        prevX -= 1;
                        prevY -= 1;
                        time += Math.sqrt(2);
                    }

                } else if (prevY == y) {
                    time += Math.abs(prevX - x);
                    prevX = x;
                } else if (prevX == x) {
                    time += Math.abs(prevY - y);
                    prevY = y;
                }

            }

        }

        return time;
    }
    
    private int mySolution2(int[][] points) {
        // This solution requires the knowlege of chebyshev distance
        //MUCH MORE EFFICIENT

        int time = 0;
        int prevX = points[0][0];
        int prevY = points[0][1];

        for (int i = 1; i < points.length; i++) {

            int[] point = points[i];

            int x = point[0];
            int y = point[1];

            //chebyshev distance
            time += Math.max(Math.abs(prevX-x), Math.abs(prevY-y));

            prevX = x;
            prevY = y;

            
        }

        return time;
    }


    public static void main(String[] args) {
        int[][] points = {{1,1},{3,4},{-1,0}};

        MinimumTimeVisitingAllPoints o = new MinimumTimeVisitingAllPoints();

        System.out.println(o.minTimeToVisitAllPoints(points));
    }

}
