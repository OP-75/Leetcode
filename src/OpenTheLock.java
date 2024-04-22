import java.lang.reflect.Array;
import java.util.*;

class Solution {

    //Pretty amazing question and solution
    
    public int openLock(String[] deadends, String target) {

        if (target.equals("0000")) {
            //special case start point = endpoint
            return 0; 
        }

       

        HashSet<String> deadendsSet = new HashSet<>();
        for (String de : deadends) {
            deadendsSet.add(de);
        }

        if (deadendsSet.contains("0000")) {
            // no way to change anything
            return -1;
        }


        // this question is pretty hard

        HashSet<String> previouslyVisted = new HashSet<>();
        String currString = "0000";
        previouslyVisted.add(currString);

        // BFS
        Queue<String> q = new LinkedList<>();
        q.add(currString);

        int opsCount = 0;
        while (!q.isEmpty()) {
            int qLen = q.size();
            opsCount++; // since this is BFS we are doing 1 operation (increment 1 char by 1 or
                        // decrement 1 char by 1)

            for (int i = 0; i < qLen; i++) {
                currString = q.poll();

                ArrayList<String> nextCombs = getNextCombinations(currString, previouslyVisted, deadendsSet);
                previouslyVisted.addAll(nextCombs);
                q.addAll(nextCombs);

                // now check if we got the target in `previouslyVisted`
                if (previouslyVisted.contains(target)) {
                    
                    // System.out.println(previouslyVisted);
                    return opsCount;
                }
            }

        }

        
        return -1;

    }

    long totalTime = 0;

    private ArrayList<String> getNextCombinations(String currStr, HashSet<String> previouslyVisited,
            HashSet<String> deadendsSet) {

        ArrayList<String> nextCombs = new ArrayList<>();

        for (int charToChange = 0; charToChange < 4; charToChange++) {

            
            String newComb = incrementDial(currStr, charToChange);

            if (previouslyVisited.contains(newComb) || deadendsSet.contains(newComb)) {
                continue;
            } else {
                nextCombs.add(newComb);
            }

        }
        for (int charToChange = 0; charToChange < 4; charToChange++) {

            
            String newComb = decrementDial(currStr, charToChange);

            if (previouslyVisited.contains(newComb) || deadendsSet.contains(newComb)) {
                continue;
            } else {
                nextCombs.add(newComb);
            }

        }

        return nextCombs;

    }

    private String incrementDial(String num, int charIndxToIncrement) {

        StringBuilder numBuilder = new StringBuilder(num);
        char c = numBuilder.charAt(charIndxToIncrement); 
        if (c=='9') {
            c = '0';
        }
        else{
            c++;
        }
        numBuilder.setCharAt(charIndxToIncrement, c);

        return numBuilder.toString();

    }

    private String decrementDial(String num, int charIndxToDecrement) {

        StringBuilder numBuilder = new StringBuilder(num);
        char c = numBuilder.charAt(charIndxToDecrement); 
        if (c=='0') {
            c = '9';
        }
        else{
            c--;
        }
        
        numBuilder.setCharAt(charIndxToDecrement, c);

        return numBuilder.toString();

    }
}
