import java.util.*;
import java.lang.*;


//IMPORTANT!!!! While comparing 2 strings dont use "==" use str1.equals(str2) functions, since "==" compares the 2 string addresses which can or cannot be the same!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


class Solution {

    int solve(int num1, int num2, String operator){

        if(operator.equals("*")) return num1*num2;
        else if(operator.equals("/")) return num1/num2;
        else if(operator.equals("-")) return num1-num2;
        else if(operator.equals("+")) return num1+num2;
        else  return 0;
    }

    boolean isOperator(String operator){

        // if (operator == "*" || operator == "/" || operator == "-" || operator == "+")
        //     return true;
        if(operator.equals("*") || operator.equals("/") || operator.equals("-") || operator.equals("+"))
        return true;

        else
            return false;
    }





    private int mySolution(String[] tokens){

        Stack<Integer> st = new Stack<>();
        
    
        for(int i=0; i<tokens.length; i++){

            String token = tokens[i];

            if(isOperator(token)==true){
                System.out.println("Inside if");
                int num2 = st.pop();
                int num1 = st.pop();
                int result = solve(num1, num2, token);
                System.out.println("res = "+result);

                st.push(result);
            }
            else{
                int asNum = Integer.MIN_VALUE;
                try{
                asNum = Integer.parseInt(token);
                st.push(asNum);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }

        }

        int finalResult = st.pop();
        return finalResult;

    }

    public int evalRPN(String[] tokens) {
        return mySolution(tokens);
    }
}

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        Solution o = new Solution();

        String tokens[] = {"2","1","+","3","*"};
        tokens = new String[]{"4","13","5","/","+"};
        tokens = new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(o.evalRPN(tokens));
    }
}
