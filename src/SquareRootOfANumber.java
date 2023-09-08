
// Qs link: https://www.codingninjas.com/studio/problems/square-root-integral_893351


public class SquareRootOfANumber {

    private static int sqrtN(long N) {
		
		long up = N, down = 0, mid=-1;
		while (up>=down) {
			mid = (up+down)/2;

			long mid_sq = mid*mid;
			long midPlus1_sq = (mid+1)*(mid+1);
			long midMinus1_sq = (mid-1)*(mid-1);

			if (mid_sq<=N && midPlus1_sq>N) {
				return (int)mid;
			}
			else if(mid_sq>N && midMinus1_sq<=N){
				return (int)mid-1;
			}
			else if(mid_sq<N){
				down = mid + 1;
			}
			else if (mid_sq>N) {
				up = mid - 1;
			}
		}


		return Integer.MIN_VALUE;

	}

    public static void main(String[] args) {
        
        System.out.println(sqrtN(100));
        System.out.println(sqrtN(81));
        System.out.println(sqrtN(80));
    }
}
