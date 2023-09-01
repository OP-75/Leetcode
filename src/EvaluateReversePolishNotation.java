import java.util.Stack;

//THIS CODE IS STILL INCOMPLETE & YET TO BE SUBMITTED ON LEETCODE


class Solution {

    private int solve(int num1, int num2, String operator) {

        if (operator == "*")
            return num1 * num2;
        else if (operator == "/")
            return num1 / num2;
        else if (operator == "-")
            return num1 - num2;
        else
            return num1 + num2;
    }

    private boolean isOperator(String operator) {

        if (operator == "*" || operator == "/" || operator == "-" || operator == "+")
            return true;

        else
            return false;
    }

    private int mySolution(String[] tokens) {

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {

            String token = tokens[i];
            if (isOperator(token)) {
                int num2 = st.pop();
                int num1 = st.pop();
                int result = solve(num1, num2, token);

                st.push(result);
            } else {
                int asNum = Integer.parseInt(token);
                st.push(asNum);
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

        String tokens[] = { "2", "1", "+", "3", "*" };
        System.out.println(o.evalRPN(tokens));
    }
}
