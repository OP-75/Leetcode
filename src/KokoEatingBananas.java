public class KokoEatingBananas {

    private static long timeTakenToEat(int[] piles, int k){
        long ans = 0;

        for (int i : piles) {
            ans = ans + (long)Math.ceil((double)i/(double)k);
            // System.out.println("ans = "+ans);
        }

        return ans;
    }

    private static int mySolution(int[] piles, int h) {

        int kstart = 1, kend = Integer.MIN_VALUE;
        int kmid = -1;
        //get max val from pile that will be the kend
        for (int i : piles) {
            kend = Math.max(i, kend);
        }

        int k = Integer.MAX_VALUE;
        while(kend>=kstart){
            kmid = (kstart+kend)/2;

            long timeTaken = timeTakenToEat(piles, kmid);

            if(timeTaken<=h){ //ie there might be still time remaining so reduce k and see if we can get a minimum k
                k = Math.min(k, kmid);
                kend = kmid -1;
            }
            else if(timeTaken>h){ //the time has ran out so increase k
                kstart = kmid +1;
            }

        }


        
        return k;
    }


    public static int minEatingSpeed(int[] piles, int h) {
        return mySolution(piles, h);
    }

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
    }
}
