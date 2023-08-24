import java.util.*;

public class GroupAnagrams {

    private static void insert(HashMap<Character, Integer> hm, char c){

        if (hm.containsKey(c)) {
            hm.put(c, hm.get(c)+1);
        } else {
            hm.put(c, 1);
        }

    }


    private static List<List<String>> mySolution(String[] strs) {
        
        HashMap<Character, Integer> hmArr[] = new HashMap[strs.length];

        for (int i = 0; i < strs.length; i++) {
            char[] sArr = strs[i].toCharArray();
            hmArr[i] = new HashMap<Character, Integer>();
            for (int j = 0; j < sArr.length; j++) {
                insert(hmArr[i], sArr[j]);
            }        
        }

        //make bool arr to keep track of the numbers inseted in list
        boolean[] isInserted = new boolean[strs.length];
        Arrays.fill(isInserted, false);

        List<List<String>> ans = new ArrayList<List<String>>();

        for (int i = 0; i < hmArr.length; i++) {

            if(isInserted[i]) continue; 

            List<String> tmp = new ArrayList<String>();
            tmp.add(strs[i]);
            isInserted[i] = true;

            for (int j = i+1; j < hmArr.length; j++) {
                if (hmArr[i].equals(hmArr[j]) && i!=j && !isInserted[j]) {
                    tmp.add(strs[j]);
                    isInserted[j] = true;
                }
            }
            ans.add(tmp);
        }



        return ans;
    }

    private static List<List<String>> neetcodeSolution(String[] strs) {
        //this is actually worse than my solution in case of runtime
        
        HashMap<HashMap<Character, Integer>, List<String>> hm = new HashMap<>();

        for (String string : strs) {
            char[] cArr = string.toCharArray();
            HashMap<Character, Integer> tmpMap = new HashMap<>();
            for (char c : cArr) {
                insert(tmpMap, c);
            }
            
            boolean hasMap = false;
            for (HashMap<Character, Integer> currMap : hm.keySet()) {
                if (currMap.equals(tmpMap)) {
                    hm.get(currMap).add(string);
                    hasMap = true;
                    break;
                }
            }
            if (!hasMap) {
                List<String> al = new ArrayList<>();
                al.add(string);
                hm.put(tmpMap, al);
            }
        }



        //put all lists into 1 list
        List<List<String>> ans = new ArrayList<>();
        for (List<String>  currList : hm.values()) {
            ans.add(currList);
        }

        
        return ans;
    }



    public static void main(String[] args) {
        // List<List<String>> ans = mySolution(new String[]{"eat","tea","tan","ate","nat","bat"});
        List<List<String>> ans = mySolution(new String[]{"ddddddddddg","dgggggggggg"});
        for (List<String> list : ans) {
            System.out.print(list.toString()+", ");
        }


        //this (neetcode solution) is actually worse than my solution in case of runtime
        // List<List<String>> ans = mySolution(new String[]{"eat","tea","tan","ate","nat","bat"});
        List<List<String>> ans2 = neetcodeSolution(new String[]{"ddddddddddg","dgggggggggg"});
        for (List<String> list : ans2) {
            System.out.print(list.toString()+", ");
        }
    }
}
