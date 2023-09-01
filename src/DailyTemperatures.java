
//this reqires the concept of monotonic Stack

import java.util.*;

class Element{

    int val;
    int indx;

    Element(int val, int indx){
        this.val = val;
        this.indx = indx;
    }

}

class Solution {
    
    Stack<Element> monotonicStack = new Stack<>();

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];

        for (int i = 0; i < ans.length; i++) {
            
            int currTemp = temperatures[i];

            while (!monotonicStack.isEmpty() && monotonicStack.peek().val<currTemp) {
                Element poppedEle = monotonicStack.pop();
                ans[poppedEle.indx] = i - poppedEle.indx;
            }
           
            monotonicStack.push(new Element(currTemp, i));
                
        
        }



        //pop all the other elements since we havent found a ele greater than these on the right/after these
        while (!monotonicStack.isEmpty()) {
            Element poppedEle = monotonicStack.pop();
            ans[poppedEle.indx] = 0;
        }

        return ans;
    }
}

public class DailyTemperatures{

    public static void main(String[] args) {
        Solution o = new Solution();

        int[] temps;

        temps = new int[]{47,47,46,76,100,70};
        System.out.println(Arrays.toString(o.dailyTemperatures(temps)));


    }

}