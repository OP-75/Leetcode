import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfOperationsToMakeArrayEmpty {

    public int minOperations(int[] nums) {

        int operationsCount = 0;

        HashMap<Integer, Integer> numFreqMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int count = numFreqMap.getOrDefault(nums[i], 0);
            numFreqMap.put(nums[i], count + 1);
        }

        System.out.println(numFreqMap.toString());

        for (Map.Entry<Integer, Integer> en : numFreqMap.entrySet()) {

            // int key = en.getKey();
            int freq = en.getValue();

            int opsCountForCurrKey = getOperationCount(freq);
            if (opsCountForCurrKey==-1) {
                return -1;
            }
            operationsCount += opsCountForCurrKey;

        }

        return operationsCount;

    }

    private int getOperationCount(int freq) {

        if (freq % 3 == 0) {
            return freq / 3;
        }

        int opsCount = 0;

        while (freq > 0) {

            if (freq == 1) {
                return -1;
            } else if (freq % 3 == 0) {
                //if at any point our freq is dividible by 3 then just increase the ops count by freq/3 & make the freq 0 (since all elements have been removed)
                opsCount += freq / 3;
                freq = 0;
            }
            else{
                //if freq is > 0 && > 3 (1st and 2nd "if" condition is not activated) so it must be either a number divisible by 2 or a numnot divisible by both 2 and 3 
                //in this cases just perform a single operation (Choose two elements with equal values and delete them from the array --- qs condition) & decrement the freq by 2
                opsCount++;
                freq -= 2;
            }
        }

        return opsCount;
    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMakeArrayEmpty o = new MinimumNumberOfOperationsToMakeArrayEmpty();
        int[] arr = { 14, 12, 14, 14, 12, 14, 14, 12, 12, 12, 12, 14, 14, 12, 14, 14, 14, 12, 12 };
        System.out.println(o.minOperations(arr));

    }

}
