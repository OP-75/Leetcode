

/*
 * One and only thing you need to know to make this problem Easy is that homogenous string "aaaaaaa" 
 * of length N contains N * (N + 1) / 2 homogenous substrings (sum of the arithmetic progression).
 * 
 * Then you just sum them up not forgetting about modulo.
 * 
 * Also the ans is very big so use `long` for intermidiate math operations
 * 
*/

public class CountNumberofHomogenousSubstrings {

    public int countHomogenous(String s) {
        return mySolution(s);
    }

    private int mySolution(String s) {
        
        char[] sarr = s.toCharArray();

        long homoStrSize = 0;
        long homoCount = 0;

        

        for (int i = 0; i < sarr.length; i++) {
            
            if (i==0 || sarr[i]==sarr[i-1]) {
                homoStrSize++;
            }

            if (i!=0 && sarr[i]!=sarr[i-1]) {
                long tmpHomoCount = homoStrSize*(homoStrSize+1)/2;
                homoCount += tmpHomoCount%((long)Math.pow(10,9)+7);
                homoStrSize = 1;
            }

        }

        //do this once again for final count ie eg str = "aaaa" so the 2nd if wont be activated
        long tmpHomoCount = homoStrSize*(homoStrSize+1)/2;
        homoCount += tmpHomoCount%((long)Math.pow(10,9)+7);

        return (int)homoCount;
    }

    public static void main(String[] args) {
        CountNumberofHomogenousSubstrings o = new CountNumberofHomogenousSubstrings();

        System.out.println(o.countHomogenous("aaaa"));
    }

}
