public class N_thTribonacciNumber {
    
}


class Solution {
    public int tribonacci(int n) {
        
        int tri[] = new int[3];
        tri[0] = 0;
        tri[1] = 1;
        tri[2] = 1;

        if(n<3){
            return tri[n];
        }

        int tn = -1;
        for (int i = 3; i <= n; i++) {
            
            tn = tri[0]+tri[1]+tri[2];
            tri[0] = tri[1];
            tri[1] = tri[2];
            tri[2] = tn;
        }

        return tn;
        
    }
}