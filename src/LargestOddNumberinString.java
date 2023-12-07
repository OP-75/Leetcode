public class LargestOddNumberinString {

    public String largestOddNumber(String num) {
        return mySolution(num.toCharArray(), num);
    }

    private String mySolution(char[] num, String numString) {

    
        for (int i = num.length-1; i >=0 ; i--) {
            if ((num[i]-'0')%2!=0) {
                //ie digit is odd
                return numString.substring(0, i+1);
            }
        }

        return "";
    }

}
