
public class PalindromicSubstrings {
    private int mySolution(String s) {

        char[] arr = s.toCharArray();

        int count=0;

        for (int i = 0; i < arr.length; i++) {

            int left = i, right = i;
            
            // odd length plindrome
            while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
                count++;
                left--;
                right++;
            }
            

            //even length palindrome (Hard part)
            left = i; 
            right = i+1;
            
            if (left >= 0 && right < arr.length && arr[left] == arr[right]) {
                count++;

                left--;
                right++;
            }
            while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
                count++;

                left--;
                right++;
            }

        }

        return count;

    }

    public int countSubstrings(String s) {
        return mySolution(s);
    }

}



    


