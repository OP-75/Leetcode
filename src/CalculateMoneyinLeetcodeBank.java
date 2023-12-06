public class CalculateMoneyinLeetcodeBank {

    public int totalMoney(int n) {
        return mySolution(0, n, 0);
    }

    private int mySolution(int currDay, int n, int prevAmount) {
        if (currDay==n) {
            //n-1 caz we are starting counting from 0
            return 0;
        }

        if (currDay%7==0) {   
            //0th day of the week = Monday

            //if prevAmt == 0 then its our first monday, else we want the amount on monday of previous week & since we are just adding $1 everyday we can get that by subtracting 6 (ie we need the "previosAmount" of Tuesday which would ne the amount on monday)  
            prevAmount = prevAmount==0? 0 : prevAmount-6; 

            // System.out.println("Monday amt:"+(prevAmount+1));

            return prevAmount + 1 + mySolution(currDay+1, n, prevAmount+1); //prevAmount for next day = curr amount for this day
        }
        else{
            return prevAmount + 1 + mySolution(currDay+1, n, prevAmount+1);
        }
    }

    public static void main(String[] args) {
        CalculateMoneyinLeetcodeBank o = new CalculateMoneyinLeetcodeBank();
        System.out.println(o.totalMoney(10));
    }

}
