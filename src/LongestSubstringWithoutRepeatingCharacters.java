import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {


    private static int mySolution(String s){

        char[] cArr = s.toCharArray();

        HashSet<Character> hs = new HashSet<>();
        int maxLength = 0;
        
        int windowStart = 0;
        for (int i = 0; i < cArr.length; i++) {
            
            char c = cArr[i];

            while (hs.contains(c)) {
                //this is for the case dvdf, the longest str of unique char is 3 (vdf)
                //pop the elements from the start of the window until the substring we have is unique
                char windowStartChar = cArr[windowStart];
                windowStart++;

                hs.remove(windowStartChar);
            }


            
            if (!hs.contains(c)) {
                hs.add(c);
            }
            

            //update maxLength if its smaller
            if (maxLength < hs.size()) {
                maxLength = hs.size();
            }

        }

        return maxLength;
    }

    
    public static void main(String[] args) {
        // abcabcbb - output 3
        // bbbbb - output 1

        System.out.println(mySolution("abcbb"));
        System.out.println(mySolution("abcabcbb"));
        System.out.println(mySolution("bbbbb"));
        System.out.println(mySolution("dvdf")); //expected output 2

    }

}
