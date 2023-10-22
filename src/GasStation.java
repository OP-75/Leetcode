public class GasStation {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // return neetcodeSolution(gas, cost);
        return mySolution(gas, cost);
    }

    private int mySolution(int[] gas, int[] cost) {
        //TLE - O(n^2) time

        for (int i = 0; i < gas.length; i++) {

            int indx = canLoop(gas, cost, i);

            if (indx>=0) {
                return indx;
            }
            
        }

        return -1;


    }

   private int canLoop(int[] gas, int[] cost, int i) {
        
        int start = i==gas.length-1? 0 : i+1;
        int end = i;

        int gasInTank = gas[i] - cost[i];
        int indx = start;

        while (gasInTank>=0) {
            
            if (indx==end) {
                return i;
            }

            gasInTank += gas[indx] - cost[indx];

            indx = indx==gas.length-1? 0 : indx+1;

        }

        return -1;

    }

 private int neetcodeSolution(int[] gas, int[] cost) {
        //greedy - O(n) time

        int[] remainingGas = new int[gas.length];

        int totalGas = 0; 
        for (int i = 0; i < remainingGas.length; i++) {
            remainingGas[i] = gas[i] - cost[i];
            totalGas += remainingGas[i];
        }

        if (totalGas<0) {
            //we cant complete the circuit
            return -1;
        }

        int startIndx = -1, gasInTank = 0;
        for (int i = 0; i < remainingGas.length; i++) {
            gasInTank += remainingGas[i];
            
            if (startIndx==-1 && gasInTank>=0) {
                startIndx = i;
            }
            
            if (gasInTank<0) {
                //reset if gas in tank is -ve
                startIndx = -1;
                gasInTank = 0;
            } 
        }

        return startIndx;
    }

    public static void main(String[] args) {
        GasStation o = new GasStation();
        System.out.println(o.canCompleteCircuit(new int[] {2,3,4}, new int[] {3,4,3}));
    }
    
}
