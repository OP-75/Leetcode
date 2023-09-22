import java.util.Comparator;
import java.util.PriorityQueue;

class Point{
    int[] pointsArr; //pointsArr[0] = x & pointsArr[1] = y
    Point(int x, int y){
        pointsArr = new int[2];
        pointsArr[0] = x;
        pointsArr[1] = y;
    }
}


class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        double p1DistTo0 = Math.sqrt( (p1.pointsArr[0]*p1.pointsArr[0]) +  (p1.pointsArr[1]*p1.pointsArr[1]) ); // dist = sqrt(x^2 + y^2)
        double p2DistTo0 = Math.sqrt( (p2.pointsArr[0]*p2.pointsArr[0]) +  (p2.pointsArr[1]*p2.pointsArr[1]) ); // dist = sqrt(x^2 + y^2)

        return Double.compare(p1DistTo0, p2DistTo0);
    }

    
}

public class KClosestPointstoOrigin {
    
    
    private int[][] mySolution(int[][] points, int k) {

        PriorityQueue<Point> pq = new PriorityQueue<>(new PointComparator());

        for (int[] point : points) {
            pq.add(new Point(point[0], point[1]));
        }

        int[][] closestKPts = new int[k][2];
        while (k>0) {
            closestKPts[k-1] = pq.poll().pointsArr;
            k--;
        }

        return closestKPts;
    }
    public int[][] kClosest(int[][] points, int k) {
        return mySolution(points, k);
    }
}



