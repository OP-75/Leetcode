import java.util.HashMap;
import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        return mySolution(s, wordDict);
    }

    HashMap<String, Boolean> memo;
    private boolean mySolution(String s, List<String> wordDict) {


        if (s.isEmpty()) {
            return true;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        for (String word : wordDict) {
            
            if (s.startsWith(word) && mySolution(s.substring(word.length()), wordDict)) {
                return true;
            }

        }


        memo.put(s, false);
        return false;
    }
}
