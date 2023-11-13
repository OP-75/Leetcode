import java.util.*;

public class SortVowelsInAString {

    public String sortVowels(String s) {
        return mySolution(s);
    }

    private String mySolution(String s) {
        
        char[] sarr = s.toCharArray();

        char[] vowelsArr = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        HashSet<Character> vowelsSet = new HashSet<>();
        for (char v : vowelsArr) {
            vowelsSet.add(v);
        }

        vowelsArr = null; //no longer needed so explicitly null it


        // Use min heap to get the vowels in sorted order 
        PriorityQueue<Character> vowelsMinHeap = new PriorityQueue<>(); 

        for (int i = 0; i < sarr.length; i++) {
            if (vowelsSet.contains(sarr[i])) {
                vowelsMinHeap.add(sarr[i]);
            }
        }
        
        
        
        //now rearrange/ressign the vowels in sarr,, sarr[i] = pop from min heap (onnly where sarr[i] is a vowel)
        for (int i = 0; i < sarr.length; i++) {
            if (vowelsSet.contains(sarr[i])) {
                //if sarr[i] is vowel then pop the vowel from minHeap (which is now sorted) and place it at sarr[i] which was unsored previosly
                sarr[i] = vowelsMinHeap.poll();
            }
        }
        
        
        return new String(sarr); //IMPORTANT: use this constructor instead of concatinating chars to empty string this is MUCH faster

    }

}
