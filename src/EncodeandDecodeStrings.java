//this question is on lintcode.com for free (question num 659) https://www.lintcode.com/problem/659/
// https://www.lintcode.com/problem/659/?source=激励弹窗分享

import java.util.ArrayList;
import java.util.List;

public class EncodeandDecodeStrings {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // write your code here
        String ans = "";
        for (int i = 0; i < strs.size(); i++) {
            String tmp = strs.get(i);

            if (tmp==":"){
                tmp += ":";
            }
            if (tmp==";"){
                tmp += ";";
            }

            ans +=tmp;
            if (i!=strs.size()-1) {
                ans += ":;";
            }
            
        }

        return ans;
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        String[] ans = str.split(":;");
        for (int i = 0; i < ans.length; i++) {
            if(ans[i].contains("::") || ans[i].contains(";;")){
                ans[i] = ans[i].replace("::", ":");
                ans[i] = ans[i].replace(";;", ";");
            }
        }

        List<String> ansList = new ArrayList<>();
        for (String string : ans) {
            ansList.add(string);
        }

        return ansList;
     }
}