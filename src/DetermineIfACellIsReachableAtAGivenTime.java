public class DetermineIfACellIsReachableAtAGivenTime {

    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        return mySolution(sx, sy, fx, fy, t);
    }

    private boolean mySolution(int sx, int sy, int fx, int fy, int t) {

        if (fx==sx && fy==sy && (t == 0 || t>=2)) {
            return true;
        }
        if (fx==sx && fy==sy && t==1) {
            return false;
        }


        int chebyshevDistance = Math.max(Math.abs(fx - sx), Math.abs(fy - sy));

        if (chebyshevDistance <= t) {
            return true;
        } else {
            return false;
        }

    }

}
