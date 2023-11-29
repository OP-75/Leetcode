public class Numberof1Bits {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        int count = 0;
        for (int i = 0; i < 32; i++) {
            
            if ((n & 1)==1) {
                //bitwise AND
                count++;
            }

            n = n >> 1; //right shift by 1 bit

        }

        return count;

    }

}
