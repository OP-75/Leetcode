import java.util.ArrayList;

public class Largest3SameDigitNumberinString {
    public String largestGoodInteger(String num) {

        // return mySolution(num.toCharArray());
        return mySolution2(num.toCharArray());

    }

    private String mySolution(char[] charArray) {

        ArrayList<Integer> goodNums = new ArrayList<>();

        int maxGoodNum = Integer.MIN_VALUE;
        String maxGoodNumStr = "";

        for (int start = 0; start <= charArray.length - 3; start++) {

            if (charArray[start] == charArray[start + 1] && charArray[start + 1] == charArray[start + 2]) {
                // if all 3 chars are equal then do this

                int pow = 0;
                int tmpNum = 0;
                String tmpNumStr = "";
                for (int i = start; i < start + 3; i++, pow++) {
                    tmpNum += (charArray[i] - '0') * Math.pow(10, pow);
                    tmpNumStr += charArray[i];
                }

                if (maxGoodNum<tmpNum) {
                    maxGoodNum = tmpNum;
                    maxGoodNumStr = tmpNumStr;
                }
            }

        }

        return maxGoodNumStr;
    }

    private String mySolution2(char[] charArray) {

        ArrayList<Integer> goodNums = new ArrayList<>();

        int maxGoodNum = Integer.MIN_VALUE;


        for (int start = 0; start <= charArray.length - 3; start++) {

            if (charArray[start] == charArray[start + 1] && charArray[start + 1] == charArray[start + 2]) {
                // if all 3 chars are equal then do this
                
                if (maxGoodNum<charArray[start]-'0') {
                    maxGoodNum = charArray[start]-'0';
                }
            }
            
        }
        
        String maxGoodNumStr = "";
        if (maxGoodNum!=Integer.MIN_VALUE) {
            maxGoodNumStr = Integer.toString(maxGoodNum) + Integer.toString(maxGoodNum) + Integer.toString(maxGoodNum);
        }

        return maxGoodNumStr;
    }
}
