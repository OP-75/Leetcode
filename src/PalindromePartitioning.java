import java.util.*;

public class PalindromePartitioning {

    private boolean isPalindrome(String s) {
        
        if (s.length()==1) {
            return true;
        }
        char[] sarr = s.toCharArray();

        int startPtr = 0;
        int endPtr = s.length() - 1;
        while (startPtr < endPtr) {
            if (sarr[startPtr] == sarr[endPtr]) {
                startPtr++;
                endPtr--;
            } else {
                return false;
            }
        }

        return true;

    }

    private void myBacktrack(String s, List<String> tmp, HashSet<List<String>> previoslyAddedList,
            List<List<String>> ansList) {

        if (s.isEmpty()) {
            List<String> tmpSorted = new ArrayList<>(tmp);
            Collections.sort(tmpSorted);
            if (!previoslyAddedList.contains(tmpSorted)) {
                previoslyAddedList.add(tmpSorted);
                ansList.add(new ArrayList<>(tmp));   
            }

            return;
        }

        if (s.length() <= 1) {
            tmp.add(s);
            List<String> tmpSorted = new ArrayList<>(tmp);
            Collections.sort(tmpSorted);
            if (!previoslyAddedList.contains(tmpSorted)) {
                previoslyAddedList.add(tmpSorted);
                ansList.add(new ArrayList<>(tmp));
            }
            tmp.remove(tmp.size() - 1);
            return;
        }

        for (int i = 0; i < s.length(); i++) {

            String s1 = s.substring(0, i);
            String s2 = s.substring(i);

            // System.out.println("s1 & s2 = "+s1+" "+s2);

            if (isPalindrome(s1) && !s1.isEmpty()) {
                tmp.add(s1);
                myBacktrack(s2, tmp, previoslyAddedList, ansList);
                tmp.remove(tmp.size() - 1);
            }

            if (isPalindrome(s2) && !s2.isEmpty()) {
                tmp.add(s2);
                myBacktrack(s1, tmp, previoslyAddedList, ansList);
                tmp.remove(tmp.size() - 1);
            }

        }

    }
    private void neetcodeBacktrack(String s, int start, List<String> tmp, List<List<String>> ansList) {

        if (start >= s.length()) {
            ansList.add(new ArrayList<>(tmp));
        }

        for (int i = start; i < s.length(); i++) {

            String curr_substring = s.substring(start, i+1);
            
            if (isPalindrome(curr_substring)) {
                tmp.add(curr_substring);
                neetcodeBacktrack(s, i+1, tmp, ansList);
                tmp.remove(tmp.size() - 1);
            }

        }

    }

    public List<List<String>> partition(String s) {

        //my backtracking solution works fine but unfortunely the test cases are such that the output needs to be in particular order
        // List<List<String>> ans = new ArrayList<>();
        // myBacktrack(s, new ArrayList<>(), new HashSet<>(), ans);
        // return ans;


        List<List<String>> ans = new ArrayList<>();
        neetcodeBacktrack(s, 0, new ArrayList<>(), ans);
        return ans;



    }

    public static void main(String[] args) {
        PalindromePartitioning o = new PalindromePartitioning();
        List<List<String>> ans = o.partition("aabb");
        System.out.println(ans);

        ans = o.partition("efe");
        System.out.println(ans);

        ans = o.partition("fff");
        System.out.println(ans);
    }
}
