import java.util.HashSet;

public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {

        HashSet<String> numsSet = new HashSet<>();
        for (String s : nums) {
            numsSet.add(s);
        }

        isStringFound("", 0, nums[0].length(), numsSet);

        return ans;
    }

    String ans = "";
    private boolean isStringFound(String string, int i, int n, HashSet<String> numsSet) {

        if(i==n && !numsSet.contains(string)){
            //this is give ie nums.length == n == nums[i].length

            ans = string;
            return true; //if found return true
        }
        else if (i>=n) {
            return false;            
        }

        if (isStringFound(string.concat("0"), i+1, n, numsSet)) {
            return true;
        }
        else if (isStringFound(string.concat("1"), i+1, n, numsSet)) {
            return true;
        }
        

        return false;
    }

    public static void main(String[] args) {
        FindUniqueBinaryString o = new FindUniqueBinaryString();
        System.out.println(o.findDifferentBinaryString(new String[]{"111","011","001"}));
        System.out.println(o.findDifferentBinaryString(new String[]{"01","10"}));
        System.out.println(o.findDifferentBinaryString(new String[]{"00","01"}));
    }

    
}
