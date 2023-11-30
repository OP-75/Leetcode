public class MinimumOneBitOperationstoMakeIntegersZero {
    // https://youtu.be/yRI18_MaG7k?si=C4OhjphyJWKID0QQ
    public int minimumOneBitOperations(int n) {
        return neetcodeSolution(n);
    }

    private int neetcodeSolution(int n) {
        if (n==0) {
            return 0;
        }

        int k = 0;
        while (Math.pow(2, k)<=n) {
            k++;
        }

        k--; //caz we incremented k too far in above loop
        return (int) (Math.pow(2, k+1) - 1 - neetcodeSolution((int)Math.pow(2, k)^n));
    }
}
