public class ReverseInteger {

    private static int reverse(int x) {

        String str = Integer.toString(x);
        boolean isNegative = x<0;

        // System.out.println(str);
        //for checking if each individual digits are less than max value
        String maxVal = Integer.toString(Integer.MAX_VALUE);
        String minVal = Integer.toString(Integer.MIN_VALUE).substring(1);

        StringBuilder str2 = new StringBuilder(str);
        if (isNegative) {
            String tmp =  str2.substring(1);
            str2 = new StringBuilder(tmp);
        }

        str2 = str2.reverse();

        String ans = str2.toString();

        if (ans.length()<minVal.length()) { //both minVal and maxVal have the same length since I have removed the "-" from min val
            if (isNegative) {
                ans = "-"+ans;
            }

            return Integer.parseInt(ans);
        }

        if (ans.length()>minVal.length()) { //sanity check tho this situation will never arise
            return 0;
        }

        
        //now this only leaves the case where ans.len == min or maxVal.len
        if (isNegative) {
            for (int i = 0; i < minVal.length(); i++) {
                if (ans.charAt(i)>minVal.charAt(i)) { //if the ascii val/number at any place is bigger that means its out of range of 32bit int
                    return 0;
                }
                if(ans.charAt(i)<minVal.charAt(i)){ //this is for the case if lets say 100s place is less than max values 100's place (ie 6) but the 10s place of ans is greater han 10s place of MAX_VALUE (ie 4) the the ans will return 0 but in reality since the 100's place of our ans is less than MAX_VALUE's 100's place obviously all the places after it (in our ans) (10s place and unit's place) value doesnt matter since the absolute value has become less than MAX_VALUE
                    break;
                }
            }
        } else {
            for (int i = 0; i < maxVal.length(); i++) {
                if (ans.charAt(i)>maxVal.charAt(i)) { //if the ascii val/number at any place is bigger that means its out of range of 32bit int
                    return 0;
                }
                if(ans.charAt(i)<minVal.charAt(i)){ //this is for the case if lets say 100s place is less than max values 100's place (ie 6) but the 10s place of ans is greater han 10s place of MAX_VALUE (ie 4) the the ans will return 0 but in reality since the 100's place of our ans is less than MAX_VALUE's 100's place obviously all the places after it (in our ans) (10s place and unit's place) value doesnt matter since the absolute value has become less than MAX_VALUE
                    break;
                }
            }
        }

        //since we have passed the above test where ans.len==maxVal.len without returning anything so our ans is within range of 32bit int
        if (isNegative) {
                ans = "-"+ans;
        }
        return Integer.parseInt(ans);

    }

    public static void main(String[] args) {
        System.out.println(reverse(-200));
        System.out.println(reverse(-123));
        System.out.println(reverse(Integer.MAX_VALUE));
        System.out.println(reverse(Integer.MIN_VALUE));
    }
}
