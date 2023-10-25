import java.util.*;

public class PartitionLabels {
    
    public List<Integer> partitionLabels(String s) {
        return mySolution(s);
    }
 

    private List<Integer> mySolution(String s) {

        char[] sArr = s.toCharArray();
        HashMap<Character, Integer> charLastIndxMap = new HashMap<>();
        for (int i = 0; i < sArr.length; i++) {
            charLastIndxMap.put(sArr[i], i); //this continously overwrites the indx so we get last indx
        }

        List<Integer> substringLengths = new ArrayList<>();

        int i = 0;
        
        while (i<sArr.length) {
            
            int lastIndx = charLastIndxMap.get(sArr[i]);

            for (int j = i; j <= lastIndx; j++) {
                lastIndx = Math.max(lastIndx, charLastIndxMap.get(sArr[j]));
            }

            //now we have gotten the last indx of unique charater in which all chars between i & lastIndx only occur in this substring

            //lastindx+1 caz endIndex is excluded
            substringLengths.add(s.substring(i, lastIndx+1).length()); 
            System.out.println(s.substring(i, lastIndx+1));

            i = lastIndx+1;
        }

        return substringLengths;
    }   

}
