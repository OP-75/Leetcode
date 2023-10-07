public class LongestPalindromicSubstring {

    private String mySolution(String s) {

        char[] arr = s.toCharArray();

        String ans = "";

        for (int i = 0; i < arr.length; i++) {

            int left = i, right = i;
            
            // odd length plindrome
            while (left >= 0 && right < arr.length && arr[left] == arr[right]) {

                if ((right- left + 1)>ans.length()) {
                    ans = s.substring(left, right+1);
                }
                left--;
                right++;
            }

            //even length palindrome (Hard part)
            left = i; 
            right = i+1;
            while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
                if ((right- left + 1)>ans.length()) {
                    ans = s.substring(left, right+1);
                }
                left--;
                right++;
            }

        }

        return ans;

    }

    public String longestPalindrome(String s) {
        return mySolution(s);
    }

}
