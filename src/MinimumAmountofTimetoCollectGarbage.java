import java.util.*;

public class MinimumAmountofTimetoCollectGarbage {

    public int garbageCollection(String[] garbage, int[] travel) {
        return mySolution(garbage, travel);
    }

    private int mySolution(String[] garbage, int[] travel) {
        
        HashMap<Character, Integer> lastIndxsOfEveryGarbage = lastIndxOfEveryGarbage(garbage);

        int time = 0;
        for (int i = 0; i < garbage.length; i++) {
            
            // time += countOf(garbage[i], 'M');
            // time += countOf(garbage[i], 'G');
            // time += countOf(garbage[i], 'P');
            //since we are getting count of every M G P in string and string only cointains M G P so just add the length to the time
            time += garbage[i].length();


            if (i<lastIndxsOfEveryGarbage.get('M')) {
                //if we are at indx less than last indx of 'M' garbage then only move this ie 'M' garbage truck forward, also if we are at the last indx then there is no need to go forward
                time += travel[i];
            }
            if (i<lastIndxsOfEveryGarbage.get('G')) {
                //if we are at indx less than last indx of 'G' garbage then only move this ie 'G' garbage truck forward, also if we are at the last indx then there is no need to go forward
                time += travel[i];
            }
            if (i<lastIndxsOfEveryGarbage.get('P')) {
                //if we are at indx less than last indx of 'P' garbage then only move this ie 'P' garbage truck forward, also if we are at the last indx then there is no need to go forward
                time += travel[i];
            }

        }

        return time;

    }

    private HashMap<Character, Integer> lastIndxOfEveryGarbage(String[] garbage){

        HashMap<Character, Integer> map = new HashMap<>();

        map.put('M', lastIndexOf(garbage, "M"));
        map.put('G', lastIndexOf(garbage, "G"));
        map.put('P', lastIndexOf(garbage, "P"));

        return map;
    }

    private int lastIndexOf(String[] garbage, String garbageType){
        int indx = -1;
        for (int i = 0; i < garbage.length; i++) { 
            if (garbage[i].contains(garbageType)) {
                indx = i;
            }
        }

        return indx;
    }


    private int countOf(String s, char c){
        char[] sarr = s.toCharArray();
        int count = 0;
        for (char sChar : sarr) {
            if (sChar==c) {
                count++;
            }
        }
        return count;
    }

}
