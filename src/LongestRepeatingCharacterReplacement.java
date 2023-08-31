import java.util.*;

//this is not tested yet, this code is incomplete

public class LongestRepeatingCharacterReplacement {
    
    
    private static void insertInHM(Map<Character, Integer> hm, char c){

        if (!hm.containsKey(c)) {
            hm.put(c, 1);
        }
        else {
            hm.put(c, hm.get(c)+1);
        }
    }
    private static void removeFromHM(Map<Character, Integer> hm, char c){
        int freq = hm.get(c);
        if (freq==1) {
            hm.remove(c);
        }
        else {
            hm.put(c, freq-1);
        }
    }
    private static char getEleWithMostFreq(Map<Character, Integer> hm){

        char ans = '_';
        // int maxFreq = Collections.max(hm.values());
        int maxFreq = 0;
        for(Character c: hm.keySet()){
            if (maxFreq<hm.get(c)) {
                maxFreq = hm.get(c);
                ans = c;
            }
        }

        return ans;
    }
    
    
    private static int mySolution(String s, int k) {
        

        char[] cArr = s.toCharArray();

        Map<Character, Integer> charFreq = new HashMap<>();

        int windowStart=0;
        insertInHM(charFreq, cArr[0]);
        int longestSeq = 1;
        int numOfChangesAllowed = k;

        char majorityChar = cArr[0];
        for (int i = 1; i < cArr.length; i++) {
            
            char c = cArr[i];
            if (majorityChar==c) {
                insertInHM(charFreq, c);
            }
            else if (c!=majorityChar && numOfChangesAllowed>0){
                insertInHM(charFreq, c);
                numOfChangesAllowed--;
            }
            else if(c!=majorityChar && numOfChangesAllowed==0){

                while(cArr[windowStart]==majorityChar){
                    windowStart++;
                    removeFromHM(charFreq,cArr[windowStart]);
                }

                if (charFreq.isEmpty()) {
                    insertInHM(charFreq, c);
                    numOfChangesAllowed = k;
                    majorityChar = c;
                    continue;
                }

                majorityChar = getEleWithMostFreq(charFreq);
                freqOfMajority

                


                
            }


        }




        return longestSeq;
    }
    
    public static void main(String[] args) {
        
    }
}
