public class CountofMatchesinTournament {

    public int numberOfMatches(int n) {
        return mySolution(n);
    }

    private int mySolution(int n) {

        if (n==1) {
            return 0;
        }

        if (n%2==0) {
            return (n/2)+mySolution(n/2);
        }
        else{
            return ((n-1)/2)+mySolution(((n-1)/2)+1);
        }
    }

    

}
