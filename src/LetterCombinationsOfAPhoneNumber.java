import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    
    private void backtrack(int[] digits, String[] mapping, int digitIndx, List<String> ansList, String tmpAnsCombination){
        
        if (digitIndx>=digits.length) {
            ansList.add(tmpAnsCombination);
            return;
        }

        int digit = digits[digitIndx];
        String mappedStr = mapping[digit];
        for (int i = 0; i < mappedStr.length(); i++) {
            char charToAppend = mappedStr.charAt(i);
            backtrack(digits, mapping, digitIndx+1, ansList, tmpAnsCombination+charToAppend);
        }
        
    }

    private void backtrackUsingSringBuilder(int[] digits, String[] mapping, int digitIndx, List<String> ansList, StringBuilder tmpAnsCombination){
        //String builder is MUCH MORE FASTER THAN CONCATINATING STRINGS
        if (digitIndx>=digits.length) {
            ansList.add(tmpAnsCombination.toString());
            return;
        }

        int digit = digits[digitIndx];
        String mappedStr = mapping[digit];
        for (int i = 0; i < mappedStr.length(); i++) {
            char charToAppend = mappedStr.charAt(i);
            backtrackUsingSringBuilder(digits, mapping, digitIndx+1, ansList, tmpAnsCombination.append(charToAppend));
            tmpAnsCombination.deleteCharAt(tmpAnsCombination.length()-1);
        }
        
    }
    
    public List<String> letterCombinations(String digits) {

        
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        char[] digitsArr = digits.toCharArray();
        int[] digitIntArr = new int[digits.length()];
        for (int i = 0; i < digitIntArr.length; i++) {
            digitIntArr[i] = digitsArr[i] - '0';
        }
        
        List<String> ans = new ArrayList<>();
        if(digits.isEmpty()){
            return ans;
        }
        // backtrack(digitIntArr, mapping, 0, ans, "");
        //String builder is MUCH MORE FASTER THAN CONCATINATING STRINGS
        backtrackUsingSringBuilder(digitIntArr, mapping, 0, ans, new StringBuilder());
        return ans;
    }


    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber o = new LetterCombinationsOfAPhoneNumber();

        System.out.println(o.letterCombinations("23"));
    }

}
