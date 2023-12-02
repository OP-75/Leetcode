import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindWordsThatCanBeFormedByCharacters {
    public int countCharacters(String[] words, String chars) {
        return mySolution(words, chars.toCharArray());
    }

    private int mySolution(String[] words, char[] chars) {
       
        ArrayList<HashMap<Character, Integer>> wordsMapList = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            
            HashMap<Character, Integer> charFreqMap = new HashMap<>();
            char[] currWord = words[i].toCharArray();
            
            for (int j = 0; j < currWord.length; j++) {
                
                int count = charFreqMap.getOrDefault(currWord[j], 0);
                charFreqMap.put(currWord[j], count+1);
                
            }
            
            wordsMapList.add(charFreqMap);
            
        }
        
        //make map of chars[]
        HashMap<Character, Integer> givenCharsFreqMap = new HashMap<>();
        for (int j = 0; j < chars.length; j++) {
                
            int count = givenCharsFreqMap.getOrDefault(chars[j], 0);
            givenCharsFreqMap.put(chars[j], count+1);
                
        }


        int ansLen = 0;
        for (int i = 0; i < words.length; i++) {
            
            HashMap<Character, Integer> currWordMap = wordsMapList.get(i);
            boolean canFormWord = true;

            for (Map.Entry<Character, Integer> en : currWordMap.entrySet()) {
                
                if (givenCharsFreqMap.containsKey(en.getKey()) && givenCharsFreqMap.get(en.getKey()) >= en.getValue()) {
                    continue;
                }
                else{
                    canFormWord = false;
                    break;
                }

            }

            if (canFormWord) {
                // System.out.println(words[i]);
                ansLen += words[i].length();
            }


        }



       
        return ansLen;
    }



    public static void main(String[] args) {
        String[] words = {"cat","bt","hat","tree"};
        String chars = "atach";

        FindWordsThatCanBeFormedByCharacters o  =new FindWordsThatCanBeFormedByCharacters();
        System.out.println(o.countCharacters(words, chars));
    }
}
