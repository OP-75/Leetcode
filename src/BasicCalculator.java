import java.util.Stack;

public class BasicCalculator {


    private static String strToPostfix(String s){
        //NOTE that since we dont have "*" or "/" operators we dont have to take care of operator priority due to BODMAS
        Stack<Character> operators = new Stack<>(); 
        String postfix = "";
        for (int i = 0; i < s.length(); i++) {

            char token = s.charAt(i);

            if (token == ' ') continue;

            if (token>='0' && token<='9'){
                postfix += token;
                continue;
            }

            else if (token=='('){
                operators.push(token);
                continue;
            }

            else if (token==')'){

                while(operators.peek()!='('){
                    char poppedOperator = operators.pop();
                    postfix += poppedOperator;
                }
                operators.pop(); //discard the '(' opening braket operator
            }

            else{
                //this "," represents the end of a number
                if(!operators.isEmpty() && operators.peek()!='('){ 
                    postfix += operators.pop();
                }
                postfix += ","; //this "," represents the end of a number
                operators.add(token);
            } 
        }

        //add the remaning operator to the postfix string 
        while (!operators.isEmpty()) {
            char poppedOperator = operators.pop();
            postfix += poppedOperator;
        }

        postfix += ","; //this is for if string is just a number like input = "2147483647"

        return postfix;

    }



    private static int mySolution(String s){
        // -(3+4) to postfix will be 34+- 

        String postfix = strToPostfix(s);
        System.out.println("postfix = " + postfix);

        Stack<Integer> nums = new Stack<>();


        String singleNumber = "";
        for (int i = 0; i < postfix.length(); i++) {
            char token = postfix.charAt(i);

            if (token>='0' && token<='9'){
                singleNumber += token;
                continue;
            }
            else if((token==',' || token=='+' || token=='-') && !singleNumber.equals("")){ //the !singleNumber.equals("") is for the case if we have 21,33,4++  after the 1st '+' the "4" is parsed & pushed onto the stack but then the singleNum = "", then on the 2nd '+' we should not try to parse singleNum again since its empty

                // ',' reresents the end of a number
                nums.push(Integer.parseInt(singleNumber));
                singleNumber="";
            }
            else if((i==0 && token==',') || (token==',' && postfix.charAt(i-1)==',')){
                // for cases like 1-(-2) or -2+1
                nums.push(0);
            }



            if((token=='+' || token=='-') && nums.size()>=2){
                int num2 = nums.pop();
                int num1 = nums.pop();

                int ans;
                if(token=='+'){
                    ans = num1 + num2;
                }
                else{
                    ans = num1 - num2;
                }
                nums.push(ans);
            }
            else if(token=='-' && nums.size()==1){
                //this case is to cover unary "-" opertor like -(3+4)
                int num = nums.pop();
                nums.push(num*-1);
            }
        }

        if(nums.size()!=1){
            // throw new IllegalAccessError("Stack size is not 1, there are still nums in stack");
        }

        return nums.pop();


    }

    public static int calculate(String s) {
        return mySolution(s);

        // see DBabichev [Python] Basic Calculator I, II, III easy solution, detailed explanation. solution for efficient approach
        // see efficent solution on YT or Leetcode my solution isnt efficient!!
    }

    public static void main(String[] args) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate("2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("23+23"));
        System.out.println(calculate("2147483647"));
        System.out.println(calculate("1-(     -2)"));
        System.out.println(calculate("-2+1"));
    }

}
